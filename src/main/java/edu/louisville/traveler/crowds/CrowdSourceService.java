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
    LivingTour aggregatedTour = completeTourFromEdges(wisdomRequest.getMap(), wisdomEdges);
    return new Wisdom(regions, crowdsByRegion, aggregatedTour);
  }

  private LivingTour completeTourFromEdges(Map map, List<Edge> edges) {
//    flatten edges into city list
    List<City> route = flattenEdgesIntoList(edges);
    for (City city : route) {
      if (Collections.frequency(route, city) > 1) {
        System.out.println(city + ": duped");
      }
    }


    for (City city : map.getCities()) {
      if (!route.contains(city)) {
        route.add(city);
      }
    }
    route.add(route.get(0));
//    get all unconnected cities
//    connect each city to the closest each (see if heuristic can help)
//    make
    return new LivingTour(route);
  }

  private List<City> flattenEdgesIntoList(List<Edge> edges) {
    List<City> cities = new ArrayList<>();
    for (Edge edge : edges) {
      if (alreadyContainsEdgeCities(cities, edge)) {
        continue; // TODO bring these two pieces together
      }

      if (containsNeitherEdgeCity(cities, edge)) {
        addBothCities(cities, edge);
      }

      if (containsStartNotEnd(cities, edge)) {
        insertEndCityAfterStart(cities, edge);
      }

      if (containsEndNotStart(cities, edge)) {
        insertStartCityBeforeEnd(cities, edge);
      }
    }
    return cities;
  }

  private void insertEndCityAfterStart(List<City> cities, Edge edge) {
    int indexOfSharedCity = cities.indexOf(edge.getStart());
    if (indexOfSharedCity == cities.size() - 1) {
      cities.add(edge.getEnd());
    } else {
      cities.add(indexOfSharedCity + 1, edge.getEnd());
    }
  }

  private void insertStartCityBeforeEnd(List<City> cities, Edge edge) {
    int indexOfSharedCity = cities.indexOf(edge.getEnd());
    cities.add(indexOfSharedCity, edge.getStart());
  }

  private void addBothCities(List<City> cities, Edge edge) {
    cities.add(edge.getStart());
    cities.add(edge.getEnd());
  }

  private boolean containsEndNotStart(List<City> cities, Edge edge) {
    return cities.contains(edge.getEnd()) && !cities.contains(edge.getStart());
  }

  private boolean containsStartNotEnd(List<City> cities, Edge edge) {
    return cities.contains(edge.getStart()) && !cities.contains(edge.getEnd());
  }

  private boolean containsNeitherEdgeCity(List<City> cities, Edge edge) {
    return !cities.contains(edge.getStart()) && !cities.contains(edge.getEnd());
  }

  private boolean alreadyContainsEdgeCities(List<City> cities, Edge edge) {
    return cities.contains(edge.getStart()) && cities.contains(edge.getEnd());
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
