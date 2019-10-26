package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.crossers.GeneCrosser;
import edu.louisville.traveler.genetics.selectors.ParentSelector;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class Breeder {
  private ParentSelector parentSelector;
  private GeneCrosser geneCrosser;
  private Map map;
  private double mutationChance;
  private List<LivingTour> currentChildren;
  private int bornChildren;
  private List<LivingTour> unbredParents = new ArrayList<>();

  Breeder(
    ParentSelector parentSelector,
    GeneCrosser geneCrosser,
    Map map,
    double mutationChance
  ) {
    this.parentSelector = parentSelector;
    this.geneCrosser = geneCrosser;
    this.map = map;
    this.mutationChance = mutationChance;
  }

  Generation breedGeneration(int gen, List<LivingTour> currentParents) {
    setupNewGeneration(currentParents);
    while (parentsAvailableForMating(this.unbredParents)) {
      breedMates();
    }
    List<LivingTour> population = new ArrayList<>();
    population.addAll(currentParents);
    population.addAll(currentChildren);
    return new Generation(gen, population, bornChildren);
  }

  private void breedMates() {
    LivingTour[] parents = this.parentSelector.selectFromPopulace(this.unbredParents);
    createChild(parents);
    markParentsComplete(parents);
  }

  private void createChild(LivingTour[] parents) {
    LivingTour child = setupNullCycle();
    this.geneCrosser.firstGenes(parents, child, this.map, this.mutationChance);
    while (childRouteIsNotComplete(child)) {
      this.geneCrosser.crossover(child, parents, this.map);
    }
    this.currentChildren.add(child);
    this.bornChildren++;
  }

  private void markParentsComplete(LivingTour[] parents) {
    LivingTour parent1 = parents[0];
    LivingTour parent2 = parents[1];
    parent1.setBred(true);
    parent2.setBred(true);
    this.unbredParents.remove(parent1);
    this.unbredParents.remove(parent2);
  }

  @NotNull
  private LivingTour setupNullCycle() {
    City[] emptyRoute = new City[this.map.getCities().size() + 1];
    return new LivingTour(Arrays.asList(emptyRoute));
  }

  private void setupNewGeneration(List<LivingTour> currentParents) {
    unbredParents.clear();
    unbredParents.addAll(currentParents);
    unbredParents.sort(Comparator.comparingDouble(LivingTour::getWeight));
    currentChildren = new ArrayList<>();
    bornChildren = 0;
  }

  private boolean childRouteIsNotComplete(LivingTour child) {
    return child.getCycle().contains(null);
  }

  private boolean parentsAvailableForMating(List<LivingTour> unbredParents) {
    return unbredParents.size() > 1;
  }
}
