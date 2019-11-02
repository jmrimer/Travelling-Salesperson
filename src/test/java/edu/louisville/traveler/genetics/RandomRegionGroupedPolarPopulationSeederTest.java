package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.seeders.RandomRegionGroupedPolarPopulationSeeder;
import edu.louisville.traveler.maps.City;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomRegionGroupedPolarPopulationSeederTest extends BaseGeneticsTest {
  @Test
  public void keepCitiesInRegions() {
    List<LivingTour> generation = new RandomRegionGroupedPolarPopulationSeeder().seed(1, map10);
    List<City> cycle = generation.get(0).getCycle();

    List<City> region1 = new ArrayList<>(cycle.subList(0, 1));
    List<City> region2 = new ArrayList<>();
    List<City> region3 = new ArrayList<>();
    List<City> region4 = new ArrayList<>(cycle.subList(1, 4));
    List<City> region5 = new ArrayList<>();
    List<City> region6 = new ArrayList<>(cycle.subList(4, 5));
    List<City> region7 = new ArrayList<>(cycle.subList(5, 7));
    List<City> region8 = new ArrayList<>();
    List<City> region9 = new ArrayList<>(cycle.subList(7, 9));
    List<City> region10 = new ArrayList<>(cycle.subList(9, 10));

    assertTrue(region1.contains(city3));
    assertTrue(region2.isEmpty());
    assertTrue(region3.isEmpty());

    assertTrue(region4.contains(city9));
    assertTrue(region4.contains(city2));
    assertTrue(region4.contains(city5));

    assertTrue(region5.isEmpty());

    assertTrue(region6.contains(city4));

    assertTrue(region7.contains(city8));
    assertTrue(region7.contains(city6));

    assertTrue(region8.isEmpty());

    assertTrue(region9.contains(city10));
    assertTrue(region9.contains(city1));

    assertTrue(region10.contains(city7));
  }
}