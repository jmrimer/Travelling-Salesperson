package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.Tour;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class BestParentsBestCrossoverTrial extends  BaseGeneticsTest {
  @Test
  public void runsTrialThatBreedsBestParentsAndCrossesBestGenes() {
    Map map = map100;
    int startingParentsCount = 128;
    int totalGenerations = (int) (Math.pow(2, 10));
    int populationCap = 128;
    int maxGeneSequenceLength = 64;
    double mutationChance = 0;

    ParentSelector parentSelector = new BestParentSelector();
    GeneCrosser geneCrosser = new BestGeneCrosser(maxGeneSequenceLength);
    Breeder breeder = new Breeder(
      parentSelector,
      geneCrosser,
      map,
      mutationChance
    ) {
    };

    TrialGenerator trialGenerator = new TrialGenerator(
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
    System.out.println("overall best length: " + new HashSet<>(bestChildren.get(0).getCycle()).size());

  }
}
