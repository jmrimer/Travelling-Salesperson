package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.seeders.PopulationSeeder;
import edu.louisville.traveler.maps.Map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrialGenerator {
  private PopulationSeeder seeder;
  private Breeder breeder;
  private final int startingParentCount;
  private final int totalGenerations;
  private int populationCap;
  private Map map;
  private List<LivingTour> currentParents = new ArrayList<>();
  private final List<LivingTour> population = new ArrayList<>();

  public TrialGenerator(
    Map map,
    PopulationSeeder seeder,
    Breeder breeder,
    int startingParentCount,
    int totalGenerations,
    int populationCap) {
    this.map = map;
    this.seeder = seeder;
    this.breeder = breeder;
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
      trial.add(generation);
    }
    return trial;
  }

  private void controlPopulation(Generation generation) {
    int percentOfWorstFitToKeep = 10;
    int numberOfWeak = (int) (((double) percentOfWorstFitToKeep / 100) * populationCap);
    this.population.addAll(generation.getPopulation());
    if (this.population.size() > populationCap) {
      this.population.sort(Comparator.comparingDouble(LivingTour::getWeight));
      this.population.subList(populationCap - 1, this.population.size() - numberOfWeak).clear();
    }
  }

  private Generation breed(int gen) {
    return breeder.breedGeneration(gen, this.currentParents);
  }

  private void newGeneration() {
    this.currentParents.clear();
    this.currentParents.addAll(this.population);
    this.currentParents.forEach(parent -> parent.setBred(false));
    this.population.clear();
  }

  private void setupFirstGeneration() {
    this.population.addAll(seeder.seed(this.startingParentCount, this.map));
  }

}
