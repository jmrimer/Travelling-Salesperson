package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.RouteWeightCalculator;
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
//      ageParents();
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

  private void ageParents() {
    for (LivingTour parent : parents) {
      parent.age();
    }
  }

  private void killParents() {
    parents.removeIf(LivingTour::isDead);
  }

  private void childrenBecomeParents() {
    parents.addAll(children);
    children = new ArrayList<>();
  }

  private void breedAllParents() {
    List<LivingTour> remainingParents = new ArrayList<>(parents);
    Iterator<LivingTour> parentIterator = remainingParents.iterator();
    while (parentIterator.hasNext()) {
      LivingTour parent1 = parentIterator.next();
      parentIterator.remove();
//      if compatible, put into list
//      choose random from list
//      age both parents
//      kill parents if necessary
      List<LivingTour> compatibleParents = new ArrayList<>();
      for (LivingTour parent2 : remainingParents) {
        if (Breeder.compatible(parent1, parent2)) {
          compatibleParents.add(parent2);
        }
//        LivingTour child = Breeder.breed(parent1, parent2);
//        if (child != null) {
//          this.children.add(child);
//        }
      }
      Iterator<LivingTour> mateIterator = compatibleParents.iterator();
      while (mateIterator.hasNext() && !parent1.isDead()) {
        int randomIndex = (int) (Math.random() * compatibleParents.size());
        LivingTour mate = compatibleParents.get(randomIndex);
        LivingTour child = Breeder.breed(parent1, mate);
        if (child != null) {
          this.children.add(child);
          parent1.age();
          parents.get(parents.indexOf(mate)).age();
        }
        compatibleParents.remove(randomIndex);
      }
    }
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
    return new LivingTour(route, RouteWeightCalculator.calculateWeight(route));
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
