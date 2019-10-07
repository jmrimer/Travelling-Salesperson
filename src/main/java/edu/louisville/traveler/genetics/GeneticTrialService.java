package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import org.springframework.stereotype.Service;


@Service
public class GeneticTrialService {
  Trial trialFromMap(Map map) {
    ParentSelector parentSelector = new RandomParentSelector();
    GeneCrosser geneCrosser = new RandomGeneCrosser(16);
    Breeder breeder = new Breeder(
      parentSelector,
      geneCrosser,
      map,
      0
    );

    TrialGenerator geneticTrialGenerator = new TrialGenerator(
      breeder,
      32,
      64,
      256
    );

    return geneticTrialGenerator.runTrial();
  }
}
