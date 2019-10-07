package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.RouteWeightCalculator;

class BestGeneCrosser extends GeneCrosser {
  BestGeneCrosser(int maxGeneSequenceLength) {
    super(maxGeneSequenceLength);
  }

  @Override
  void crossover(LivingTour child, LivingTour[] parents, Map map) {
    int currentGenomeLength = randomGenomeLength(map);
    int childFirstOpenGene = child.getCycle().indexOf(null);

    boolean bred = false;

    while (!bred) {
      if (currentGenomeLength == 0) {
        mutateSingleGene(map, child);
        break;
      }

      int childAvailableStartIndex = indexOfAvailableGenomeLength(
        child,
        currentGenomeLength,
        childFirstOpenGene
      );

      if (sequenceIsAvailable(childAvailableStartIndex)) {
        LivingTour[] suitableParents = checkForParentSuitabilityOnSequence(
          parents,
          child,
          childAvailableStartIndex,
          currentGenomeLength
        );

        if (suitableParents.length == 0) {
          currentGenomeLength--;
        } else if (suitableParents.length == 1) {
          transposeGenesToChild(
            child,
            currentGenomeLength,
            suitableParents[0],
            childAvailableStartIndex
          );
          bred = true;
        } else {
          LivingTour breedingParent = selectBestParent(
            parents,
            childAvailableStartIndex,
            currentGenomeLength
          );
          transposeGenesToChild(
            child,
            currentGenomeLength,
            breedingParent,
            childAvailableStartIndex
          );
          bred = true;
        }
      } else {
        currentGenomeLength--;
      }
    }
  }

  LivingTour selectBestParent(LivingTour[] parents, int genomeStart, int genomeLength) {
    double parent1RouteWeight = RouteWeightCalculator.calculateWeight(
      parents[0].getCycle().subList(genomeStart, genomeStart + genomeLength - 1)
    );

    double parent2RouteWeight = RouteWeightCalculator.calculateWeight(
      parents[1].getCycle().subList(genomeStart, genomeStart + genomeLength - 1)
    );

    return parent1RouteWeight < parent2RouteWeight ? parents[0] : parents[1];
  }

  @Override
  void firstGenes(LivingTour[] parents, LivingTour child, Map map, double mutationChance) {
    int genomeLength = randomGenomeLength(map);
    LivingTour breedingParent = selectBestParent(
      parents,
      0,
      genomeLength
    );
    transposeGenesToChild(
      child,
      genomeLength,
      breedingParent,
      0
    );
    child.getCycle().set(child.getCycle().size() - 1, child.getCycle().get(0));
  }

  LivingTour[] checkForParentSuitabilityOnSequence(
    LivingTour[] parents,
    LivingTour child,
    int childAvailableStartIndex,
    int currentGenomeLength
  ) {
    LivingTour[] suitableParents = new LivingTour[]{};
    boolean parent1IsSuitable = true;
    boolean parent2IsSuitable = true;

    for (int i = 0; i < currentGenomeLength; i++) {
      if (child.getCycle().contains(parents[0].getCycle().get(childAvailableStartIndex + i))) {
        parent1IsSuitable = false;
      }
      if (child.getCycle().contains(parents[1].getCycle().get(childAvailableStartIndex + i))) {
        parent2IsSuitable = false;
      }
    }

    if (parent1IsSuitable && parent2IsSuitable) {
      suitableParents = parents;
    } else if (parent1IsSuitable) {
      suitableParents = new LivingTour[]{parents[0]};
    } else if (parent2IsSuitable) {
      suitableParents = new LivingTour[]{parents[1]};
    }

    return suitableParents;
  }
}
