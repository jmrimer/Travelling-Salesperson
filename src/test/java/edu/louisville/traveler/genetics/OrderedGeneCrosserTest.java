package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.crossers.OrderedGeneCrosser;
import edu.louisville.traveler.maps.City;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderedGeneCrosserTest extends BaseGeneticsTest {

  @Test
  public void crossingKeepsOrder() {
    LivingTour parent1 = new LivingTour(List.of(
      city1, city2, city3, city4, city5, city6, city7, city8, city9, city10, city1
    ));

    LivingTour parent2 = new LivingTour(List.of(
      city5, city7, city3, city1, city2, city8, city4, city6, city9, city10, city5
    ));

    LivingTour expectedChild = new LivingTour(List.of(
      city4, city5, city6, city7, city3, city1, city2, city8, city9, city10, city4
    ));

    assertEquals(
      expectedChild.getCycle(),
      new OrderedGeneCrosser(16).orderedCrossOnInclusiveIndexRange(new LivingTour[]{parent1, parent2}, 3, 6)
    );

    expectedChild = new LivingTour(List.of(
      city1, city2, city3, city4, city5, city6, city7, city8, city9, city10, city1
    ));

    assertEquals(
      expectedChild.getCycle(),
      new OrderedGeneCrosser(16).orderedCrossOnInclusiveIndexRange(new LivingTour[]{parent1, parent2}, 0, 9)
    );
  }

  @Test
  public void crossoverKeepsProperLengthOnlyOneDuplicateAtStartEnd() {
    LivingTour parent1 = new LivingTour(List.of(
      city1, city2, city3, city4, city5, city6, city7, city8, city9, city10, city1
    ));

    LivingTour parent2 = new LivingTour(List.of(
      city5, city7, city3, city1, city2, city8, city4, city6, city9, city10, city5
    ));

    City[] emptyRoute = new City[map10.getCities().size() + 1];
    LivingTour expectedChild = new LivingTour(Arrays.asList(emptyRoute));

    new OrderedGeneCrosser(16).crossover(expectedChild,new LivingTour[]{parent1, parent2}, map10);
    System.out.println(expectedChild.getCycle());
    assertEquals(
      11,
      expectedChild.getCycle().size()
    );
  }

}