package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface TrialGenerator {
  Trial runTrial();

  void breedParents();

  default void setupNewGeneration(
    List<LivingTour> currentParents,
    List<LivingTour> currentChildren,
    int newParentsPerGeneration,
    Map map
  ) {
    createNewParents(currentParents, newParentsPerGeneration, map);
    childrenBecomeParents(currentChildren, currentParents);
  }

  default void controlPopulation(
    List<LivingTour> currentParents,
    List<LivingTour> currentChildren,
    int populationCap
  ) {
    if (currentParents.size() + currentChildren.size() > populationCap) {
      this.ageParents(currentParents, currentChildren);
      this.killParents(currentParents);
    }
    this.revitalizeFitChildren(currentChildren);
  }

  default LivingTour findRandomMate(List<LivingTour> compatibleParents) {
    int randomIndex = (int) (Math.random() * compatibleParents.size());
    return compatibleParents.get(randomIndex);
  }

  private void childrenBecomeParents(List<LivingTour> currentChildren, List<LivingTour> currentParents) {
    currentParents.addAll(currentChildren);
    currentChildren.clear();
  }

  private void createNewParents(List<LivingTour> currentParents, int newParentsPerGeneration, Map map) {
    for (int i = 0; i < newParentsPerGeneration; i++) {
      currentParents.add(generateRandomTour(map));
    }
  }

  private void revitalizeFitChildren(List<LivingTour> currentChildren) {
    double averageFitness = 0;
    for (LivingTour child : currentChildren) {
      if (child != null) {
        averageFitness += child.getWeight();
      }
    }
    averageFitness /= currentChildren.size();
    for (LivingTour child : currentChildren) {
      if (child != null) {
        if (child.getWeight() < averageFitness) {
          child.revitalize();
        }
      }
    }
  }

  private void ageParents(List<LivingTour> currentParents, List<LivingTour> currentChildren) {
    double averageFitness = 0;
    for (LivingTour child : currentChildren) {
      if (child != null) {
        averageFitness += child.getWeight();
      }
    }
    averageFitness /= currentChildren.size();
    for (LivingTour parent : currentParents) {
      parent.age();
      if (parent.getWeight() < averageFitness) {
        parent.age();
      }
    }
  }

  private void killParents(List<LivingTour> currentParents) {
    currentParents.removeIf(LivingTour::isDead);
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
