package edu.louisville.traveler.genetics.seeders;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RandomPopulationSeeder implements PopulationSeeder {

  @Override
  public List<LivingTour> seed(int populationSize, Map map) {
    List<LivingTour> randomGeneration = new ArrayList<>();
    for (int i = 0; i < populationSize; i++) {
      randomGeneration.add(generateRandomTour(map));
    }
    return randomGeneration;
  }

  private LivingTour generateRandomTour(Map map) {
    List<City> remainingCities = new ArrayList<>(map.getCities());
    List<City> route = new ArrayList<>();
    City start = addAndRemoveRandomCity(remainingCities, route);
    addAllRemainingCities(remainingCities, route);
    route.add(start);
    return new LivingTour(route);
  }

  private void addAllRemainingCities(List<City> remainingCities, List<City> route) {
    Iterator<City> cityIterator = remainingCities.iterator();
    while (cityIterator.hasNext()) {
      addAndRemoveRandomCity(remainingCities, route);
    }
  }

  private City addAndRemoveRandomCity(List<City> remainingCities, List<City> route) {
    City city = remainingCities.get((int) (Math.random() * remainingCities.size()));
    route.add(city);
    remainingCities.remove(city);
    return city;
  }
}
