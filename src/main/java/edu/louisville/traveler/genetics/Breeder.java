package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;

import java.util.List;

public interface Breeder {
  LivingTour breedParents(LivingTour parent1, LivingTour parent2, int maxGeneSequenceLength);

  Generation breedGeneration(Map map, List<LivingTour> currentParents, int gen);

  static void firstGene(LivingTour parent, LivingTour child, int maxGeneSequenceLength) {
    int geneSequenceLength = maxGeneSequenceLength <= parent.getCycle().size() ?
      (int) (Math.random() * maxGeneSequenceLength):
      (int) (Math.random() * parent.getCycle().size());

    if (geneSequenceLength < 2) {
      geneSequenceLength = 2;
    }

    for (int i = 0; i < geneSequenceLength; i++) {
      child.getCycle().set(i, parent.getCycle().get(i));
    }
    child.getCycle().set(child.getCycle().size() - 1, parent.getCycle().get(0));
  }

  static LivingTour selectRandomParent(LivingTour parent1, LivingTour parent2) {
    return Math.random() < 0.5 ? parent1 : parent2;
  }

  static boolean childRouteIsNotComplete(LivingTour child) {
    return child.getCycle().contains(null);
  }
}
