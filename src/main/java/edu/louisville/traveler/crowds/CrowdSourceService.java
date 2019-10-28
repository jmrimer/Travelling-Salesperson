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
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Edge;
import edu.louisville.traveler.maps.Map;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class CrowdSourceService {
  public Wisdom wisdomFromRequest(WisdomRequestModel wisdomRequest) {
    TourAggregator tourAggregator = new TourAggregator();
    List<Map> regions = MapRegionizer.regionize(wisdomRequest.getMap(), wisdomRequest.getRegionCount());
    PopulationSeeder seeder = new RandomRegionGroupedPolarPopulationSeeder();
    ParentSelector parentSelector = new TournamentStyleParentSelector();
    GeneCrosser geneCrosser = new OrderedGeneCrosser(wisdomRequest.getMaxMutationSize());

    HashMap<Map, List<LivingTour>> crowdsByRegion = new HashMap<>();

    for (Map region : regions) {
      crowdsByRegion.put(region, new ArrayList<>());
    }

    for (Map region : regions) {
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
        crowdsByRegion.get(region).add(trial.bestChild());
      }
    }
//    separate map into regions
//    get crowd for every region
//    get wisdom of crowd
//    keep wisdom of crowd for display on front end
//    connect all missing pieces
//    send final living tour as answer
    List<Edge> wisdomEdges = aggregateCrowds(crowdsByRegion, wisdomRequest.getAgreementThreshold());
    LivingTour aggregatedTour = tourAggregator.aggregate(wisdomRequest.getMap(), wisdomEdges);
    return new Wisdom(regions, crowdsByRegion, aggregatedTour, wisdomEdges);
  }

  private List<Edge> aggregateCrowds(HashMap<Map, List<LivingTour>> crowdsByRegion, int agreementThreshold) {
    List<Edge> sharedEdges = new ArrayList<>();
    for (java.util.Map.Entry<Map, List<LivingTour>> entry : crowdsByRegion.entrySet()) {
      WisdomCollector wisdomCollector = new WisdomCollector(entry.getValue(), agreementThreshold);
      sharedEdges.addAll(wisdomCollector.collect());
    }
    return sharedEdges;
  }
}
