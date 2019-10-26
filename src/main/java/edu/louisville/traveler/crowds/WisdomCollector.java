package edu.louisville.traveler.crowds;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Edge;
import edu.louisville.traveler.maps.Map;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class WisdomCollector {
  private final List<LivingTour> crowd;
  private final int agreementThreshold;

  public List<Edge> collect() {
    Map map = generateMap();
    HashMap<City, Set<City>> wisdomMatrix = initializeMatrix(map);
    gatherEdgeAppearancesFromCrowd(wisdomMatrix);
    HashMap<Edge, Integer> edgeAgreementCounts = initializeEdgeCounts(wisdomMatrix);
    countEdgeAppearances(edgeAgreementCounts);
    return edgesThatExceedThreshold(edgeAgreementCounts);
  }

  private void countEdgeAppearances(HashMap<Edge, Integer> edgeAgreementCounts) {
    for (java.util.Map.Entry<Edge, Integer> entry : edgeAgreementCounts.entrySet()) {
      entry.setValue(countEdgeAppearanceInCrowd(entry.getKey()));
    }
  }

  private Integer countEdgeAppearanceInCrowd(Edge edge) {
    int appearanceCount = 0;
    for (LivingTour livingTour : crowd) {
      if (tourContainsEdge(edge, livingTour)) {
        appearanceCount++;
      }
    }
    return appearanceCount;
  }

  private boolean tourContainsEdge(Edge edge, LivingTour livingTour) {
    int edgeStartIndex = livingTour.getCycle().indexOf(edge.getStart());
    return getCityBefore(livingTour, edgeStartIndex).equals(edge.getEnd()) ||
      getCityAfter(livingTour, edgeStartIndex).equals(edge.getEnd());
  }

  private List<Edge> edgesThatExceedThreshold(HashMap<Edge, Integer> edgeAgreementCounts) {
    List<Edge> edgesThatExceedThreshold = new ArrayList<>();
    for (java.util.Map.Entry<Edge, Integer> entry : edgeAgreementCounts.entrySet()) {
      if (exceedsThreshold(entry.getValue())) {
        edgesThatExceedThreshold.add(entry.getKey());
      }
    }
    removeRedundantEdges(edgesThatExceedThreshold);
    return edgesThatExceedThreshold;
  }

  private void removeRedundantEdges(List<Edge> edgesThatExceedThreshold) {
    Iterator<Edge> baseEdgeIterator = edgesThatExceedThreshold.iterator();
    while (baseEdgeIterator.hasNext()) {
      Edge baseEdge = baseEdgeIterator.next();
      List<Edge> otherEdges = new ArrayList<>(edgesThatExceedThreshold);
      for (Edge innerEdge : otherEdges) {
        if (innerEdge != baseEdge && innerEdge.equals(baseEdge)) {
          baseEdgeIterator.remove();
        }
      }
    }
  }

  private boolean exceedsThreshold(Integer count) {
    return (100 * count / crowd.size()) >= agreementThreshold;
  }

  private HashMap<Edge, Integer> initializeEdgeCounts(HashMap<City, Set<City>> wisdomMatrix) {
    HashMap<Edge, Integer> edgeCounts = new HashMap<>();
    for (java.util.Map.Entry<City, Set<City>> entry : wisdomMatrix.entrySet()) {
      for (City connectedCity : entry.getValue()) {
        Edge edge = new Edge(entry.getKey(), connectedCity);
        edgeCounts.put(edge, 0);
      }
    }
    return edgeCounts;
  }

  private void gatherEdgeAppearancesFromCrowd(HashMap<City, Set<City>> wisdomMatrix) {
    for (City city : wisdomMatrix.keySet()) {
      for (LivingTour livingTour : crowd) {
        City[] connectedCities = findConnectedCitiesInTour(livingTour, city);
        wisdomMatrix.get(city).add(connectedCities[0]);
        wisdomMatrix.get(city).add(connectedCities[1]);
      }
    }
  }

  private City[] findConnectedCitiesInTour(LivingTour livingTour, City city) {
    City[] connectedCities = new City[2];
    int cityIndex = livingTour.getCycle().indexOf(city);
    connectedCities[0] = getCityBefore(livingTour, cityIndex);
    connectedCities[1] = getCityAfter(livingTour, cityIndex);
    return connectedCities;
  }

  private City getCityAfter(LivingTour livingTour, int cityIndex) {
    if (cityIsAtStartOrEndOfTour(livingTour, cityIndex)) {
      return getSecondCity(livingTour);
    }
    return livingTour.getCycle().get(cityIndex + 1);
  }

  private City getCityBefore(LivingTour livingTour, int cityIndex) {
    if (cityIsAtStartOrEndOfTour(livingTour, cityIndex)) {
      return getSecondToLastCity(livingTour);
    }
    return livingTour.getCycle().get(cityIndex - 1);

  }

  private City getSecondCity(LivingTour livingTour) {
    return livingTour.getCycle().get(1);
  }

  private City getSecondToLastCity(LivingTour livingTour) {
    return livingTour.getCycle().get(livingTour.getCycle().size() - 2);
  }

  private boolean cityIsAtStartOrEndOfTour(LivingTour livingTour, int cityIndex) {
    return cityIndex == 0 || cityIndex == livingTour.getCycle().size() - 1;
  }

  private HashMap<City, Set<City>> initializeMatrix(Map map) {
    HashMap<City, Set<City>> matrix = new HashMap<>();
    for (City city : map.getCities()) {
      matrix.put(city, new HashSet<>());
    }
    return matrix;
  }

  private Map generateMap() {
    List<City> cities = new ArrayList<>(this.crowd.get(0).getCycle());
    cities.remove(cities.size() - 1);
    return new Map(cities);
  }
}
