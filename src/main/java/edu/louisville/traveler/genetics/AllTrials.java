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
  private int deceasedParents;
  private int bornChildren;
  private int populationCap;
  private int maxGeneSequenceLength;

  public AllTrials(
    Breeder breeder,
    Map map,
    int newParentsPerGeneration,
    int totalGenerations,
    int populationCap,
    int maxGeneSequenceLength
  ) {
    this.breeder = breeder;
    this.map = map;
    this.newParentsPerGeneration = newParentsPerGeneration;
    this.totalGenerations = totalGenerations;
    this.currentParents = new ArrayList<>();
    this.currentChildren = new ArrayList<>();
    this.deceasedParents = 0;
    this.bornChildren = 0;
    this.populationCap = populationCap;
    this.maxGeneSequenceLength = maxGeneSequenceLength;
  }

  @Override
  public Trial runTrial() {
    Trial trial = new Trial();
    for (int gen = 0; gen < totalGenerations; gen++) {
      resetCounters();
      newGeneration();
      breed();
      controlPopulation();
      addTrial(trial, gen);
    }
    return trial;
  }

  private void addTrial(Trial trial, int gen) {
    trial.add(
      new Generation(
        gen,
        new ArrayList<>(this.currentParents),
        new ArrayList<>(this.currentChildren),
        this.bornChildren,
        this.deceasedParents
      )
    );
  }

  private void controlPopulation() {
    controlPopulation(
      this.currentParents,
      this.currentChildren,
      this.populationCap
    );
  }

  private void breed() {
//    breeder.breedParents();
  }

  private void newGeneration() {
    setupNewGeneration(
      this.currentParents,
      this.currentChildren,
      this.newParentsPerGeneration,
      this.map
    );
  }

  private void resetCounters() {
    this.bornChildren = 0;
    this.deceasedParents = 0;
  }

  @Override
  public void breedParents() {

  }
}
