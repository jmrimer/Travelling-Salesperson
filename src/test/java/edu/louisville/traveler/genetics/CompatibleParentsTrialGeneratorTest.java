//package edu.louisville.traveler.genetics;
//
//import edu.louisville.traveler.maps.Tour;
//import org.junit.jupiter.api.Test;
//
//import java.util.Comparator;
//
//public class CompatibleParentsTrialGeneratorTest extends BaseGeneticsTest {
//  @Test
//  public void returnsTrialThatTracksEachGeneration() {
//    CompatibleParentSelector compatibleParentSelector = new CompatibleParentSelector();
//
//    TrialGenerator trialGenerator = new TrialGenerator(
//      new CompatibleParentsBreeder(compatibleParentSelector, 16, 2),
//      4,
//      30,
//      64
//    );
//    Trial trial = trialGenerator.runTrial();
//    for (int i = 0; i < trial.getGenerations().size(); i++) {
//      System.out.println(trial.getGenerations().get(i).getParentsAliveAtEndOfGeneration().size());
//      trial.getGenerations().get(i).getPopulation().sort(Comparator.comparingDouble(Tour::getWeight));
//      try {
//
//        System.out.println("Best child: " + trial.getGenerations().get(i).getPopulation().get(0).getWeight());
//      } catch (IndexOutOfBoundsException e) {
//
//      }
//
//      System.out.println(trial.getGenerations().get(i).getPopulation().size());
//    }
//  }
//}