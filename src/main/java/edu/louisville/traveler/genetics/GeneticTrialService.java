package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import org.springframework.stereotype.Service;


@Service
public class GeneticTrialService {
  public Trial trialFromMap(Map map) {
    GeneticTrialGenerator geneticTrialGenerator = new GeneticTrialGenerator(map, 4, 10);
    return geneticTrialGenerator.runTrial();
  }
}
