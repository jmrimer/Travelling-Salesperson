package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.Tour;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompatibleParentsBreeder implements Breeder {
  @Override
  public LivingTour breedParents(LivingTour parent1, LivingTour parent2, int maxGeneSequenceLength) {
    if (compatible(parent1, parent2)) {
      LivingTour child = swapGeneSequence(parent1, parent2);
      if (isMoreFit(child, parent1, parent2)) {
        return child;
      }
    }
    return null;
  }

  @Override
  public Generation breedGeneration(Map map, List<LivingTour> currentParents, int gen) {
    return null;
  }

  static boolean compatible(Tour parent1, Tour parent2) {
    return indexOfSingleCompatibleGenePair(parent1, parent2)[0] > -1;
  }

  private static LivingTour swapGeneSequence(LivingTour parent1, LivingTour parent2) {
    List<City> childRoute = new ArrayList<>();
    List<City> commonGenome = commonSequence(parent1, parent2);
    childRoute.addAll(commonGenome);
    List<City> genePool = new ArrayList<>(parent1.getCycle());
    addRemainingCitiesAtRandom(childRoute, genePool, commonGenome);
    childRoute.add(childRoute.get(0));
    return new LivingTour(childRoute);
  }

  private static boolean isMoreFit(Tour child, Tour parent1, Tour parent2) {
    return child.getWeight() < parent1.getWeight() && child.getWeight() < parent2.getWeight();
  }

  private static int[] indexOfSingleCompatibleGenePair(Tour parent1, Tour parent2) {
    City fromCity = parent1.getCycle().get(0);
    for (int i = 0; i < parent1.getCycle().size() - 1; i++) {
      City toCity = parent1.getCycle().get(i + 1);
      int indexFromOtherParent = otherParentSharesGenePair(parent2, fromCity, toCity);
      if (compatible(indexFromOtherParent)) {
        return new int[]{i, indexFromOtherParent};
      }
      fromCity = toCity;
    }
    return new int[]{-1, -1};
  }

  static List<City> commonSequence(LivingTour parent1, LivingTour parent2) {
    List<City> commonSequence = new ArrayList<>();
    int[] sequenceStarts = indexOfSingleCompatibleGenePair(parent1, parent2);
    if (compatible(sequenceStarts[0])) {
      pairwiseCompare(parent1, parent2, commonSequence, sequenceStarts[0]);
    }
    return commonSequence;
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

  private static int otherParentSharesGenePair(Tour otherParent, City fromCity, City toCity) {
    int indexOfFromCity = otherParent.getCycle().indexOf(fromCity);
    City nextCity = otherParent.getCycle().get(indexOfFromCity + 1);
    return nextCity.equals(toCity) ? indexOfFromCity : -1;
  }

  private static void pairwiseCompare(LivingTour parent1, LivingTour parent2, List<City> commonSequence, int sequenceStart) {
    for (int i = 0; i < parent1.getCycle().size() - 1; i++) {
      int parent1Index = adjustParentIndex(parent1, sequenceStart, i);
      int parent2Index = adjustParentIndex(parent2, sequenceStart, i);
      if (sequenceContinues(parent1, parent2, parent1Index, parent2Index)) {
        commonSequence.add(parent1.getCycle().get(parent1Index));
      } else {
        break;
      }
    }
  }

  private static boolean sequenceContinues(LivingTour parent1, LivingTour parent2, int parent1Index, int parent2Index) {
    return parent1.getCycle().get(parent1Index).equals(parent2.getCycle().get(parent2Index));
  }

  private static int adjustParentIndex(LivingTour parent, int sequenceStart, int i) {
    int parentIndex = sequenceStart + i;
    if (parentIndex >= parent.getCycle().size()) {
      parentIndex -= parent.getCycle().size();
    }
    return parentIndex;
  }

  private static boolean compatible(int sequenceStart) {
    return sequenceStart > -1;
  }

  LivingTour breedParents(LivingTour compatibleParent1, LivingTour compatibleParent2) {
    return this.breedParents(compatibleParent1, compatibleParent2, 0);
  }
}
