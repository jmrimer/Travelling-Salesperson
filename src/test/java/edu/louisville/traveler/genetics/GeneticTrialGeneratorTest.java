package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.RouteWeightCalculator;
import edu.louisville.traveler.maps.Tour;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class GeneticTrialGeneratorTest extends BaseGeneticsTest {
  //  start with 4 parents
//  add 4 parents each generation
//  run for given generation #
//  expect compatible parents to breed
//  if child is better than parent then it survives
//
  @Test
  public void createsGivenNumberOfParentsEachGeneration() {
    City city1 = new City(1, 1, 1);
    City city2 = new City(2, 2, 2);
    City city3 = new City(3, 3, 3);
    Map map = new Map(List.of(city1, city2, city3));
    List<City> route1 = List.of(city1, city2, city3, city1);
    List<City> route2 = List.of(city1, city3, city2, city1);
    List<City> route3 = List.of(city2, city1, city3, city2);
    List<City> route4 = List.of(city2, city3, city1, city2);
    List<City> route5 = List.of(city3, city1, city2, city3);
    List<City> route6 = List.of(city3, city2, city1, city3);
    List<Tour> possibleParents = List.of(
      new Tour(route1, RouteWeightCalculator.calculateWeight(route1)),
      new Tour(route2, RouteWeightCalculator.calculateWeight(route2)),
      new Tour(route3, RouteWeightCalculator.calculateWeight(route3)),
      new Tour(route4, RouteWeightCalculator.calculateWeight(route4)),
      new Tour(route5, RouteWeightCalculator.calculateWeight(route5)),
      new Tour(route6, RouteWeightCalculator.calculateWeight(route6))
    );
    GeneticTrialGenerator geneticTrialGenerator = new GeneticTrialGenerator(map, 4, 100);
    geneticTrialGenerator.runTrial();
    assertEquals(400, geneticTrialGenerator.getParents().size());
    for (Tour parent : geneticTrialGenerator.getParents()) {
      assertTrue(possibleParents.contains(parent));
    }
    System.out.println(geneticTrialGenerator.getChildren().size());
  }

  @Test
  public void averagePathWeightOfChildrenLessThanParents() {
    GeneticTrialGenerator geneticTrialGenerator = new GeneticTrialGenerator(map100, 4, 10);
    geneticTrialGenerator.runTrial();
    double parentWeightTotal = 0;
    for (Tour parent : geneticTrialGenerator.getParents()) {
      parentWeightTotal += parent.getWeight();
    }
    double parentWeightAverage = parentWeightTotal / geneticTrialGenerator.getParents().size();

    double childrenWeightTotal = 0;
    for (Tour child : geneticTrialGenerator.getChildren()) {
      childrenWeightTotal += child.getWeight();
    }
    double childrenWeightAverage = childrenWeightTotal / geneticTrialGenerator.getChildren().size();
    System.out.println(parentWeightAverage);
    System.out.println(childrenWeightAverage);
    assertThat(
      "children weight average",
      childrenWeightAverage,
      lessThan(parentWeightAverage)
    );
  }

  @Test
  public void returnsTrialThatTracksEachGeneration() {
    GeneticTrialGenerator geneticTrialGenerator = new GeneticTrialGenerator(map100, 4, 10);
    Trial trial = geneticTrialGenerator.runTrial();
    assertEquals(10, trial.getGenerations().size());
    for (int i = 0; i < trial.getGenerations().size(); i++) {
      assertThat(
        "number of parents",
        trial.getGenerations().get(i).getParents().size(),
        greaterThanOrEqualTo((int) 4 * i)
      );
      System.out.println(trial.getGenerations().get(i).getParents().size());
      System.out.println(trial.getGenerations().get(i).getChildren().size());
    }
  }
}