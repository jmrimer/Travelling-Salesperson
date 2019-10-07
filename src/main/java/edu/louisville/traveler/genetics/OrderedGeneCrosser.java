package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.*;

public class OrderedGeneCrosser extends BestGeneCrosser {

  OrderedGeneCrosser(int maxGeneSequenceLength) {
    super(maxGeneSequenceLength);
  }

  @Override
  void crossover(LivingTour child, LivingTour[] parents, Map map) {
    int genomeLength = randomGenomeLength(map);
    List<Integer> randomIndexes = randomIndexes(genomeLength, map);
    Collections.sort(randomIndexes);
    List<City> citiesFromParent1 = new ArrayList<>();
    for (int index : randomIndexes) {
      citiesFromParent1.add(parents[0].getCycle().get(index));
    }
//    find order of cities in parent 2
    List<City> citiesFromParent2 = new ArrayList<>();
    for (int i = 0; i < parents[1].getCycle().size(); i++) {
      if (citiesFromParent1.contains(parents[1].getCycle().get(i))) {
        citiesFromParent2.add(parents[1].getCycle().get(i));
      }
    }

    for (int i = 0; i < child.getCycle().size(); i++) {
      if (!randomIndexes.contains(i)) {
        child.getCycle().set(i, parents[0].getCycle().get(i));
      }
    }

    Iterator<City> cityIterator = citiesFromParent2.iterator();
    for (int i = 0; i < child.getCycle().size(); i++) {
      if (child.getCycle().get(i) == null) {
        child.getCycle().set(i, cityIterator.next());
      }
    }
//    transfer all randoms from one parent
//    then copy the rest into the openings from second parent
  }

  private List<Integer> randomIndexes(int genomeLength, Map map) {
    List<Integer> randomIndexes = new ArrayList<>();
    for (int i = 0; i < genomeLength; i++) {
      int randomIndex = (int) (Math.random() * map.getCities().size());
      while (randomIndexes.contains(randomIndex)) {
        randomIndex = (int) (Math.random() * map.getCities().size());
      }
      randomIndexes.add(randomIndex);
    }
    return randomIndexes;
  }

  @Override
  void firstGenes(LivingTour[] parents, LivingTour child, Map map, double mutationChance) {

  }

  @Override
  void mutateSingleGene(Map map, LivingTour child) {

  }
}
