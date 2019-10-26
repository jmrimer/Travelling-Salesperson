package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.crossers.GeneCrosser;
import edu.louisville.traveler.genetics.crossers.OrderedGeneCrosser;
import edu.louisville.traveler.genetics.seeders.PopulationSeeder;
import edu.louisville.traveler.genetics.seeders.RandomRegionGroupedPolarPopulationSeeder;
import edu.louisville.traveler.genetics.selectors.ParentSelector;
import edu.louisville.traveler.genetics.selectors.TournamentStyleParentSelector;
import org.springframework.stereotype.Service;

@Service
public class GeneticTrialService {
  Trial trialFromMap(TrialRequestModel trialRequest) {
    PopulationSeeder seeder = new RandomRegionGroupedPolarPopulationSeeder();
    ParentSelector parentSelector = new TournamentStyleParentSelector();
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
