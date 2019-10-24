package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.*;

class OrderedGeneCrosser extends BestGeneCrosser {

  OrderedGeneCrosser(int maxGeneSequenceLength) {
    super(maxGeneSequenceLength);
  }

  @Override
  void crossover(LivingTour child, LivingTour[] parents, Map map) {
    int genomeLength = randomGenomeLength(map);
    int randomStart = (int) (Math.random() * (map.getCities().size() - genomeLength));
    int randomEnd = randomStart + genomeLength;
//    List<Integer> randomIndexes = randomIndexes(genomeLength, map);

    child.setCycle(orderedCrossOnInclusiveIndexRange(parents, randomStart, randomEnd));
//
//    List<City> citiesFromParent1 =
//      createRandomCityListFromParent(parents[0], randomIndexes);
//
//    List<City> citiesFromParent2 =
//      createOrderedListFromRemainingCities(parents[1], citiesFromParent1);
//
//    for (int i = 0; i < parents[1].getCycle().size(); i++) {
//      City orderedCity = parents[1].getCycle().get(i);
//      if (citiesFromParent1.contains(orderedCity)) {
//        citiesFromParent2.add(orderedCity);
//      }
//    }
//
//    for (int i = 0; i < child.getCycle().size(); i++) {
//      if (!randomIndexes.contains(i)) {
//        child.getCycle().set(i, parents[0].getCycle().get(i));
//      }
//    }
//
//    Iterator<City> cityIterator = citiesFromParent2.iterator();
//    for (int i = 0; i < child.getCycle().size(); i++) {
//      if (child.getCycle().get(i) == null) {
//        child.getCycle().set(i, cityIterator.next());
//      }
//    }
//    child.getCycle().set(child.getCycle().size() - 1, child.getCycle().get(0));
  }

  private List<City> createOrderedListFromRemainingCities(LivingTour parent, List<City> citiesFromParent1) {
    List<City> orderedCities = new ArrayList<>();
    for (int i = 0; i < parent.getCycle().size(); i++) {
      City orderedCity = parent.getCycle().get(i);
      if (!citiesFromParent1.contains(orderedCity)) {
        orderedCities.add(orderedCity);
      }
    }
    return orderedCities;
  }

  List<City> createRandomCityListFromParent(LivingTour parent, List<Integer> indexes) {
    List<City> citiesFromParent = new ArrayList<>();
    for (int index : indexes) {
      citiesFromParent.add(parent.getCycle().get(index));
    }
    return citiesFromParent;
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

  List<City> orderedCrossOnInclusiveIndexRange(LivingTour[] parents, int start, int end) {
    List<Integer> indexes = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      indexes.add(i);
    }

    List<City> citiesFromParent1 =
      createRandomCityListFromParent(parents[0], indexes);

    List<City> citiesFromParent2 =
      createOrderedListFromRemainingCities(parents[1], citiesFromParent1);

    citiesFromParent1.addAll(citiesFromParent2);
    citiesFromParent1.add(citiesFromParent1.get(0));
    return citiesFromParent1;
  }
}
