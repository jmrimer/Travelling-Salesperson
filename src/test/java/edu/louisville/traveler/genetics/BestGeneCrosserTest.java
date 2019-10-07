package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.RouteWeightCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BestGeneCrosserTest extends BaseGeneticsTest {
  private LivingTour parent1;
  private LivingTour parent2;

  @Before
  public void setup() {
    List<City> cycle1 = List.of(city1, city2, city3, city4, city5, city1);
    List<City> cycle2 = List.of(city1, city3, city2, city5, city4, city1);
    parent1 = new LivingTour(cycle1);
    parent2 = new LivingTour(cycle2);
  }

  @Test
  public void findsSuitableParent() {
    List<City> cycle3 = Arrays.asList(city1, city2, null, null, null, city1);
    LivingTour child = new LivingTour(cycle3);


    LivingTour[] suitableParents = new BestGeneCrosser(4)
      .checkForParentSuitabilityOnSequence(
        new LivingTour[]{parent1, parent2},
        child,
        2,
        3
      );

    assertEquals(suitableParents.length, 1);
    assertEquals(suitableParents[0], parent1);
  }

  @Test
  public void selectBestParent() {
    LivingTour bestParent = new BestGeneCrosser(4).selectBestParent(
      new LivingTour[]{parent1, parent2},
      2,
      3
    );
    assertEquals(bestParent, parent2);
  }
}