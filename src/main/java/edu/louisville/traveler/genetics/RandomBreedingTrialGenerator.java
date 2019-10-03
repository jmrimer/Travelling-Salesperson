package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RandomBreedingTrialGenerator implements TrialGenerator {
  private final Map map;
  private final int newParentsPerGeneration;
  private final int totalGenerations;
  private List<LivingTour> currentParents;
  private List<LivingTour> currentChildren;
  private int deceasedParents;
  private int bornChildren;
  private int populationCap;
  private int maxGeneSequenceLength;

  RandomBreedingTrialGenerator(
    Map map,
    int newParentsPerGeneration,
    int totalGenerations,
    int populationCap,
    int maxGeneSequenceLength
  ) {
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
      System.out.println(gen);
      this.bornChildren = 0;
      this.deceasedParents = 0;
      setupNewGeneration(
        this.currentParents,
        this.currentChildren,
        this.newParentsPerGeneration,
        this.map
      );
      breedParents();
      controlPopulation(this.currentParents, this.currentChildren, this.populationCap);
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
    return trial;
  }

  @Override
  public void breedParents() {
    while (parentsAvailableForMating()) {
      breedAndKillMates(currentParents.get((int) (Math.random() * currentParents.size())));
      killParents();
    }
  }

  private void breedAndKillMates(LivingTour parentSeekingMate) {
    LivingTour randomMate = findRandomMate(parentSeekingMate);
    LivingTour child = Breeder.breedRandomParents(parentSeekingMate, randomMate, this.maxGeneSequenceLength);
    bornChildren++;
    if (currentChildren.size() == 0) {
      this.currentChildren.add(child);
    } else if (isFit(child)) {
      this.currentChildren.add(child);
    }
    parentSeekingMate.age();
    randomMate.age();
  }

  private boolean isFit(LivingTour newborn) {
    double averageFitness = 0;
    for (LivingTour child : currentChildren) {
      if (child != null) {
        averageFitness += child.getWeight();
      }
    }
    averageFitness /= currentChildren.size();
    return averageFitness == 0 || newborn.getWeight() < averageFitness;
  }

  private boolean parentsAvailableForMating() {
    currentParents.removeAll(Collections.singleton(null));
    return currentParents.size() > 2;
  }

  private void killParents() {
    Iterator<LivingTour> parentIterator = currentParents.iterator();
    while (parentIterator.hasNext()) {
      LivingTour parent = parentIterator.next();
      if (parent.isDead()) {
        this.deceasedParents++;
        parentIterator.remove();
      }
    }
    currentParents.removeAll(Collections.singleton(null));
  }

  private LivingTour findRandomMate(LivingTour parentSeekingMate) {
    int randomIndex = (int) (Math.random() * currentParents.size());
    while (randomIndex == currentParents.indexOf(parentSeekingMate)) {
      randomIndex = (int) (Math.random() * currentParents.size());
    }
    return currentParents.get(randomIndex);
  }
}
