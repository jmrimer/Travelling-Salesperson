package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.crossers.GeneCrosser;
import edu.louisville.traveler.genetics.selectors.ParentSelector;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Edge;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.MapHelpers;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Breeder {
  private ParentSelector parentSelector;
  private GeneCrosser geneCrosser;
  private Map map;
  private double mutationChance;
  private List<LivingTour> currentChildren;
  private int bornChildren;
  private List<LivingTour> unbredParents = new ArrayList<>();

  public Breeder(
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
    this.currentChildren.add(removeIntersections(child));
    this.bornChildren++;
  }

  public static LivingTour removeIntersections(LivingTour child) {
    List<Intersection> intersections = MapHelpers.findIntersections(child);

    for (Intersection intersection : intersections) {
      removeIntersection(child, intersection);
    }
    return child;
  }

  private static void removeIntersection(LivingTour child, Intersection intersection) {
    Edge edge1 = intersection.getEdge1();
    City start1 = edge1.getStart();
    City end1 = edge1.getEnd();
    Edge edge2 = intersection.getEdge2();
    City start2 = edge2.getStart();
    City end2 = edge2.getEnd();
    List<City> cycle = new ArrayList<>(child.getCycle());

    int indexOfEdge1Start = cycle.indexOf(start1);
    int indexOfEdge1End = cycle.indexOf(end1);
    int indexOfEdge2Start = cycle.indexOf(start2);
    int indexOfEdge2End = cycle.indexOf(end2);

    City connectedCity = connectTo(child, start1, edge2);
    if (connectedCity != null) {
      if (edge2.getStart().equals(connectedCity)) {
        cycle.set(indexOfEdge1End, end2);
        cycle.set(indexOfEdge2End, end1);
      } else {
        cycle.set(indexOfEdge1End, start2);
        cycle.set(indexOfEdge2Start, end1);
      }
      child.setCycle(cycle);
      return;
    }

    connectedCity = connectTo(child, end1, edge2);
    if (connectedCity != null) {
      if (edge2.getStart().equals(connectedCity)) {
        cycle.set(indexOfEdge1Start, end2);
        cycle.set(indexOfEdge2End, start1);
      } else {
        cycle.set(indexOfEdge1Start, start2);
        cycle.set(indexOfEdge2Start, start1);
      }
      child.setCycle(cycle);
    }
  }

  private static City connectTo(LivingTour child, City start, Edge edge) {
    City before = MapHelpers.cityBefore(start, child.getCycle());
    City after = MapHelpers.cityAfter(start, child.getCycle());
    if (edge.contains(before)) {
      return before;
    }
    if (edge.contains(after)) {
      return after;
    }
    return null;
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
