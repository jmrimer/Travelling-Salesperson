package edu.louisville.traveler.genetics.crossers;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.genetics.crossers.BestGeneCrosser;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.ArrayList;
import java.util.List;

public class OrderedGeneCrosser extends BestGeneCrosser {

  public OrderedGeneCrosser(int maxGeneSequenceLength) {
    super(maxGeneSequenceLength);
  }

  @Override
  public void crossover(LivingTour child, LivingTour[] parents, Map map) {
    int genomeLength = randomGenomeLength(map) - 1;
    int randomStart = (int) (Math.random() * (map.getCities().size() - genomeLength));
    int randomEnd = randomStart + genomeLength;
    child.setCycle(orderedCrossOnInclusiveIndexRange(parents, randomStart, randomEnd));
  }

  private List<City> createOrderedListFromRemainingCities(LivingTour parent, List<City> citiesFromParent1) {
    List<City> orderedCities = new ArrayList<>();
    for (int i = 0; i < parent.getCycle().size() - 1; i++) {
      City orderedCity = parent.getCycle().get(i);
      if (!citiesFromParent1.contains(orderedCity)) {
        orderedCities.add(orderedCity);
      }
    }
    return orderedCities;
  }

  public List<City> createRandomCityListFromParent(LivingTour parent, List<Integer> indexes) {
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
  public void firstGenes(LivingTour[] parents, LivingTour child, Map map, double mutationChance) {

  }

  @Override
  public void mutateSingleGene(Map map, LivingTour child) {

  }

  public List<City> orderedCrossOnInclusiveIndexRange(LivingTour[] parents, int start, int end) {
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
