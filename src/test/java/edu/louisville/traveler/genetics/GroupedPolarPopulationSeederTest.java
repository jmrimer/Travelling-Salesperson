package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.seeders.GroupedPolarPopulationSeeder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupedPolarPopulationSeederTest extends BaseGeneticsTest {
  @Test
  public void groupsBasedOnR() {
    List<LivingTour> generation = new GroupedPolarPopulationSeeder().seed(1, map10);

    assertEquals(List.of(
      city2,
      city4,
      city8,
      city10,
      city7,
      city3,
      city9,
      city5,
      city6,
      city1,
      city2
      ),
      generation.get(0).getCycle());
  }
}