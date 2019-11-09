package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.seeders.PolarPopulationSeeder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PolarPopulationSeederTest extends BaseGeneticsTest {
  @Test
  public void createsGraphInCounterClockwiseOrder() {
    List<LivingTour> seed = new PolarPopulationSeeder().seed(8, map5);
    assertEquals(8, seed.size());

    LivingTour expectedTour = new LivingTour(List.of(city3, city2, city5, city4, city1, city3));

    for (LivingTour livingTour : seed) {
      assertEquals(expectedTour, livingTour);
    }
  }
  
  @Test
  public void createsTwoGroupsBasedOnRInCounterClockwiseOrder() {
    List<LivingTour> seed = new PolarPopulationSeeder().seed(8, map5);
    assertEquals(8, seed.size());

    LivingTour expectedTour = new LivingTour(List.of(city3, city2, city5, city4, city1, city3));

    for (LivingTour livingTour : seed) {
      assertEquals(expectedTour, livingTour);
    }
  }

}