package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RandomBreedingTrialGenerator implements TrialGenerator {
  private final Map map;
  private final int newParentsPerGeneration;
  private final int totalGenerations;
  private List<LivingTour> currentParents;
  private List<LivingTour> currentChildren;
  private List<LivingTour> deceasedParents;

  public RandomBreedingTrialGenerator(Map map, int newParentsPerGeneration, int totalGenerations) {
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
      breedAllParents();
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

  private void killParents() {
    currentParents.removeIf(LivingTour::isDead);
  }

  private void childrenBecomeParents() {
    currentParents.addAll(currentChildren);
    currentChildren = new ArrayList<>();
  }

  private void breedAllParents() {
    Iterator<LivingTour> parentIterator = currentParents.iterator();
    while (parentIterator.hasNext()) {
      LivingTour parentSeekingMate = parentIterator.next();
      breedAndKillMates(parentSeekingMate);
    }
  }

  private void breedAndKillMates(LivingTour parentSeekingMate) {
    Iterator<LivingTour> mateIterator = currentParents.iterator();
    while (livingParentHasSuitableMates(parentSeekingMate, mateIterator)) {
      LivingTour randomMate = findRandomMate(currentParents);
      LivingTour child = Breeder.breedParents(parentSeekingMate, randomMate);
      this.currentChildren.add(child);
      parentSeekingMate.age();
      currentParents.get(currentParents.indexOf(randomMate)).age();
      mateIterator.next();
    }
  }

  private boolean livingParentHasSuitableMates(LivingTour parent1, Iterator<LivingTour> mateIterator) {
    return mateIterator.hasNext() && !parent1.isDead();
  }

  private LivingTour findRandomMate(List<LivingTour> compatibleParents) {
    int randomIndex = (int) (Math.random() * compatibleParents.size());
    return compatibleParents.get(randomIndex);
  }

  private void createNewParents() {
    for (int i = 0; i < newParentsPerGeneration; i++) {
      this.currentParents.add(generateRandomTour());
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
