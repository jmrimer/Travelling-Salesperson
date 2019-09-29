package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import org.springframework.stereotype.Service;


@Service
public class GeneticTrialService {
  Trial trialFromMap(Map map) {
    TrialGenerator geneticTrialGenerator = new RandomBreedingTrialGenerator(
      map,
      32,
      64,
      256,
      16
    );
    return geneticTrialGenerator.runTrial();
  }
}
