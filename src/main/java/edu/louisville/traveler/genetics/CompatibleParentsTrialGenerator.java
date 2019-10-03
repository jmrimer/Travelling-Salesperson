package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class CompatibleParentsTrialGenerator implements TrialGenerator {
  private final Map map;
  private final int newParentsPerGeneration;
  private final int totalGenerations;
  private List<LivingTour> currentParents;
  private List<LivingTour> currentChildren;
  private List<LivingTour> deceasedParents;

  public CompatibleParentsTrialGenerator(Map map, int newParentsPerGeneration, int totalGenerations) {
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
    List<LivingTour> remainingParents = new ArrayList<>(currentParents);
    Iterator<LivingTour> parentIterator = remainingParents.iterator();
    while (parentIterator.hasNext()) {
      LivingTour parent1 = parentIterator.next();
      parentIterator.remove();
      List<LivingTour> compatibleParents = findCompatibleMates(remainingParents, parent1);
      breedAndKillCompatibleMates(parent1, compatibleParents);
    }
  }

  private List<LivingTour> findCompatibleMates(List<LivingTour> remainingParents, LivingTour parent1) {
    List<LivingTour> compatibleMates = new ArrayList<>();
    for (LivingTour parent2 : remainingParents) {
      if (Breeder.compatible(parent1, parent2)) {
        compatibleMates.add(parent2);
      }
    }
    return compatibleMates;
  }

  private void breedAndKillCompatibleMates(LivingTour parent1, List<LivingTour> compatibleParents) {
    Iterator<LivingTour> mateIterator = compatibleParents.iterator();
    while (livingParentHasSuitableMates(parent1, mateIterator)) {
      LivingTour randomMate = findRandomMate(compatibleParents);
      LivingTour child = Breeder.breedCompatibleParents(parent1, randomMate);
      if (child != null) {
        this.currentChildren.add(child);
        parent1.age();
        currentParents.get(currentParents.indexOf(randomMate)).age();
      }
      compatibleParents.remove(randomMate);
    }
  }

  private boolean livingParentHasSuitableMates(LivingTour parent1, Iterator<LivingTour> mateIterator) {
    return mateIterator.hasNext() && !parent1.isDead();
  }
}
