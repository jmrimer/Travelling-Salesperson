package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.*;

public class RandomParentsBreeder implements Breeder {
  private List<LivingTour> currentChildren;
  private int maxGeneSequenceLength;
  private int bornChildren;
  private int deceasedParents;


  RandomParentsBreeder(int maxGeneSequenceLength) {
    this.maxGeneSequenceLength = maxGeneSequenceLength;
  }

  @Override
  public LivingTour breedParents(LivingTour parent1, LivingTour parent2, int maxGeneSequenceLength) {
    Map map = new Map(new ArrayList<>(parent1.getCycle()));
    City[] emptyRoute = new City[map.getCities().size()];
    LivingTour child = new LivingTour(Arrays.asList(emptyRoute));

    Breeder.firstGene(
      Breeder.selectRandomParent(parent1, parent2),
      child,
      maxGeneSequenceLength
    );

    while (Breeder.childRouteIsNotComplete(child)) {
      LivingTour selectedParent = Breeder.selectRandomParent(parent1, parent2);
      LivingTour backupParent = selectedParent.equals(parent1) ? parent2 : parent1;
      int geneSequenceLength = maxGeneSequenceLength <= map.getCities().size() ?
        (int) (Math.random() * maxGeneSequenceLength) :
        (int) (Math.random() * map.getCities().size());
      addSequenceToChild(map, child, selectedParent, backupParent, geneSequenceLength);
    }
    return child;
  }

  @Override
  public Generation breedGeneration(Map map, List<LivingTour> currentParents, int gen) {
    currentChildren = new ArrayList<>();
    bornChildren = 0;
    deceasedParents = 0;
    while (parentsAvailableForMating(currentParents)) {
      breedAndKillMates(currentParents);
      killParents(currentParents);
    }
    return new Generation(
      gen,
      currentParents,
      currentChildren,
      bornChildren,
      deceasedParents
    );
  }

  private static void addSequenceToChild(
    Map map, LivingTour child,
    LivingTour selectedParent,
    LivingTour backupParent,
    int geneSequenceLength
  ) {
    int currentSequenceLength = geneSequenceLength;
    LivingTour breedingParent = selectedParent;
    boolean bred = false;
    int childFirstOpenGene = child.getCycle().indexOf(null);
    boolean triedBothParents = false;

    while (!bred) {
      if (triedBothParents && currentSequenceLength == 0) {
        mutateSingleGene(
          map,
          child
        );
      }

      if (!triedBothParents && currentSequenceLength == 0) {
        breedingParent = backupParent;
        triedBothParents = true;
        currentSequenceLength = geneSequenceLength;
      }

      int childAvailableStartIndex =
        indexOfAvailableOpening(
          child,
          currentSequenceLength,
          childFirstOpenGene
        );

      if (sequenceAvailable(childAvailableStartIndex)) {
        boolean suitable = checkSuitabilityOfParentGenesForChild(
          breedingParent,
          child,
          currentSequenceLength,
          childAvailableStartIndex
        );

        if (suitable) {
          transposeGenesToChild(child, currentSequenceLength, breedingParent, childAvailableStartIndex);
          bred = true;
        } else {
          childFirstOpenGene++;
        }
      } else {
        currentSequenceLength--;
      }
    }
  }

  private static void mutateSingleGene(Map map, LivingTour child) {
    int mutantIndex = (int) (Math.random() * map.getCities().size());
    City mutantGene = map.getCities().get(mutantIndex);
    while (child.getCycle().contains(mutantGene)) {
      mutantGene = map.getCities().get((int) (Math.random() * map.getCities().size()));
    }
    child.getCycle().set(child.getCycle().indexOf(null), mutantGene);
  }

  private static int indexOfAvailableOpening(LivingTour child, int currentSequenceLength, int childFirstOpenGene) {
    int indexOfOpening = -1;
    for (int i = childFirstOpenGene; i <= child.getCycle().size() - currentSequenceLength; i++) {
      boolean availableSequence = true;
      for (int j = 0; j < currentSequenceLength; j++) {
        if (child.getCycle().get(i + j) != null) {
          availableSequence = false;
          break;
        }
      }
      if (availableSequence) {
        indexOfOpening = i;
        break;
      }
    }
    return indexOfOpening;
  }

  private static boolean sequenceAvailable(int childAvailableStartIndex) {
    return childAvailableStartIndex > -1;
  }

  private static boolean checkSuitabilityOfParentGenesForChild(
    LivingTour parent,
    LivingTour child,
    int geneSequenceLength,
    int geneStartingIndex
  ) {
    boolean suitable = true;
    for (int i = 0; i < geneSequenceLength; i++) {
      City parentGene = parent.getCycle().get(geneStartingIndex + i);
      if (child.getCycle().contains(parentGene)) {
        suitable = false;
        break;
      }
    }
    return suitable;
  }

  private static void transposeGenesToChild(LivingTour child, int currentSequenceLength, LivingTour breedingParent, int childAvailableStartIndex) {
    for (int i = 0; i < currentSequenceLength; i++) {
      int geneIndex = childAvailableStartIndex + i;
      City parentGene = breedingParent.getCycle().get(geneIndex);
      child.getCycle().set(geneIndex, parentGene);
    }
  }

  private boolean parentsAvailableForMating(List<LivingTour> currentParents) {
    currentParents.removeAll(Collections.singleton(null));
    return currentParents.size() > 2;
  }

  private void breedAndKillMates(List<LivingTour> currentParents) {
    LivingTour parentSeekingMate = currentParents.get((int) (Math.random() * currentParents.size()));
    LivingTour randomMate = findRandomMate(parentSeekingMate, currentParents);
    LivingTour child = breedParents(parentSeekingMate, randomMate, this.maxGeneSequenceLength);
    bornChildren++;
    if (currentChildren.size() == 0) {
      this.currentChildren.add(child);
    } else if (isFit(child)) {
      this.currentChildren.add(child);
    }
    parentSeekingMate.age();
    randomMate.age();
  }

  private LivingTour findRandomMate(LivingTour parentSeekingMate, List<LivingTour> currentParents) {
    int randomIndex = (int) (Math.random() * currentParents.size());
    while (randomIndex == currentParents.indexOf(parentSeekingMate)) {
      randomIndex = (int) (Math.random() * currentParents.size());
    }
    return currentParents.get(randomIndex);
  }

  private void killParents(List<LivingTour> currentParents) {
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
}
