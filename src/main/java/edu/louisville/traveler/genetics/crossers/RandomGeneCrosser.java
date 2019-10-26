package edu.louisville.traveler.genetics.crossers;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.genetics.crossers.GeneCrosser;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

public class RandomGeneCrosser extends GeneCrosser {

  public RandomGeneCrosser(int maxGeneSequenceLength) {
    super(maxGeneSequenceLength);
  }

  public void crossover(LivingTour child, LivingTour[] parents, Map map) {
    int startingRandomGenomeLength = randomGenomeLength(map);
    int currentGenomeLength = startingRandomGenomeLength;
    int childFirstOpenGene = child.getCycle().indexOf(null);

    LivingTour breedingParent = selectRandomParent(parents);
    boolean bred = false;
    boolean triedBothParents = false;

    while (!bred) {
      if (bothParentsUnsuitableForReproduction(currentGenomeLength, triedBothParents)) {
        mutateSingleGene(map, child);
      }

      if (firstParentUnsuitableForReproduction(currentGenomeLength, triedBothParents)) {
        breedingParent = breedingParent.equals(parents[0]) ? parents[1] : parents[0];;
        triedBothParents = true;
        currentGenomeLength = startingRandomGenomeLength;
      }

      int childAvailableStartIndex = indexOfAvailableGenomeLength(
        child,
        currentGenomeLength,
        childFirstOpenGene
      );

      if (sequenceIsAvailable(childAvailableStartIndex)) {
        boolean parentIsSuitable = checkSuitabilityOfParentGenesForChild(
          breedingParent,
          child,
          currentGenomeLength,
          childAvailableStartIndex
        );

        if (parentIsSuitable) {
          transposeGenesToChild(child, currentGenomeLength, breedingParent, childAvailableStartIndex);
          bred = true;
        } else {
          childFirstOpenGene++;
        }
      } else {
        currentGenomeLength--;
      }
    }
  }

  private boolean firstParentUnsuitableForReproduction(int currentSequenceLength, boolean triedBothParents) {
    return !triedBothParents && currentSequenceLength == 0;
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

  @Override
  public void mutateSingleGene(Map map, LivingTour child) {
    if (child.getCycle().indexOf(null) > -1) {
      int mutantIndex = (int) (Math.random() * map.getCities().size());
      City mutantGene = map.getCities().get(mutantIndex);
      while (child.getCycle().contains(mutantGene)) {
        mutantGene = map.getCities().get((int) (Math.random() * map.getCities().size()));
      }
      child.getCycle().set(child.getCycle().indexOf(null), mutantGene);
    }
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

  private boolean bothParentsUnsuitableForReproduction(int currentSequenceLength, boolean triedBothParents) {
    return triedBothParents && currentSequenceLength == 0;
  }

  private LivingTour selectRandomParent(LivingTour[] parents) {
    return Math.random() < 0.5 ? parents[0] : parents[1];
  }

  private boolean shouldInheritGenes(double mutationChance) {
    return Math.random() * 100 < mutationChance;
  }
}
