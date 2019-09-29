package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.RouteWeightCalculator;
import edu.louisville.traveler.maps.Tour;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

@Data
class GeneticTrialGenerator {
  private final List<City> map;
  private final int newParentsPerGeneration;
  private final int totalGenerations;
  List<Tour> parents;
  List<Tour> livingParents;
  private List<Tour> children;

  GeneticTrialGenerator(List<City> map, int newParentsPerGeneration, int totalGenerations) {
    this.map = map;
    this.newParentsPerGeneration = newParentsPerGeneration;
    this.totalGenerations = totalGenerations;
    this.parents = new ArrayList<>();
    this.children = new ArrayList<>();
  }

  void runTrial() {
    for (int generation = 0; generation < totalGenerations; generation++) {
      createNewParents();
      breedAllParents();
    }
  }

  private void breedAllParents() {
    List<Tour> remainingParents = new ArrayList<>(parents);
    Iterator<Tour> parentIterator = remainingParents.iterator();
    while (parentIterator.hasNext()) {
      Tour parent1 = parentIterator.next();
      parentIterator.remove();
      for (Tour parent2 : remainingParents) {
        Tour child = Breeder.breed(parent1, parent2);
        if (child != null) {
          this.children.add(child);
        }
      }
    }
  }

  private void createNewParents() {
    for (int i = 0; i < newParentsPerGeneration; i++) {
      this.parents.add(generateRandomTour());
    }
  }

  private Tour generateRandomTour() {
    List<City> remainingCities = new ArrayList<>(map);
    List<City> route = new ArrayList<>();
    City start = addAndRemoveRandomCity(remainingCities, route);
    addAllRemainingCities(remainingCities, route);
    route.add(start);
    return new Tour(route, RouteWeightCalculator.calculateWeight(route));
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
