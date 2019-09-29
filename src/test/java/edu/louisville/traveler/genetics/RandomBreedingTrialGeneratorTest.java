package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.Tour;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RandomBreedingTrialGeneratorTest extends BaseGeneticsTest {
  @Test
  public void returnsTrialThatDoesNotConsiderCompatibility() {
    Map map = map100;
    int parentsPerGeneration = 64;
    int totalGenerations = 64;
    int populationCap = 256;
    int maxGeneSequenceLength = 16;

    TrialGenerator trialGenerator = new RandomBreedingTrialGenerator(
      map,
      parentsPerGeneration,
      totalGenerations,
      populationCap,
      maxGeneSequenceLength
    );

    Trial trial = trialGenerator.runTrial();
    List<LivingTour> bestChildren = new ArrayList<>();
    for (int i = 0; i < trial.getGenerations().size(); i++) {
      System.out.println("living parents:" + trial.getGenerations().get(i).getParentsAliveAtEndOfGeneration().size());
      System.out.println("dead parents:" + trial.getGenerations().get(i).getParentsDiedThisGeneration().size());
      trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().sort(Comparator.comparingDouble(Tour::getWeight));
      try {
        System.out.println(("births: " + trial.getGenerations().get(i).getChildrenBornThisGeneration().size()));
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

}
