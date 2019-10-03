package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import org.springframework.stereotype.Service;


@Service
public class GeneticTrialService {
  public Trial trialFromMap(Map map) {
    TrialGenerator geneticTrialGenerator = new CompatibleParentsTrialGenerator(map, 4, 30);
    return geneticTrialGenerator.runTrial();
  }
}