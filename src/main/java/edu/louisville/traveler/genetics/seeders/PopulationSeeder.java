package edu.louisville.traveler.genetics.seeders;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.Map;

import java.util.List;

public interface PopulationSeeder {
  List<LivingTour> seed(int populationSize, Map map);
}
