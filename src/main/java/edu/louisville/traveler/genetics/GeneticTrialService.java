package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import org.springframework.stereotype.Service;


@Service
public class GeneticTrialService {
  int startingParentsCount = 32;
  int populationCap = 32;
  int totalGenerations = (int) (Math.pow(2, 12));
  int maxGeneSequenceLength = 16;
  double mutationChance = 0;

  Trial trialFromMap(Map map) {
    ParentSelector parentSelector = new RandomParentSelector();
    GeneCrosser geneCrosser = new OrderedGeneCrosser(maxGeneSequenceLength);
    Breeder breeder = new Breeder(
      parentSelector,
      geneCrosser,
      map,
      mutationChance
    );

    TrialGenerator geneticTrialGenerator = new TrialGenerator(
      breeder,
      startingParentsCount,
      totalGenerations,
      populationCap
    );

    return geneticTrialGenerator.runTrial();
  }
}
