package edu.louisville.traveler.genetics;

import org.springframework.stereotype.Service;

@Service
public class GeneticTrialService {
  Trial trialFromMap(TrialRequestModel trialRequest) {
    PopulationSeeder seeder = new GroupedPolarPopulationSeeder();
    ParentSelector parentSelector = new RandomParentSelector();
    GeneCrosser geneCrosser = new OrderedGeneCrosser(trialRequest.getMaxMutationSize());

    Breeder breeder = new Breeder(
      parentSelector,
      geneCrosser,
      trialRequest.getMap(),
      trialRequest.getMutationRate()
    );

    TrialGenerator geneticTrialGenerator = new TrialGenerator(
      trialRequest.getMap(),
      seeder,
      breeder,
      trialRequest.getStartingPopulation(),
      trialRequest.getTotalGenerations(),
      trialRequest.getPopulationCap()
    );

    return geneticTrialGenerator.runTrial();
  }
}
