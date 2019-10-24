package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.MapHelpers;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

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
    Point2D center = MapHelpers.centerOf(map);
    return null;
  }
}
