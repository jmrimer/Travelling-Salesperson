package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.Tour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class Breeder {
  static LivingTour breedRandomParents(LivingTour parent1, LivingTour parent2) {
    Map map = new Map(new ArrayList<>(parent1.getCycle()));
    City[] emptyRoute = new City[map.getCities().size()];
    LivingTour child = new LivingTour(Arrays.asList(emptyRoute));

    firstGene(selectRandomParent(parent1, parent2), child);

    while (childRouteIsNotComplete(child)) {
      LivingTour selectedParent = selectRandomParent(parent1, parent2);
      LivingTour backupParent = selectedParent.equals(parent1) ? parent2 : parent1;
      int geneSequenceLength = 16;
      addSequenceToChild(map, child, selectedParent, backupParent, geneSequenceLength);
    }
//    select random parent
//    select random-length gene sequence from parent, add to child

    return isMoreFit(child, parent1, parent2) ? child : null;
  }

  private static void firstGene(LivingTour parent, LivingTour child) {
    int geneSequenceLength = 2;
    for (int i = 0; i < geneSequenceLength; i++) {
      child.getCycle().set(i, parent.getCycle().get(i));
    }
    child.getCycle().set(child.getCycle().size() - 1, parent.getCycle().get(0));
  }

  private static boolean childRouteIsNotComplete(LivingTour child) {
    return child.getCycle().contains(null);
  }

  private static void addSequenceToChild(
    Map map, LivingTour child,
    LivingTour selectedParent,
    LivingTour backupParent,
    int geneSequenceLength
  ) {
    int currentSequenceLength = geneSequenceLength;
    LivingTour breedingParent = selectedParent;
//    start with sequence length
//    if not available, seqlength--
//    if available, check if suitable
//    if not suitable, sequencelength--
//    if sequencelength == 0, switch parent start over
//    if neither parent suitable, mutate using remaining cities

    boolean bred = false;
    int childFirstOpenGene = child.getCycle().indexOf(null);
    boolean triedBothParents = false;

    while (!bred) {
      if (triedBothParents && currentSequenceLength == 0) {
        mutateSingleGene(
          map,
          child
        );
      }

      if (!triedBothParents && currentSequenceLength == 0) {
        breedingParent = backupParent;
        triedBothParents = true;
        currentSequenceLength = geneSequenceLength;
      }

      int childAvailableStartIndex =
        indexOfAvailableOpening(
          child,
          currentSequenceLength,
          childFirstOpenGene
        );

      if (sequenceAvailable(childAvailableStartIndex)) {
        boolean suitable = checkSuitabilityOfParentGenesForChild(
          breedingParent,
          child,
          currentSequenceLength,
          childAvailableStartIndex
        );

        if (suitable) {
          transposeGenesToChild(child, currentSequenceLength, breedingParent, childAvailableStartIndex);
          bred = true;
        } else {
          childFirstOpenGene++;
        }
      } else {
        currentSequenceLength--;
      }
    }
  }

  private static void mutateSingleGene(Map map, LivingTour child) {
    int mutantIndex = (int) (Math.random() * map.getCities().size());
    City mutantGene = map.getCities().get(mutantIndex);
    while (child.getCycle().contains(mutantGene)) {
      mutantGene = map.getCities().get((int) (Math.random() * map.getCities().size()));
    }
    child.getCycle().set(child.getCycle().indexOf(null), mutantGene);
  }

  private static void transposeGenesToChild(LivingTour child, int currentSequenceLength, LivingTour breedingParent, int childAvailableStartIndex) {
    for (int i = 0; i < currentSequenceLength; i++) {
      int geneIndex = childAvailableStartIndex + i;
      City parentGene = breedingParent.getCycle().get(geneIndex);
      child.getCycle().set(geneIndex, parentGene);
    }
  }

  private static boolean checkSuitabilityOfParentGenesForChild(
    LivingTour parent,
    LivingTour child,
    int geneSequenceLength,
    int geneStartingIndex
  ) {
    boolean suitable = true;
    for (int i = 0; i < geneSequenceLength; i++) {
      City parentGene = parent.getCycle().get(geneStartingIndex + i);
      if (child.getCycle().contains(parentGene)) {
        suitable = false;
        break;
      }
    }
    return suitable;
  }

  private static boolean sequenceAvailable(int childAvailableStartIndex) {
    return childAvailableStartIndex > -1;
  }

  private static int indexOfAvailableOpening(LivingTour child, int currentSequenceLength, int childFirstOpenGene) {
    int indexOfOpening = -1;
    for (int i = childFirstOpenGene; i <= child.getCycle().size() - currentSequenceLength; i++) {
      boolean availableSequence = true;
      for (int j = 0; j < currentSequenceLength; j++) {
        if (child.getCycle().get(i + j) != null) {
          availableSequence = false;
          break;
        }
      }
      if (availableSequence) {
        indexOfOpening = i;
        break;
      }
    }
    return indexOfOpening;
  }

  private static void mutateSequence(LivingTour child, LivingTour parent, int childAvailableStartIndex, int sequenceSize) {
  }

  private static boolean isOutOfSuitableSpace(LivingTour child, int childFirstOpenGene, int sequenceSize) {
    return childFirstOpenGene + sequenceSize > child.getCycle().size();
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
    List<City> genePool = new ArrayList<>(parent1.getCycle());
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

  static boolean compatible(Tour parent1, Tour parent2) {
    return indexOfSingleCompatibleGenePair(parent1, parent2)[0] > -1;
  }

  private static int otherParentSharesGenePair(Tour otherParent, City fromCity, City toCity) {
    int indexOfFromCity = otherParent.getCycle().indexOf(fromCity);
    City nextCity = otherParent.getCycle().get(indexOfFromCity + 1);
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
}
