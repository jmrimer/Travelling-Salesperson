package edu.louisville.traveler.maps;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouteWeightCalculatorBruteForceTest extends BaseBruteForceTest {
  private RouteWeightCalculator subject;

  @Before
  public void setup() {
    subject = new RouteWeightCalculator();
  }

  @Test
  public void assignsWeightsToSingleRoute() {
    assertEquals(
      20f,
      subject.calculateWeight(route1),
      0.5
    );
  }
}