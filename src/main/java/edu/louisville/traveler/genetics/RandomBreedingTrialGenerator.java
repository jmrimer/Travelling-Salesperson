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
  private List<LivingTour> deceasedParents;
  private int populationCap = 10;

  RandomBreedingTrialGenerator(Map map, int newParentsPerGeneration, int totalGenerations, int populationCap) {
    this.map = map;
    this.newParentsPerGeneration = newParentsPerGeneration;
    this.totalGenerations = totalGenerations;
    this.currentParents = new ArrayList<>();
    this.currentChildren = new ArrayList<>();
    this.deceasedParents = new ArrayList<>();
    this.populationCap = populationCap;
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
      System.out.println(gen);
      breedParents();
      controlPopulation(this.currentParents, this.currentChildren, this.populationCap);
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
    while (parentsAvailableForMating()) {
      breedAndKillMates(currentParents.get((int) (Math.random() * currentParents.size())));
      killParents();
    }
//    Iterator<LivingTour> parentIterator = currentParents.iterator();
//    while (parentIterator.hasNext()) {
//      LivingTour parentSeekingMate = parentIterator.next();
//      breedAndKillMates(parentSeekingMate);
//    }
//      while (parentIterator.hasNext()) {
//      LivingTour parentSeekingMate = parentIterator.next();
//      breedAndKillMates(parentSeekingMate);
//    }
  }

  private void breedAndKillMates(LivingTour parentSeekingMate) {
    LivingTour randomMate = findRandomMate(parentSeekingMate);
    LivingTour child = Breeder.breedRandomParents(parentSeekingMate, randomMate);
    if (child != null) {
      this.currentChildren.add(child);
    }
    parentSeekingMate.age();
    randomMate.age();
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
        this.deceasedParents.add(parent);
        parentIterator.remove();
      }
    }
    currentParents.removeAll(Collections.singleton(null));
  }

  private boolean livingParentHasSuitableMates(LivingTour parentSeekingMate, Iterator<LivingTour> mateIterator) {
    return mateIterator.hasNext() && parentSeekingMate.isAlive();
  }

  private LivingTour findRandomMate(LivingTour parentSeekingMate) {
    int randomIndex = (int) (Math.random() * currentParents.size());
    while (randomIndex == currentParents.indexOf(parentSeekingMate)) {
      randomIndex = (int) (Math.random() * currentParents.size());
    }
    return currentParents.get(randomIndex);
  }
}
