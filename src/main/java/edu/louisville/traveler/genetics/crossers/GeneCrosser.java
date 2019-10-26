package edu.louisville.traveler.genetics.crossers;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

public abstract class GeneCrosser {
  public int maxGeneSequenceLength;

  public GeneCrosser(int maxGeneSequenceLength) {
    this.maxGeneSequenceLength = maxGeneSequenceLength;
  }

  public abstract void crossover(LivingTour child, LivingTour[] parents, Map map);

  public abstract void firstGenes(LivingTour[] parents, LivingTour child, Map map, double mutationChance);

  public void transposeGenesToChild(
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

  public int randomGenomeLength(Map map) {
    return this.maxGeneSequenceLength <= map.getCities().size() ?
      ((int) (Math.random() * this.maxGeneSequenceLength)) + 1 :
      ((int) (Math.random() * map.getCities().size())) + 1;
  }

  public static int indexOfAvailableGenomeLength(
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

  public boolean sequenceIsAvailable(int childAvailableStartIndex) {
    return childAvailableStartIndex > -1;
  }

  public abstract void mutateSingleGene(Map map, LivingTour child);

}
