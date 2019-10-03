package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

abstract class GeneCrosser {
  int maxGeneSequenceLength;

  GeneCrosser(int maxGeneSequenceLength) {
    this.maxGeneSequenceLength = maxGeneSequenceLength;
  }

  abstract void crossover(LivingTour child, LivingTour[] parents, Map map);

  abstract void firstGenes(LivingTour[] parents, LivingTour child, Map map, double mutationChance);

  void transposeGenesToChild(
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

  int randomGenomeLength(Map map) {
    return this.maxGeneSequenceLength <= map.getCities().size() ?
      (int) (Math.random() * this.maxGeneSequenceLength) + 1 :
      (int) (Math.random() * map.getCities().size()) + 1;
  }

  static int indexOfAvailableGenomeLength(
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

  boolean sequenceIsAvailable(int childAvailableStartIndex) {
    return childAvailableStartIndex > -1;
  }

  abstract void mutateSingleGene(Map map, LivingTour child);

}
