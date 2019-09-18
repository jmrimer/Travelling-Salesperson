
package edu.louisville.traveler.maps;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TravelingSalespersonProblemSolverBruteForceTest extends BaseBruteForceTest {
  private TravelingSalespersonProblemSolver subject;

  @Before
  public void setup() {
    subject = new TravelingSalespersonProblemSolver();
  }

  @Test
  public void returnsBestRoute() {
    assertEquals(
      new Tour(List.of(city1, city2, city3, city4, city1), 20),
      subject.discoverBestTour(List.of(city1, city2, city3, city4))
    );
  }
}
