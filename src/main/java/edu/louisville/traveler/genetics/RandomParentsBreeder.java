package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.*;

public class RandomParentsBreeder extends Breeder {

  RandomParentsBreeder(ParentSelector parentSelector, Map map, int maxGeneSequenceLength, double mutationChance) {
    super(parentSelector, map, maxGeneSequenceLength, mutationChance);
  }

  @Override
  void addSequenceToChild(LivingTour child, LivingTour[] parents, int geneSequenceLength) {
    LivingTour selectedParent = selectRandomParent(parents);
    LivingTour backupParent = selectedParent.equals(parents[0]) ? parents[1] : parents[0];
    int currentSequenceLength = geneSequenceLength;
    LivingTour breedingParent = selectedParent;
    boolean bred = false;
    int childFirstOpenGene = child.getCycle().indexOf(null);
    boolean triedBothParents = false;

    while (!bred) {
      if (triedBothParents && currentSequenceLength == 0) {
        Breeder.mutateSingleGene(
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
