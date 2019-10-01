package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
class GeneticTrialGenerator {
  private final Map map;
  private final int newParentsPerGeneration;
  private final int totalGenerations;
  private List<LivingTour> parents;
  private List<LivingTour> children;

  GeneticTrialGenerator(Map map, int newParentsPerGeneration, int totalGenerations) {
    this.map = map;
    this.newParentsPerGeneration = newParentsPerGeneration;
    this.totalGenerations = totalGenerations;
    this.parents = new ArrayList<>();
    this.children = new ArrayList<>();
  }

  Trial runTrial() {
    Trial trial = new Trial();
    for (int gen = 0; gen < totalGenerations; gen++) {
      createNewParents();
      childrenBecomeParents();
      breedAllParents();
      ageParents();
      revitalizeFitChildren();
      killParents();
      trial.add(
        new Generation(
          gen,
          new ArrayList<>(this.parents),
          new ArrayList<>(this.children)
        )
      );
    }
    return trial;  }

  Trial runTrialWithCompatibility() {
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
          new ArrayList<>(this.parents),
          new ArrayList<>(this.children)
        )
      );
    }
    return trial;
  }

  private void revitalizeFitChildren() {
    double averageFitness = 0;
    for (LivingTour child : children) {
      averageFitness += child.getWeight();
    }
    averageFitness /= children.size();
    for (LivingTour child : children) {
      if (child.getWeight() < averageFitness) {
        child.revitalize();
      }
    }
  }

  private void ageParents() {
    double averageFitness = 0;
    for (LivingTour child : children) {
      averageFitness += child.getWeight();
    }
    averageFitness /= children.size();
    for (LivingTour parent : parents) {
      parent.age();
      if (parent.getWeight() < averageFitness) {
        parent.age();
      }
    }
  }

  private void killParents() {
    parents.removeIf(LivingTour::isDead);
  }

  private void childrenBecomeParents() {
    parents.addAll(children);
    children = new ArrayList<>();
  }

  private void breedAllCompatibleParents() {
    List<LivingTour> remainingParents = new ArrayList<>(parents);
    Iterator<LivingTour> parentIterator = remainingParents.iterator();
    while (parentIterator.hasNext()) {
      LivingTour parent1 = parentIterator.next();
      parentIterator.remove();
      List<LivingTour> compatibleParents = findCompatibleMates(remainingParents, parent1);
      breedAndKillCompatibleMates(parent1, compatibleParents);
    }
  }

  private void breedAllParents() {
    List<LivingTour> remainingParents = new ArrayList<>(parents);
    Iterator<LivingTour> parentIterator = remainingParents.iterator();
    while (parentIterator.hasNext()) {
      LivingTour parent1 = parentIterator.next();
      parentIterator.remove();
      breedAndKillMates(parent1, remainingParents);
    }
  }

  private void breedAndKillMates(LivingTour parent1, List<LivingTour> remainingParents) {
    Iterator<LivingTour> mateIterator = remainingParents.iterator();
    while (livingParentHasSuitableMates(parent1, mateIterator)) {
      LivingTour randomMate = findRandomMate(remainingParents);
      LivingTour child = Breeder.breedParents(parent1, randomMate);
      if (child != null) {
        this.children.add(child);
        parent1.age();
        parents.get(parents.indexOf(randomMate)).age();
      }
      remainingParents.remove(randomMate);
    }
  }

  private void breedAndKillCompatibleMates(LivingTour parent1, List<LivingTour> compatibleParents) {
    Iterator<LivingTour> mateIterator = compatibleParents.iterator();
    while (livingParentHasSuitableMates(parent1, mateIterator)) {
      LivingTour randomMate = findRandomMate(compatibleParents);
      LivingTour child = Breeder.breedCompatibleParents(parent1, randomMate);
      if (child != null) {
        this.children.add(child);
        parent1.age();
        parents.get(parents.indexOf(randomMate)).age();
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

  private List<LivingTour> findCompatibleMates(List<LivingTour> remainingParents, LivingTour parent1) {
    List<LivingTour> compatibleMates = new ArrayList<>();
    for (LivingTour parent2 : remainingParents) {
      if (Breeder.compatible(parent1, parent2)) {
        compatibleMates.add(parent2);
      }
    }
    return compatibleMates;
  }

  private void createNewParents() {
    for (int i = 0; i < newParentsPerGeneration; i++) {
      this.parents.add(generateRandomTour());
    }
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
