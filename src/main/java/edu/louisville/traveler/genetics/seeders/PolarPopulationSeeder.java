package edu.louisville.traveler.genetics.seeders;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.MapHelpers;
import edu.louisville.traveler.maps.PolarCoordinates;

import java.awt.geom.Point2D;
import java.util.*;

public class PolarPopulationSeeder implements PopulationSeeder {
  @Override
  public List<LivingTour> seed(int populationSize, Map map) {
    List<LivingTour> generation = new ArrayList<>();
    for (int i = 0; i < populationSize; i++) {
      generation.add(polarTour(map));
    }
    return generation;
  }

  private LivingTour polarTour(Map map) {
    TreeMap<PolarCoordinates, City> polarRoute = new TreeMap<>();

    Point2D center = MapHelpers.centerOf(map);
    for (City city : map.getCities()) {
      PolarCoordinates polarCoordinates = MapHelpers.mapPolarPointFromCenter(city, center);
      polarRoute.put(polarCoordinates, city);
    }
    List<City> polarCycle = new ArrayList<>(polarRoute.values());
    polarCycle.add(polarCycle.get(0));
    return new LivingTour(polarCycle);
  }
}
