package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TrialGenerator {
  private Breeder breeder;
  private final Map map;
  private final int startingParentCount;
  private final int totalGenerations;
  private int populationCap;
  private List<LivingTour> currentParents = new ArrayList<>();
  private final List<LivingTour> population = new ArrayList<>();

  TrialGenerator(
    Breeder breeder,
    Map map,
    int startingParentCount,
    int totalGenerations,
    int populationCap
  ) {
    this.breeder = breeder;
    this.map = map;
    this.startingParentCount = startingParentCount;
    this.totalGenerations = totalGenerations;
    this.populationCap = populationCap;
  }

  public Trial runTrial() {
    Trial trial = new Trial();
    setupFirstGeneration();
    for (int gen = 0; gen < totalGenerations; gen++) {
      newGeneration();
      Generation generation = breed(gen);
      controlPopulation(generation);
      if (gen == totalGenerations - 1) {
        trial.add(generation);
      }
    }
    return trial;
  }

  private void controlPopulation(Generation generation) {
    this.population.addAll(generation.getChildrenAliveAtEndOfGeneration());
    this.population.addAll(generation.getParentsAliveAtEndOfGeneration());
    if (this.population.size() > populationCap) {
      this.population.sort(Comparator.comparingDouble(LivingTour::getWeight));
      this.population.subList(populationCap - 1, this.population.size() - 1).clear();
    }
  }

  private Generation breed(int gen) {
    return breeder.breedGeneration(
      this.map,
      this.currentParents,
      gen
    );
  }

  private void newGeneration() {
    this.currentParents.clear();
    this.currentParents.addAll(this.population);
    for (LivingTour parent : this.currentParents) {
      parent.setBred(false);
    }
    this.population.clear();
  }

  private void setupFirstGeneration() {
    for (int i = 0; i < this.startingParentCount; i++) {
      this.population.add(generateRandomTour(this.map));
    }
  }

  private LivingTour generateRandomTour(Map map) {
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
