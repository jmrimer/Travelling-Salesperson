package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Tour;
import org.junit.Test;

import java.util.Comparator;

public class RandomBreedingTrialGeneratorTest extends BaseGeneticsTest {
  @Test
  public void returnsTrialThatDoesNotConsiderCompatibility() {
    TrialGenerator trialGenerator = new RandomBreedingTrialGenerator(map100, 16, 64, 10);
    Trial trial = trialGenerator.runTrial();
    for (int i = 0; i < trial.getGenerations().size(); i++) {
      System.out.println("living parents:" + trial.getGenerations().get(i).getParentsAliveAtEndOfGeneration().size());
      trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().sort(Comparator.comparingDouble(Tour::getWeight));
      try {
        System.out.println(("living children: " + trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().size()));
        System.out.println("Best child: " + trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().get(0).getWeight());
      } catch (IndexOutOfBoundsException e) {

      }

      System.out.println(trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().size());
    }
  }

}
