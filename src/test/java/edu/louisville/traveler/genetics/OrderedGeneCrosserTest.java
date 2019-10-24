package edu.louisville.traveler.genetics;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

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
  }

}