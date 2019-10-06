package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.Tour;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RandomBreedingTrialGeneratorTest extends BaseGeneticsTest {
  @Test
  public void smallTrial() {
    Map map = new Map(List.of(
      new City(1, 1, 1),
      new City(2, 1, 2),
      new City(3, 1, 3),
      new City(4, 3, 3),
      new City(5, 3, 2)
    ));
    int parentsPerGeneration = 16;
    int totalGenerations = 16;
    int populationCap = 256;
    int maxGeneSequenceLength = 16;

    TrialGenerator trialGenerator = new TrialGenerator(
      new RandomParentsBreeder(maxGeneSequenceLength, 0),
      map,
      parentsPerGeneration,
      totalGenerations,
      populationCap
    );

    Trial trial = trialGenerator.runTrial();
    List<LivingTour> bestChildren = new ArrayList<>();
    for (int i = 0; i < trial.getGenerations().size(); i++) {
      System.out.println("living parents:" + trial.getGenerations().get(i).getParentsAliveAtEndOfGeneration().size());
      System.out.println("dead parents:" + trial.getGenerations().get(i).getParentsDiedThisGeneration());
      trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().sort(Comparator.comparingDouble(Tour::getWeight));
      try {
        System.out.println(("births: " + trial.getGenerations().get(i).getChildrenBornThisGeneration()));
        System.out.println(("living children: " + trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().size()));
        bestChildren.add(trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().get(0));
        System.out.println("Best child: " + trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().get(0).getWeight());
      } catch (IndexOutOfBoundsException e) {

      }
      System.out.println(trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().size());
    }
    bestChildren.sort(Comparator.comparingDouble(Tour::getWeight));
    System.out.println(bestChildren.get(0).getWeight());
  }

  @Test
  public void returnsTrialThatDoesNotConsiderCompatibility() {
    Map map = map100;
    int startingParentsCount = 32;
    int totalGenerations = (int) (Math.pow(2, 12));
    int populationCap = 32;
    int maxGeneSequenceLength = 8;
    double mutationChance = 0;

    TrialGenerator trialGenerator = new TrialGenerator(
      new RandomParentsBreeder(maxGeneSequenceLength, mutationChance),
      map,
      startingParentsCount,
      totalGenerations,
      populationCap
    );

    Trial trial = trialGenerator.runTrial();
    List<LivingTour> bestChildren = new ArrayList<>();
    for (int i = 0; i < trial.getGenerations().size(); i++) {
      System.out.println(("gen: " + i));
      System.out.println("living parents:" + trial.getGenerations().get(i).getParentsAliveAtEndOfGeneration().size());
      System.out.println("dead parents:" + trial.getGenerations().get(i).getParentsDiedThisGeneration());
      trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().sort(Comparator.comparingDouble(Tour::getWeight));
      try {
        System.out.println(("births: " + trial.getGenerations().get(i).getChildrenBornThisGeneration()));
        System.out.println(("living children: " + trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().size()));
        bestChildren.add(trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().get(0));
        System.out.println("Best child: " + trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().get(0).getWeight());
        System.out.println("Best child size: " + trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().get(0).getCycle().size());
      } catch (IndexOutOfBoundsException e) {

      }
      System.out.println(trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().size());
    }
    bestChildren.sort(Comparator.comparingDouble(Tour::getWeight));
    System.out.println(bestChildren.get(0).getWeight());
    System.out.println(bestChildren.get(0).getCycle());
  }

}
