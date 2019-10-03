package edu.louisville.traveler.genetics;

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

  RandomBreedingTrialGenerator(Map map, int newParentsPerGeneration, int totalGenerations) {
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
      setupNewGeneration(
        this.currentParents,
        this.currentChildren,
        this.newParentsPerGeneration,
        this.map
      );
      breedParents();
      controlPopulation(this.currentParents, this.currentChildren);
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

  @Override
  public void breedParents() {
    Iterator<LivingTour> parentIterator = currentParents.iterator();
    while (parentIterator.hasNext()) {
      LivingTour parentSeekingMate = parentIterator.next();
      breedAndKillMates(parentSeekingMate);
    }
  }

  private void breedAndKillMates(LivingTour parentSeekingMate) {
    Iterator<LivingTour> mateIterator = currentParents.iterator();
    while (livingParentHasSuitableMates(parentSeekingMate, mateIterator)) {
      LivingTour randomMate = findRandomMate(parentSeekingMate);
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

  private LivingTour findRandomMate(LivingTour parentSeekingMate) {
    int randomIndex = (int) (Math.random() * currentParents.size());
    while (randomIndex == currentParents.indexOf(parentSeekingMate)) {
      randomIndex = (int) (Math.random() * currentParents.size());
    }
    return currentParents.get(randomIndex);
  }
}
