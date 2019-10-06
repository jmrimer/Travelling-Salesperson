package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.*;

abstract class Breeder {
  private List<LivingTour> currentChildren;
  private int bornChildren;
  private List<LivingTour> unbredParents = new ArrayList<>();
  private ParentSelector parentSelector;
  private int maxGeneSequenceLength;
  private double mutationChance;
  Map map;

  Breeder(ParentSelector parentSelector, Map map, int maxGeneSequenceLength, double mutationChance) {
    this.parentSelector = parentSelector;
    this.map = map;
    this.maxGeneSequenceLength = maxGeneSequenceLength;
    this.mutationChance = mutationChance;
  }

  abstract void addSequenceToChild(LivingTour child, LivingTour[] parents, int geneSequenceLength);

  public Generation breedGeneration(int gen, List<LivingTour> currentParents) {
    int deceasedParents = setupNewGeneration(currentParents);
    while (parentsAvailableForMating(unbredParents)) {
      breedMates();
    }
    return new Generation(gen, currentParents, currentChildren, bornChildren, deceasedParents);
  }

  void breedMates() {
    City[] emptyRoute = new City[this.map.getCities().size() + 1];
    LivingTour child = new LivingTour(Arrays.asList(emptyRoute));
    LivingTour[] parents = this.parentSelector.selectFromPopulace(this.unbredParents);
    if (parents.length == 2) {

      firstGenes(selectRandomParent(parents), child, map, this.mutationChance);

      while (childRouteIsNotComplete(child)) {
        int geneSequenceLength = this.maxGeneSequenceLength <= map.getCities().size() ?
          (int) (Math.random() * this.maxGeneSequenceLength) + 1 :
          (int) (Math.random() * map.getCities().size()) + 1;
        addSequenceToChild(child, parents, geneSequenceLength);
      }
      this.currentChildren.add(child);
      bornChildren++;
      LivingTour parent1 = parents[0];
      LivingTour parent2 = parents[1];
      parent1.setBred(true);
      parent2.setBred(true);
      unbredParents.remove(parent1);
      unbredParents.remove(parent2);
    }
  }

  List<LivingTour> randomGeneration(int populationCount) {
    List<LivingTour> randomGeneration = new ArrayList<>();
    for (int i = 0; i < populationCount; i++) {
      randomGeneration.add(generateRandomTour(this.map));
    }
    return randomGeneration;
  }

  LivingTour selectRandomParent(LivingTour[] parents) {
    return Math.random() < 0.5 ? parents[0] : parents[1];
  }

  static void mutateSingleGene(Map map, LivingTour child) {
    if (child.getCycle().indexOf(null) > -1) {
      int mutantIndex = (int) (Math.random() * map.getCities().size());
      City mutantGene = map.getCities().get(mutantIndex);
      while (child.getCycle().contains(mutantGene)) {
        mutantGene = map.getCities().get((int) (Math.random() * map.getCities().size()));
      }
      child.getCycle().set(child.getCycle().indexOf(null), mutantGene);
    }
  }

  private int setupNewGeneration(List<LivingTour> currentParents) {
    unbredParents.clear();
    unbredParents.addAll(currentParents);
    unbredParents.sort(Comparator.comparingDouble(LivingTour::getWeight));
    currentChildren = new ArrayList<>();
    bornChildren = 0;
    return 0;
  }

  private void firstGenes(LivingTour parent, LivingTour child, Map map, double mutationChance) {
    boolean shouldInherit = Breeder.shouldInheritGenes(mutationChance);

    int geneSequenceLength = this.maxGeneSequenceLength <= parent.getCycle().size() ?
      (int) (Math.random() * this.maxGeneSequenceLength) :
      (int) (Math.random() * parent.getCycle().size());

    if (geneSequenceLength < 2) {
      geneSequenceLength = 2;
    }

    if (shouldInherit) {
      for (int i = 0; i < geneSequenceLength; i++) {
        child.getCycle().set(i, parent.getCycle().get(i));
      }
    } else {
      for (int i = 0; i < geneSequenceLength; i++) {
        mutateSingleGene(map, child);
      }
    }
    child.getCycle().set(child.getCycle().size() - 1, child.getCycle().get(0));
  }

  private static boolean shouldInheritGenes(double mutationChance) {
    return Math.random() * 100 < mutationChance;
  }

  private boolean childRouteIsNotComplete(LivingTour child) {
    return child.getCycle().contains(null);
  }

  private boolean parentsAvailableForMating(List<LivingTour> unbredParents) {

//    currentParents.removeAll(Collections.singleton(null));
//    int availableParentCount = 0;
//    for (LivingTour parent : currentParents) {
//      if (!parent.didBreed()) {
//        availableParentCount++;
//        if (availableParentCount > 1) {
//          break;
//        }
//      }
//    }
    return unbredParents.size() > 1;
  }

  private LivingTour generateRandomTour(Map map) {
    List<City> remainingCities = new ArrayList<>(map.getCities());
    List<City> route = new ArrayList<>();
    City start = addAndRemoveRandomCity(remainingCities, route);
    addAllRemainingCities(remainingCities, route);
    route.add(start);
    return new LivingTour(route);
  }

  private void addAllRemainingCities(List<City> remainingCities, List<City> route) {
    Iterator<City> cityIterator = remainingCities.iterator();
    while (cityIterator.hasNext()) {
      addAndRemoveRandomCity(remainingCities, route);
    }
  }

  private City addAndRemoveRandomCity(List<City> remainingCities, List<City> route) {
    City city = remainingCities.get((int) (Math.random() * remainingCities.size()));
    route.add(city);
    remainingCities.remove(city);
    return city;
  }
}
