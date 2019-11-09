package edu.louisville.traveler.crowds;

import edu.louisville.traveler.genetics.BaseGeneticsTest;
import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.Edge;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WisdomCollectorTest extends BaseGeneticsTest {
  @Test
  public void findsAgreedUponConnectionsFromChildren() {
    List<Edge> expectedWisdom = List.of(
      new Edge(city2, city3),
      new Edge(city8, city9)
    );

    LivingTour child1 = new LivingTour(
      List.of(city1, city2, city3, city4, city5, city6, city7, city8, city9, city10, city1)
    );
    LivingTour child2 = new LivingTour(
      List.of(city1, city4, city2, city3, city6, city7, city5, city10, city8, city9, city1)
    );
    LivingTour child3 = new LivingTour(
      List.of(city2, city3, city1, city6, city4, city5, city9, city8, city7, city10, city2)
    );
    LivingTour child4 = new LivingTour(
      List.of(city4, city1, city3, city2, city7, city5, city8, city9, city6, city10, city4)
    );
    LivingTour child5 = new LivingTour(
      List.of(city1, city2, city4, city3, city5, city6, city8, city7, city9, city10, city1)
    );

    List<LivingTour> crowd = List.of(child1, child2, child3, child4, child5);

    assertEquals(
      expectedWisdom,
      new WisdomCollector(crowd, 80).collect()
    );
  }
}