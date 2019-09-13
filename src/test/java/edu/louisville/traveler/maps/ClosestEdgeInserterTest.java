package edu.louisville.traveler.maps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClosestEdgeInserterTest extends BaseInsertionTest{
  @Test
  public void findsPathByInsertingNextPointClosestToExistingEdge() {
    City city1 = new City(1, 0, 0);
    City city2 = new City(2, 1, 1);
    City city3 = new City(3, 0, 2);
    City city4 = new City(4, 1, 3);
    List<City> cities = List.of(city1, city2, city3, city4);
    List<City> route = new ArrayList<>(cities);
    route.add(city1);
    Tour expectedTour = new Tour(
      route,
      (float) (Math.sqrt(2) * 3 + Math.sqrt(10))
    );

    Tour actualTour = new ClosestEdgeInserter().generateTour(cities);
    assertEquals(
      expectedTour.route,
      actualTour.route
    );
    assertEquals(
      expectedTour.weight,
      actualTour.weight,
      0.0005
    );
  }

  @Test
  public void findsPathOf30Cities() {
    Tour expectedRoute = new Tour(
      List.of(
        city30_1,
        city30_24,
        city30_30,
        city30_15,
        city30_7,
        city30_17,
        city30_23,
        city30_19,
        city30_3,
        city30_21,
        city30_28,
        city30_16,
        city30_11,
        city30_12,
        city30_18,
        city30_10,
        city30_8,
        city30_4,
        city30_29,
        city30_13,
        city30_25,
        city30_6,
        city30_20,
        city30_2,
        city30_14,
        city30_26,
        city30_5,
        city30_9,
        city30_27,
        city30_22,
        city30_1
      ),
      632.2992223290279
    );
    Tour actualTour = new ClosestEdgeInserter().generateTour(thirtyCities);
    assertEquals(
      expectedRoute.route,
      actualTour.route
    );
    assertEquals(
      expectedRoute.weight,
      actualTour.weight,
      0.5
    );
  }

  @Test
  public void findShortestPathFor40Cities() {
    List<City> expectedRoute = List.of(
      city40_1,
      city40_24,
      city40_30,
      city40_15,
      city40_7,
      city40_17,
      city40_40,
      city40_10,
      city40_16,
      city40_11,
      city40_12,
      city40_18,
      city40_8,
      city40_35,
      city40_4,
      city40_33,
      city40_29,
      city40_38,
      city40_31,
      city40_13,
      city40_25,
      city40_6,
      city40_20,
      city40_37,
      city40_39,
      city40_26,
      city40_14,
      city40_2,
      city40_23,
      city40_34,
      city40_36,
      city40_19,
      city40_28,
      city40_32,
      city40_5,
      city40_22,
      city40_9,
      city40_27,
      city40_3,
      city40_21,
      city40_1
    );
    Tour expectedTour = new Tour(
      expectedRoute,
      869.1664896526967
    );
    Tour actualTour = new ClosestEdgeInserter().generateTour(fortyCities);
    assertEquals(
      expectedTour.route,
      actualTour.route
    );
    assertEquals(
      expectedTour.weight,
      actualTour.weight,
      0.5
    );
  }
}