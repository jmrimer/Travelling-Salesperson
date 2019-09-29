package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.RouteWeightCalculator;
import edu.louisville.traveler.maps.Tour;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Breeder {
  static LivingTour breed(Tour parent1, Tour parent2) {
    if (compatible(parent1, parent2)) {
      LivingTour child = swapSingleGene(parent1, parent2);
      if (isMoreFit(child, parent1, parent2)) {
        return child;
      }
    }
    return null;
  }

  private static boolean isMoreFit(Tour child, Tour parent1, Tour parent2) {
    return child.getWeight() < parent1.getWeight() && child.getWeight() < parent2.getWeight();
  }

  private static LivingTour swapSingleGene(Tour parent1, Tour parent2) {
    List<City> childRoute = new ArrayList<>();
    int firstCityIndex = indexOfSingleCompatibleGenePair(parent1, parent2);
    City firstCity = parent1.getRoute().get(firstCityIndex);
    City secondCity = parent1.getRoute().get(firstCityIndex + 1);
    childRoute.add(firstCity);
    childRoute.add(secondCity);
    List<City> genePool = new ArrayList<>(parent1.getRoute());
    addRemainingCitiesAtRandom(childRoute, genePool, firstCity, secondCity);
    childRoute.add(firstCity);
    return new LivingTour(childRoute, RouteWeightCalculator.calculateWeight(childRoute));
  }

  private static void addRemainingCitiesAtRandom(List<City> childRoute, List<City> genePool, City firstCity, City secondCity) {
    genePool.remove(firstCity);
    genePool.remove(firstCity);
    genePool.remove(secondCity);
    Iterator<City> cityIterator = genePool.iterator();
    while (cityIterator.hasNext()) {
      int randomGeneIndex = (int) (Math.random() * genePool.size());
      City insertGene = genePool.get(randomGeneIndex);
      childRoute.add(insertGene);
      genePool.remove(randomGeneIndex);
    }
  }

  private static int indexOfSingleCompatibleGenePair(Tour parent1, Tour parent2) {
    City fromCity = parent1.getRoute().get(0);
    for (int i = 0; i < parent1.getRoute().size() - 1; i++) {
      City toCity = parent1.getRoute().get(i + 1);
      if (otherParentSharesGenePair(parent2, fromCity, toCity)) {
        return i;
      }
      fromCity = toCity;
    }
    return -1;
  }

  private static boolean compatible(Tour parent1, Tour parent2) {
    return indexOfSingleCompatibleGenePair(parent1, parent2) > -1;
  }

  private static boolean otherParentSharesGenePair(Tour otherParent, City fromCity, City toCity) {
    int indexOfFromCity = otherParent.getRoute().indexOf(fromCity);
    City nextCity = otherParent.getRoute().get(indexOfFromCity + 1);
    return nextCity.equals(toCity);
  }
}
