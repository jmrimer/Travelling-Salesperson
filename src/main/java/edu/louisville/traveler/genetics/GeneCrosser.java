package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;

abstract class GeneCrosser {
  int maxGeneSequenceLength;

  GeneCrosser(int maxGeneSequenceLength) {
    this.maxGeneSequenceLength = maxGeneSequenceLength;
  }

  abstract void crossover(LivingTour child, LivingTour[] parents, Map map);

  abstract void firstGenes(LivingTour[] parents, LivingTour child, Map map, double mutationChance);
}
