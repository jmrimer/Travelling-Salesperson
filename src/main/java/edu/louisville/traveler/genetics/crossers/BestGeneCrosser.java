package edu.louisville.traveler.genetics.crossers;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.genetics.crossers.GeneCrosser;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.RouteWeightCalculator;

import java.util.List;

public class BestGeneCrosser extends GeneCrosser {
  public BestGeneCrosser(int maxGeneSequenceLength) {
    super(maxGeneSequenceLength);
  }

  @Override
  public void crossover(LivingTour child, LivingTour[] parents, Map map) {
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

  public LivingTour selectBestParent(LivingTour[] parents, int genomeStart, int genomeLength) {
    double parent1RouteWeight = RouteWeightCalculator.calculateWeight(
      parents[0].getCycle().subList(genomeStart, genomeStart + genomeLength - 1)
    );

    double parent2RouteWeight = RouteWeightCalculator.calculateWeight(
      parents[1].getCycle().subList(genomeStart, genomeStart + genomeLength - 1)
    );

    return parent1RouteWeight < parent2RouteWeight ? parents[0] : parents[1];
  }

  @Override
  public void firstGenes(LivingTour[] parents, LivingTour child, Map map, double mutationChance) {
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

  @Override
  public void mutateSingleGene(Map map, LivingTour child) {
    int availableIndex = child.getCycle().indexOf(null);
    double bestWeight = Double.MAX_VALUE;
    City bestCity = null;

    if (availableIndex > -1) {
      for (City city : map.getCities()) {
        if (!child.getCycle().contains(city)) {


          float weight = RouteWeightCalculator.calculateWeight(List.of(child.getCycle().get(availableIndex - 1), city));
          if (
            weight < bestWeight
          ) {
            bestWeight = weight;
            bestCity = city;
          }
        }
      }
      child.getCycle().set(availableIndex, bestCity);
    }
  }

  public LivingTour[] checkForParentSuitabilityOnSequence(
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
