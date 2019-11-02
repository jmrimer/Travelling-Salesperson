package edu.louisville.traveler.maps;

import org.junit.jupiter.api.BeforeEach;;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteWeightCalculatorBruteForceTest extends BaseBruteForceTest {
  private RouteWeightCalculator subject;

@BeforeEach
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