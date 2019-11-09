//package edu.louisville.traveler.genetics;
//
//import edu.louisville.traveler.maps.City;
//import edu.louisville.traveler.maps.Tour;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.lessThan;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CompatibleParentsBreederTest {
////  @Test
////  public void breedsTwoParentsThatAreCompatible() {
////    City city1 = new City(1, 1, 1);
////    City city2 = new City(2, 1, 2);
////    City city3 = new City(3, 1, 3);
////    City city4 = new City(4, 3, 3);
////    City city5 = new City(5, 3, 2);
////    List<City> route1 = List.of(city1, city4, city3, city5, city2, city1);
////    List<City> route2 = List.of(city1, city4, city5, city3, city2, city1);
////    List<City> route3 = List.of(city1, city4, city3, city2, city5, city1);
////    List<City> route4 = List.of(city1, city4, city2, city3, city5, city1);
////    List<City> route5 = List.of(city1, city4, city2, city5, city3, city1);
////    List<List<City>> possibleChildRoutes = List.of(route1, route2, route3, route4, route5);
////    Tour compatibleParent1 = new Tour(route1, RouteWeightCalculator.calculateWeight(route1));
////    Tour compatibleParent2 = new Tour(route2, RouteWeightCalculator.calculateWeight(route2));
////
////    Tour child = Breeder.breed(compatibleParent1, compatibleParent2);
////    assertTrue(
////      possibleChildRoutes.contains(child.getRoute())
////    );
////  }
//
//  @Test
//  public void childSurvivesIfMoreFitThanParent() {
//    City city1 = new City(1, 1, 1);
//    City city2 = new City(2, 1, 2);
//    City city3 = new City(3, 1, 3);
//    City city4 = new City(4, 3, 3);
//    City city5 = new City(5, 3, 2);
//    List<City> route1 = List.of(city1, city4, city3, city5, city2, city1);
//    List<City> route2 = List.of(city1, city4, city5, city3, city2, city1);
//    List<City> route3 = List.of(city1, city4, city5, city3, city2, city1);
//    List<City> route4 = List.of(city1, city4, city5, city2, city3, city1);
//    List<City> route5 = List.of(city1, city4, city3, city2, city5, city1);
//    List<List<City>> possibleChildRoutes = List.of(route1, route2, route3, route4, route5);
//    LivingTour compatibleParent1 = new LivingTour(route1);
//    LivingTour compatibleParent2 = new LivingTour(route2);
//
//    CompatibleParentSelector compatibleParentSelector = new CompatibleParentSelector();
//
//    Tour child = new CompatibleParentsBreeder(compatibleParentSelector, 16, 0)
//      .breedParents(compatibleParent1, compatibleParent2);
//    while (child == null) {
//      child = new CompatibleParentsBreeder(compatibleParentSelector, 16, 0)
//        .breedParents(compatibleParent1, compatibleParent2);
//    }
//    assertTrue(
//      possibleChildRoutes.contains(child.getCycle())
//    );
//  }
//
//  @Test
//  public void keepsLongGenomeSequences() {
//    City city1 = new City(1, 1, 1);
//    City city2 = new City(2, 1, 2);
//    City city3 = new City(3, 1, 3);
//    City city4 = new City(4, 3, 3);
//    City city5 = new City(5, 3, 2);
//    List<City> route1 = List.of(city1, city4, city2, city5, city3, city1);
//    List<City> route2 = List.of(city1, city4, city2, city3, city5, city1);
//    LivingTour parent1 = new LivingTour(route1);
//    LivingTour parent2 = new LivingTour(route2);
//    assertEquals(List.of(city1, city4, city2), new CompatibleParentsBreeder(new CompatibleParentSelector(), 16, 0)
//      .commonSequence(parent1, parent2));
//  }
//
//  @Test
//  public void breedsLivingChild() {
//    City city1 = new City(1, 1, 1);
//    City city2 = new City(2, 1, 2);
//    City city3 = new City(3, 1, 3);
//    City city4 = new City(4, 3, 3);
//    City city5 = new City(5, 3, 2);
//    List<City> longestRoute = List.of(city1, city3, city5, city2, city4, city1); // 11.300563
//    List<City> nextLongestRoute = List.of(city1, city5, city3, city2, city4, city1); // 10.536631
//    LivingTour badParent1 = new LivingTour(longestRoute);
//    LivingTour badParent2 = new LivingTour(nextLongestRoute);
//    CompatibleParentSelector compatibleParentSelector = new CompatibleParentSelector();
//    LivingTour child = new CompatibleParentsBreeder(compatibleParentSelector,16,0).breedParents(badParent1, badParent2);
//    if (child != null) {
//      assertEquals(6, child.getCycle().size());
//      assertThat(
//        "child is better than parent",
//        child.getWeight(),
//        lessThan(badParent2.getWeight())
//      );
//      for (City city : List.of(city1,city2,city3,city4,city5)) {
//        assertTrue("child does not contain city: " + city, child.getCycle().contains(city));
//      }
//    } else {
//      assertNull(child);
//    }
//  }
//}