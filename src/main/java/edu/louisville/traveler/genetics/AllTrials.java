package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;

import java.util.ArrayList;
import java.util.List;

public class AllTrials implements TrialGenerator {
  private Breeder breeder;
  private final Map map;
  private final int newParentsPerGeneration;
  private final int totalGenerations;
  private List<LivingTour> currentParents;
  private List<LivingTour> currentChildren;
  private int populationCap;

  AllTrials(
    Breeder breeder,
    Map map,
    int newParentsPerGeneration,
    int totalGenerations,
    int populationCap
  ) {
    this.breeder = breeder;
    this.map = map;
    this.newParentsPerGeneration = newParentsPerGeneration;
    this.totalGenerations = totalGenerations;
    this.currentParents = new ArrayList<>();
    this.currentChildren = new ArrayList<>();
    this.populationCap = populationCap;
  }

  @Override
  public Trial runTrial() {
    Trial trial = new Trial();
    setupNewGeneration(currentParents, currentChildren, 512, map);
    for (int gen = 0; gen < totalGenerations; gen++) {
      newGeneration();
      Generation generation = breed(gen);
      controlPopulation(generation);
      trial.add(generation);
    }
    return trial;
  }

  private void controlPopulation(Generation generation) {
    controlPopulation(
      generation.getParentsAliveAtEndOfGeneration(),
      generation.getChildrenAliveAtEndOfGeneration(),
      this.populationCap
    );
  }

  private Generation breed(int gen) {
    return breeder.breedGeneration(
      this.map,
      this.currentParents,
      gen
    );
  }

  private void newGeneration() {
    setupNewGeneration(
      this.currentParents,
      this.currentChildren,
      this.newParentsPerGeneration,
      this.map
    );
  }

  @Override
  public void breedParents() {

  }
}
