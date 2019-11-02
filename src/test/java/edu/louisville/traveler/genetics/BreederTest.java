package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Edge;
import edu.louisville.traveler.maps.MapHelpers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BreederTest extends BaseGeneticsTest {
  @Test
  public void removesIntersections() {
    LivingTour childWithIntersection = new LivingTour(List.of(
      city5, city8, city10, city2, city4, city5
    ));
    LivingTour childWithoutIntersection = new LivingTour(List.of(
      city5, city2, city10, city8, city4, city5
    ));

    assertEquals(
      childWithoutIntersection,
      Breeder.removeIntersections(childWithIntersection)
    );
  }

  @Test
  public void findIntersections() {
    LivingTour childWithIntersection = new LivingTour(List.of(
      city5, city8, city10, city2, city4, city5
    ));

    assertEquals(
      List.of(new Intersection(new Edge(city5,city8), new Edge(city2, city4))),
      MapHelpers.findIntersections(childWithIntersection)
    );
  }
}