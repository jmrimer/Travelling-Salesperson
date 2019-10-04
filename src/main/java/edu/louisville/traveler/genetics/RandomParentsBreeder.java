package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.ArrayList;
import java.util.Arrays;

public class RandomParentsBreeder implements Breeder {

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

}
