package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.crossers.GeneCrosser;
import edu.louisville.traveler.genetics.crossers.RandomGeneCrosser;
import edu.louisville.traveler.genetics.seeders.RandomPopulationSeeder;
import edu.louisville.traveler.genetics.selectors.ParentSelector;
import edu.louisville.traveler.genetics.selectors.RandomParentSelector;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.Tour;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RandomParentsRandomCrossoverTrial extends BaseGeneticsTest {
//  @Test
//  public void smallTrial() {
//    Map map = new Map(List.of(
//      new City(1, 1, 1),
//      new City(2, 1, 2),
//      new City(3, 1, 3),
//      new City(4, 3, 3),
//      new City(5, 3, 2)
//    ));
//    int parentsPerGeneration = 16;
//    int totalGenerations = 16;
//    int populationCap = 256;
//    int maxGeneSequenceLength = 16;
//
//    TrialGenerator trialGenerator = new TrialGenerator(
//      new RandomParentsBreeder(null, maxGeneSequenceLength, 0),
//      map,
//      parentsPerGeneration,
//      totalGenerations,
//      populationCap
//    );
//
//    Trial trial = trialGenerator.runTrial();
//    List<LivingTour> bestChildren = new ArrayList<>();
//    for (int i = 0; i < trial.getGenerations().size(); i++) {
//      System.out.println("living parents:" + trial.getGenerations().get(i).getParentsAliveAtEndOfGeneration().size());
//      System.out.println("dead parents:" + trial.getGenerations().get(i).getParentsDiedThisGeneration());
//      trial.getGenerations().get(i).getPopulation().sort(Comparator.comparingDouble(Tour::getWeight));
//      try {
//        System.out.println(("births: " + trial.getGenerations().get(i).getChildrenBornThisGeneration()));
//        System.out.println(("living children: " + trial.getGenerations().get(i).getPopulation().size()));
//        bestChildren.add(trial.getGenerations().get(i).getPopulation().get(0));
//        System.out.println("Best child: " + trial.getGenerations().get(i).getPopulation().get(0).getWeight());
//      } catch (IndexOutOfBoundsException e) {
//
//      }
//      System.out.println(trial.getGenerations().get(i).getPopulation().size());
//    }
//    bestChildren.sort(Comparator.comparingDouble(Tour::getWeight));
//    System.out.println(bestChildren.get(0).getWeight());
//  }
//
//  @Test
//  public void returnsTrialThatDoesNotConsiderCompatibility() {
//    Map map = map100;
//    int startingParentsCount = 64;
//    int totalGenerations = (int) (Math.pow(2, 10));
//    int populationCap = 64;
//    int maxGeneSequenceLength = 16;
//    double mutationChance = 0;
//
//    TrialGenerator trialGenerator = new TrialGenerator(
//      new RandomParentsBreeder(null, maxGeneSequenceLength, mutationChance),
//      map,
//      startingParentsCount,
//      totalGenerations,
//      populationCap
//    );
//
//    Trial trial = trialGenerator.runTrial();
//    List<LivingTour> bestChildren = new ArrayList<>();
//    for (int i = 0; i < trial.getGenerations().size(); i++) {
//      System.out.println(("gen: " + i));
//      System.out.println("living parents:" + trial.getGenerations().get(i).getParentsAliveAtEndOfGeneration().size());
//      System.out.println("dead parents:" + trial.getGenerations().get(i).getParentsDiedThisGeneration());
//      trial.getGenerations().get(i).getPopulation().sort(Comparator.comparingDouble(Tour::getWeight));
//      try {
//        System.out.println(("births: " + trial.getGenerations().get(i).getChildrenBornThisGeneration()));
//        System.out.println(("living children: " + trial.getGenerations().get(i).getPopulation().size()));
//        bestChildren.add(trial.getGenerations().get(i).getPopulation().get(0));
//        System.out.println("Best child: " + trial.getGenerations().get(i).getPopulation().get(0).getWeight());
//        System.out.println("Best child size: " + trial.getGenerations().get(i).getPopulation().get(0).getCycle().size());
//      } catch (IndexOutOfBoundsException e) {
//
//      }
//      System.out.println(trial.getGenerations().get(i).getPopulation().size());
//    }
//    bestChildren.sort(Comparator.comparingDouble(Tour::getWeight));
//    System.out.println(bestChildren.get(0).getWeight());
//    System.out.println(bestChildren.get(0).getCycle());
//  }

  @Test
  public void returnsTrialThatDoesNotConsiderCompatibility() {
    Map map = map100;
    int startingParentsCount = 32;
    int totalGenerations = (int) (Math.pow(2, 10));
    int populationCap = 32;
    int maxGeneSequenceLength = 16;
    double mutationChance = 0;

    ParentSelector parentSelector = new RandomParentSelector();
    GeneCrosser geneCrosser = new RandomGeneCrosser(maxGeneSequenceLength);
    Breeder breeder = new Breeder(
      parentSelector,
      geneCrosser,
      map,
      mutationChance
    ) {
    };

    TrialGenerator trialGenerator = new TrialGenerator(
      map100,
      new RandomPopulationSeeder(),
      breeder,
      startingParentsCount,
      totalGenerations,
      populationCap
    );

    Trial trial = trialGenerator.runTrial();
    List<LivingTour> bestChildren = new ArrayList<>();
    for (int i = 0; i < trial.getGenerations().size(); i++) {
      trial.getGenerations().get(i).getPopulation().sort(Comparator.comparingDouble(Tour::getWeight));
      try {
        bestChildren.add(trial.getGenerations().get(i).getPopulation().get(0));
      } catch (IndexOutOfBoundsException e) {

      }
    }
    bestChildren.sort(Comparator.comparingDouble(Tour::getWeight));
    System.out.println("overall best weight: " + bestChildren.get(0).getWeight());
    System.out.println("overall best length: " + bestChildren.get(0).getCycle());
  }

}
