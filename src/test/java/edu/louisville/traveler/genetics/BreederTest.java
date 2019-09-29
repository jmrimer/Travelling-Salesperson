package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.RouteWeightCalculator;
import edu.louisville.traveler.maps.Tour;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BreederTest {
//  @Test
//  public void breedsTwoParentsThatAreCompatible() {
//    City city1 = new City(1, 1, 1);
//    City city2 = new City(2, 1, 2);
//    City city3 = new City(3, 1, 3);
//    City city4 = new City(4, 3, 3);
//    City city5 = new City(5, 3, 2);
//    List<City> route1 = List.of(city1, city4, city3, city5, city2, city1);
//    List<City> route2 = List.of(city1, city4, city5, city3, city2, city1);
//    List<City> route3 = List.of(city1, city4, city3, city2, city5, city1);
//    List<City> route4 = List.of(city1, city4, city2, city3, city5, city1);
//    List<City> route5 = List.of(city1, city4, city2, city5, city3, city1);
//    List<List<City>> possibleChildRoutes = List.of(route1, route2, route3, route4, route5);
//    Tour compatibleParent1 = new Tour(route1, RouteWeightCalculator.calculateWeight(route1));
//    Tour compatibleParent2 = new Tour(route2, RouteWeightCalculator.calculateWeight(route2));
//
//    Tour child = Breeder.breed(compatibleParent1, compatibleParent2);
//    assertTrue(
//      possibleChildRoutes.contains(child.getRoute())
//    );
//  }

  @Test
  public void childSurvivesIfMoreFitThanParent() {
    City city1 = new City(1, 1, 1);
    City city2 = new City(2, 1, 2);
    City city3 = new City(3, 1, 3);
    City city4 = new City(4, 3, 3);
    City city5 = new City(5, 3, 2);
    List<City> route1 = List.of(city1, city4, city3, city5, city2, city1);
    List<City> route2 = List.of(city1, city4, city5, city3, city2, city1);
    List<City> route3 = List.of(city1, city4, city5, city3, city2, city1);
    List<City> route4 = List.of(city1, city4, city5, city2, city3, city1);
    List<City> route5 = List.of(city1, city4, city3, city2, city5, city1);
    List<City> route6 = List.of(city1, city4, city2, city3, city5, city1);
    List<City> route7 = List.of(city1, city4, city2, city5, city3, city1);
    List<List<City>> possibleChildRoutes = List.of(route1, route2, route3, route4, route5);
    Tour compatibleParent1 = new Tour(route1, RouteWeightCalculator.calculateWeight(route6));
    Tour compatibleParent2 = new Tour(route2, RouteWeightCalculator.calculateWeight(route7));


    Tour child = Breeder.breed(compatibleParent1, compatibleParent2);
    while (child == null) {
      child = Breeder.breed(compatibleParent1, compatibleParent2);
    }
    assertTrue(
      possibleChildRoutes.contains(child.getRoute())
    );
  }

}