package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.List;

public interface Breeder {
  LivingTour breedParents(LivingTour parent1, LivingTour parent2, int maxGeneSequenceLength);

  Generation breedGeneration(Map map, List<LivingTour> currentParents, int gen);

  static void firstGenes(
    LivingTour parent,
    LivingTour child,
    Map map,
    int maxGeneSequenceLength,
    double mutationChance
  ) {
    boolean shouldInherit = Breeder.shouldInheritGenes(mutationChance);

    int geneSequenceLength = maxGeneSequenceLength <= parent.getCycle().size() ?
      (int) (Math.random() * maxGeneSequenceLength) :
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

  static LivingTour selectRandomParent(LivingTour parent1, LivingTour parent2) {
    return Math.random() < 0.5 ? parent1 : parent2;
  }

  static boolean shouldInheritGenes(double mutationChance) {
    return Math.random() * 100 < mutationChance;
  }

  static boolean childRouteIsNotComplete(LivingTour child) {
    return child.getCycle().contains(null);
  }

  static void mutateSingleGene(Map map, LivingTour child) {
    if (child.getCycle().indexOf(null) > -1) {
      int mutantIndex = (int) (Math.random() * map.getCities().size());
      City mutantGene = map.getCities().get(mutantIndex);
      while (child.getCycle().contains(mutantGene)) {
        mutantGene = map.getCities().get((int) (Math.random() * map.getCities().size()));
      }
      child.getCycle().set(child.getCycle().indexOf(null), mutantGene);
    }
  }
}
