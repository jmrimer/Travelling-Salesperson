package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class CompatibleParentsTrialGenerator implements TrialGenerator {
  private final Map map;
  private final int newParentsPerGeneration;
  private final int totalGenerations;
  private List<LivingTour> currentParents;
  private List<LivingTour> currentChildren;
  private List<LivingTour> deceasedParents;

  public CompatibleParentsTrialGenerator(Map map, int newParentsPerGeneration, int totalGenerations) {
    this.map = map;
    this.newParentsPerGeneration = newParentsPerGeneration;
    this.totalGenerations = totalGenerations;
    this.currentParents = new ArrayList<>();
    this.currentChildren = new ArrayList<>();
    this.deceasedParents = new ArrayList<>();
  }

  @Override
  public Trial runTrial() {
    Trial trial = new Trial();
    for (int gen = 0; gen < totalGenerations; gen++) {
      createNewParents();
      childrenBecomeParents();
      breedAllCompatibleParents();
      ageParents();
      revitalizeFitChildren();
      killParents();
      trial.add(
        new Generation(
          gen,
          new ArrayList<>(this.currentParents),
          new ArrayList<>(this.currentChildren),
          new ArrayList<>(this.currentChildren),
          new ArrayList<>(this.deceasedParents)
        )
      );
    }
    return trial;
  }

  private void createNewParents() {
    for (int i = 0; i < newParentsPerGeneration; i++) {
      this.currentParents.add(generateRandomTour());
    }
  }

  private void childrenBecomeParents() {
    currentParents.addAll(currentChildren);
    currentChildren = new ArrayList<>();
  }

  private void breedAllCompatibleParents() {
    List<LivingTour> remainingParents = new ArrayList<>(currentParents);
    Iterator<LivingTour> parentIterator = remainingParents.iterator();
    while (parentIterator.hasNext()) {
      LivingTour parent1 = parentIterator.next();
      parentIterator.remove();
      List<LivingTour> compatibleParents = findCompatibleMates(remainingParents, parent1);
      breedAndKillCompatibleMates(parent1, compatibleParents);
    }
  }

  private void ageParents() {
    double averageFitness = 0;
    for (LivingTour child : currentChildren) {
      averageFitness += child.getWeight();
    }
    averageFitness /= currentChildren.size();
    for (LivingTour parent : currentParents) {
      parent.age();
      if (parent.getWeight() < averageFitness) {
        parent.age();
      }
    }
  }

  private void revitalizeFitChildren() {
    double averageFitness = 0;
    for (LivingTour child : currentChildren) {
      averageFitness += child.getWeight();
    }
    averageFitness /= currentChildren.size();
    for (LivingTour child : currentChildren) {
      if (child.getWeight() < averageFitness) {
        child.revitalize();
      }
    }
  }

  private void killParents() {
    currentParents.removeIf(LivingTour::isDead);
  }

  private List<LivingTour> findCompatibleMates(List<LivingTour> remainingParents, LivingTour parent1) {
    List<LivingTour> compatibleMates = new ArrayList<>();
    for (LivingTour parent2 : remainingParents) {
      if (Breeder.compatible(parent1, parent2)) {
        compatibleMates.add(parent2);
      }
    }
    return compatibleMates;
  }

  private void breedAndKillCompatibleMates(LivingTour parent1, List<LivingTour> compatibleParents) {
    Iterator<LivingTour> mateIterator = compatibleParents.iterator();
    while (livingParentHasSuitableMates(parent1, mateIterator)) {
      LivingTour randomMate = findRandomMate(compatibleParents);
      LivingTour child = Breeder.breedCompatibleParents(parent1, randomMate);
      if (child != null) {
        this.currentChildren.add(child);
        parent1.age();
        currentParents.get(currentParents.indexOf(randomMate)).age();
      }
      compatibleParents.remove(randomMate);
    }
  }

  private boolean livingParentHasSuitableMates(LivingTour parent1, Iterator<LivingTour> mateIterator) {
    return mateIterator.hasNext() && !parent1.isDead();
  }

  private LivingTour findRandomMate(List<LivingTour> compatibleParents) {
    int randomIndex = (int) (Math.random() * compatibleParents.size());
    return compatibleParents.get(randomIndex);
  }

  private LivingTour generateRandomTour() {
    List<City> remainingCities = new ArrayList<>(map.getCities());
    List<City> route = new ArrayList<>();
    City start = addAndRemoveRandomCity(remainingCities, route);
    addAllRemainingCities(remainingCities, route);
    route.add(start);
    return new LivingTour(route);
  }

  private void addAllRemainingCities(List<City> remainingCities, List<City> route) {
    Iterator<City> cityIterator = remainingCities.iterator();
    while (cityIterator.hasNext()) {
      addAndRemoveRandomCity(remainingCities, route);
    }
  }

  private City addAndRemoveRandomCity(List<City> remainingCities, List<City> route) {
    City city = remainingCities.get((int) (Math.random() * remainingCities.size()));
    route.add(city);
    remainingCities.remove(city);
    return city;
  }

}
