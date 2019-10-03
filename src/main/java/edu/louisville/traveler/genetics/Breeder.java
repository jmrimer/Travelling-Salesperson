package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.Tour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class Breeder {
  static LivingTour breedParents(LivingTour parent1, LivingTour parent2) {
    Map map = new Map(new ArrayList<>(parent1.getRoute()));
//    LivingTour child = new LivingTour(Arrays.asList(new City[map.getCities().size()]));
    LivingTour child = new LivingTour(new ArrayList<>());

    while (childRouteIsNotComplete(map, child)) {
      LivingTour parent = selectRandomParent(parent1, parent2);
      addSequenceToChild(child, parent);
    }
//    select random parent
//    select random-length gene sequence from parent, add to child

    return child;
  }

  private static boolean childRouteIsNotComplete(Map map, LivingTour child) {
    return child.getRoute().contains(null);
  }

  private static void addSequenceToChild(LivingTour child, LivingTour parent) {
    while (true) {
//      choose sequence length
      int sequenceSize = 2;

//      find random opening in child for the sequence
      int childAvailableStartIndex = 0;
      for (int i = 0; i < child.getRoute().size() - sequenceSize; i++) {
        boolean availableSequence = true;
        for (int j = 0; j < sequenceSize; j++) {
          if (child.getRoute().get(i + j) != null) {
            availableSequence = false;
            break;
          }
        }
        if (availableSequence) {
          childAvailableStartIndex = i;
          break;
        }
      }

//      transpose random parent genome of length
      for (int i = 0; i < sequenceSize; i++) {
        child
          .getRoute()
          .add(
            childAvailableStartIndex + i,
            parent.getRoute().get(childAvailableStartIndex + i)
          );
      }
//        IF the cities in the sequence have not been used yet
//        AND the
    }
  }

  private static LivingTour selectRandomParent(LivingTour parent1, LivingTour parent2) {
    return Math.random() < 0.5 ? parent1 : parent2;
  }

  static LivingTour breedCompatibleParents(LivingTour parent1, LivingTour parent2) {
    if (compatible(parent1, parent2)) {
      LivingTour child = swapGeneSequence(parent1, parent2);
      if (isMoreFit(child, parent1, parent2)) {
        return child;
      }
    }
    return null;
  }

  private static boolean isMoreFit(Tour child, Tour parent1, Tour parent2) {
    return child.getWeight() < parent1.getWeight() && child.getWeight() < parent2.getWeight();
  }

  private static LivingTour swapGeneSequence(LivingTour parent1, LivingTour parent2) {
    List<City> childRoute = new ArrayList<>();
    List<City> commonGenome = commonSequence(parent1, parent2);
    childRoute.addAll(commonGenome);
    List<City> genePool = new ArrayList<>(parent1.getRoute());
    addRemainingCitiesAtRandom(childRoute, genePool, commonGenome);
    childRoute.add(childRoute.get(0));
    return new LivingTour(childRoute);
  }

  private static void addRemainingCitiesAtRandom(List<City> childRoute, List<City> genePool, List<City> citiesAlreadyAdded) {
    genePool.removeAll(citiesAlreadyAdded);
    Iterator<City> cityIterator = genePool.iterator();
    while (cityIterator.hasNext()) {
      int randomGeneIndex = (int) (Math.random() * genePool.size());
      City insertGene = genePool.get(randomGeneIndex);
      childRoute.add(insertGene);
      genePool.remove(randomGeneIndex);
    }
  }

  private static int[] indexOfSingleCompatibleGenePair(Tour parent1, Tour parent2) {
    City fromCity = parent1.getRoute().get(0);
    for (int i = 0; i < parent1.getRoute().size() - 1; i++) {
      City toCity = parent1.getRoute().get(i + 1);
      int indexFromOtherParent = otherParentSharesGenePair(parent2, fromCity, toCity);
      if (compatible(indexFromOtherParent)) {
        return new int[]{i, indexFromOtherParent};
      }
      fromCity = toCity;
    }
    return new int[]{-1, -1};
  }

  static boolean compatible(Tour parent1, Tour parent2) {
    return indexOfSingleCompatibleGenePair(parent1, parent2)[0] > -1;
  }

  private static int otherParentSharesGenePair(Tour otherParent, City fromCity, City toCity) {
    int indexOfFromCity = otherParent.getRoute().indexOf(fromCity);
    City nextCity = otherParent.getRoute().get(indexOfFromCity + 1);
    return nextCity.equals(toCity) ? indexOfFromCity : -1;
  }

  static List<City> commonSequence(LivingTour parent1, LivingTour parent2) {
    List<City> commonSequence = new ArrayList<>();
    int[] sequenceStarts = indexOfSingleCompatibleGenePair(parent1, parent2);
    if (compatible(sequenceStarts[0])) {
      pairwiseCompare(parent1, parent2, commonSequence, sequenceStarts[0]);
    }
    return commonSequence;
  }

  private static void pairwiseCompare(LivingTour parent1, LivingTour parent2, List<City> commonSequence, int sequenceStart) {
    for (int i = 0; i < parent1.getRoute().size() - 1; i++) {
      int parent1Index = adjustParentIndex(parent1, sequenceStart, i);
      int parent2Index = adjustParentIndex(parent2, sequenceStart, i);
      if (sequenceContinues(parent1, parent2, parent1Index, parent2Index)) {
        commonSequence.add(parent1.getRoute().get(parent1Index));
      } else {
        break;
      }
    }
  }

  private static boolean sequenceContinues(LivingTour parent1, LivingTour parent2, int parent1Index, int parent2Index) {
    return parent1.getRoute().get(parent1Index).equals(parent2.getRoute().get(parent2Index));
  }

  private static int adjustParentIndex(LivingTour parent, int sequenceStart, int i) {
    int parentIndex = sequenceStart + i;
    if (parentIndex >= parent.getRoute().size()) {
      parentIndex -= parent.getRoute().size();
    }
    return parentIndex;
  }

  private static boolean compatible(int sequenceStart) {
    return sequenceStart > -1;
  }
}
