package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

public class RandomGeneCrosser extends GeneCrosser {

  RandomGeneCrosser(int maxGeneSequenceLength) {
    super(maxGeneSequenceLength);
  }

  void crossover(LivingTour child, LivingTour[] parents, Map map) {
    int genomeLength = this.maxGeneSequenceLength <= map.getCities().size() ?
      (int) (Math.random() * this.maxGeneSequenceLength) + 1 :
      (int) (Math.random() * map.getCities().size()) + 1;
    LivingTour selectedParent = selectRandomParent(parents);
    LivingTour backupParent = selectedParent.equals(parents[0]) ? parents[1] : parents[0];
    int currentSequenceLength = genomeLength;
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
        currentSequenceLength = genomeLength;
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

  @Override
  public void firstGenes(LivingTour[] parents, LivingTour child, Map map, double mutationChance) {
    boolean shouldInherit = shouldInheritGenes(mutationChance);
    LivingTour parent = selectRandomParent(parents);

    int geneSequenceLength = this.maxGeneSequenceLength <= parent.getCycle().size() ?
      (int) (Math.random() * this.maxGeneSequenceLength) :
      (int) (Math.random() * parent.getCycle().size());

    if (geneSequenceLength < 2) {
      geneSequenceLength = 2;
    }

    if (shouldInherit) {
      for (int i = 0; i < geneSequenceLength; i++) {
        child.getCycle().set(i, parent.getCycle().get(i));
      }
    } else {
      for (int i = 0; i < geneSequenceLength; i++) {
        mutateSingleGene(map, child);
      }
    }
    child.getCycle().set(child.getCycle().size() - 1, child.getCycle().get(0));
  }

  private static int indexOfAvailableOpening(
    LivingTour child,
    int currentSequenceLength,
    int childFirstOpenGene
  ) {
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

  private boolean sequenceAvailable(int childAvailableStartIndex) {
    return childAvailableStartIndex > -1;
  }

  private boolean checkSuitabilityOfParentGenesForChild(
    LivingTour parent,
    LivingTour child,
    int genomeLength,
    int geneStartingIndex
  ) {
    boolean suitable = true;
    for (int i = 0; i < genomeLength; i++) {
      City parentGene = parent.getCycle().get(geneStartingIndex + i);
      if (child.getCycle().contains(parentGene)) {
        suitable = false;
        break;
      }
    }
    return suitable;
  }

  private void transposeGenesToChild(
    LivingTour child,
    int currentSequenceLength,
    LivingTour breedingParent,
    int childAvailableStartIndex
  ) {
    for (int i = 0; i < currentSequenceLength; i++) {
      int geneIndex = childAvailableStartIndex + i;
      City parentGene = breedingParent.getCycle().get(geneIndex);
      child.getCycle().set(geneIndex, parentGene);
    }
  }

  private LivingTour selectRandomParent(LivingTour[] parents) {
    return Math.random() < 0.5 ? parents[0] : parents[1];
  }

  private void mutateSingleGene(Map map, LivingTour child) {
    if (child.getCycle().indexOf(null) > -1) {
      int mutantIndex = (int) (Math.random() * map.getCities().size());
      City mutantGene = map.getCities().get(mutantIndex);
      while (child.getCycle().contains(mutantGene)) {
        mutantGene = map.getCities().get((int) (Math.random() * map.getCities().size()));
      }
      child.getCycle().set(child.getCycle().indexOf(null), mutantGene);
    }
  }

  private boolean shouldInheritGenes(double mutationChance) {
    return Math.random() * 100 < mutationChance;
  }
}
