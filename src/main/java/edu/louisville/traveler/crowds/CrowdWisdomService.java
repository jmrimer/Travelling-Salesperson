package edu.louisville.traveler.crowds;

import edu.louisville.traveler.genetics.Breeder;
import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.genetics.Trial;
import edu.louisville.traveler.genetics.TrialGenerator;
import edu.louisville.traveler.genetics.crossers.GeneCrosser;
import edu.louisville.traveler.genetics.crossers.OrderedGeneCrosser;
import edu.louisville.traveler.genetics.seeders.PopulationSeeder;
import edu.louisville.traveler.genetics.seeders.RandomRegionGroupedPolarPopulationSeeder;
import edu.louisville.traveler.genetics.selectors.ParentSelector;
import edu.louisville.traveler.genetics.selectors.TournamentStyleParentSelector;
import edu.louisville.traveler.maps.Edge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrowdWisdomService {
  public Wisdom wisdomFromRequest(WisdomRequestModel wisdomRequest) {
    List<LivingTour> crowd = new ArrayList<>();

    HashMap<Integer, List<LivingTour>> crowdsByRegion = populateCrowd(wisdomRequest, crowd);
    List<Edge> wisdomEdges = aggregateCrowd(crowd, wisdomRequest.getAgreementThreshold());
    LivingTour aggregatedTour = TourAggregator.aggregate(wisdomRequest.getMap(), wisdomEdges);

    return new Wisdom(crowdsByRegion, aggregatedTour, wisdomEdges);
  }

  private HashMap<Integer, List<LivingTour>> populateCrowd(WisdomRequestModel wisdomRequest, List<LivingTour> crowd) {
    HashMap<Integer, List<LivingTour>> crowdsByRegion = new HashMap<>();

    crowd.addAll(generateCrowdForRegionCount(wisdomRequest, 4, crowdsByRegion));
    crowd.addAll(generateCrowdForRegionCount(wisdomRequest, 8, crowdsByRegion));
    crowd.addAll(generateCrowdForRegionCount(wisdomRequest, 12, crowdsByRegion));
    crowd.addAll(generateCrowdForRegionCount(wisdomRequest, 16, crowdsByRegion));

    return crowdsByRegion;
  }

  private List<LivingTour> generateCrowdForRegionCount(WisdomRequestModel wisdomRequest, int regionCount, HashMap<Integer, List<LivingTour>> crowdsByRegion) {
    List<LivingTour> crowd = new ArrayList<>();
    PopulationSeeder seeder = new RandomRegionGroupedPolarPopulationSeeder(regionCount);
    ParentSelector parentSelector = new TournamentStyleParentSelector();
    GeneCrosser geneCrosser = new OrderedGeneCrosser(wisdomRequest.getMaxMutationSize());

    Breeder breeder = new Breeder(
      parentSelector,
      geneCrosser,
      wisdomRequest.getMap(),
      wisdomRequest.getMutationRate()
    );

    TrialGenerator geneticTrialGenerator = new TrialGenerator(
      wisdomRequest.getMap(),
      seeder,
      breeder,
      wisdomRequest.getStartingPopulation(),
      wisdomRequest.getTotalGenerations(),
      wisdomRequest.getPopulationCap()
    );

    for (int i = 0; i < wisdomRequest.getCrowdSize(); i++) {
      Trial trial = geneticTrialGenerator.runTrial();
      crowd.add(trial.bestChild());
    }

    crowdsByRegion.put(regionCount, crowd);
    return crowd;
  }

  private List<Edge> aggregateCrowd(List<LivingTour> crowd, int agreementThreshold) {
    WisdomCollector wisdomCollector = new WisdomCollector(crowd, agreementThreshold);
    return wisdomCollector.collect();
  }
}
