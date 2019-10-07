package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.Tour;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class BestParentOrderedCrossoverTrialTest extends BaseGeneticsTest {
  @Test
  public void selectsBestParentsAndCrossesOrderedGenes() {
    Map map = map100;
    int startingParentsCount = 64;
    int totalGenerations = (int) (Math.pow(2, 10));
    int populationCap = 64;
    int maxGeneSequenceLength = 50;
    double mutationChance = 0;

    ParentSelector parentSelector = new RandomParentSelector();
    GeneCrosser geneCrosser = new OrderedGeneCrosser(maxGeneSequenceLength);
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
      trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().sort(Comparator.comparingDouble(Tour::getWeight));
      try {
        bestChildren.add(trial.getGenerations().get(i).getChildrenAliveAtEndOfGeneration().get(0));
      } catch (IndexOutOfBoundsException e) {

      }
    }
    bestChildren.sort(Comparator.comparingDouble(Tour::getWeight));
    System.out.println("overall best weight: " + bestChildren.get(0).getWeight());
    System.out.println(("cycle: " + bestChildren.get(0).getCycle()));
    System.out.println("overall best length: " + new HashSet<>(bestChildren.get(0).getCycle()).size());
  }
}
