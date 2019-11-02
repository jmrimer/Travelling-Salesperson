package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.crossers.GeneCrosser;
import edu.louisville.traveler.genetics.crossers.OrderedGeneCrosser;
import edu.louisville.traveler.genetics.seeders.RandomPopulationSeeder;
import edu.louisville.traveler.genetics.selectors.MultiMutatedBestParentSelector;
import edu.louisville.traveler.genetics.selectors.ParentSelector;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.Tour;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MultiMutationParentsOrderedCrossoverTrial extends BaseGeneticsTest {
  @Test
  public void multiMutateBestParentsAndOrderCrossoverTrial() {
    Map map = map100;
    int startingParentsCount = 32;
    int totalGenerations = (int) (Math.pow(2, 14));
    int populationCap = 32;
    int maxGeneSequenceLength = 50;
    double mutationChance = 0;
    int parentMutationLength = 4;
    int maxMutations = 4;

    ParentSelector parentSelector = new MultiMutatedBestParentSelector(parentMutationLength, maxMutations);
    GeneCrosser geneCrosser = new OrderedGeneCrosser(maxGeneSequenceLength);
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
