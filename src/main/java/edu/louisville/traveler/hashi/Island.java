package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class Island {
  private Coordinates coordinates;
  private int population;
  private int adjustedPopulation;

  private Map<Direction, Island> neighbors = new HashMap<>();
  private Island neighborNorth;
  private Island neighborEast;
  private Island neighborSouth;
  private Island neighborWest;

  private int bridgeCountNorth;
  private int bridgeCountEast;
  private int bridgeCountSouth;
  private int bridgeCountWest;

  private Map<Direction, List<Integer>> constraints = new HashMap<>();
  private List<Integer> constraintsNorth;
  private List<Integer> constraintsEast;
  private List<Integer> constraintsSouth;
  private List<Integer> constraintsWest;

  public Island(Coordinates coordinates, int population) {
    this.coordinates = coordinates;
    this.population = population;
    this.adjustedPopulation = population;
  }

  public void setPopulation(int population) {
    this.population = population;
    this.adjustedPopulation = population;
  }

  public void decreaseAdjustedPopulation() {
    this.adjustedPopulation--;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Island)) {
      return false;
    }

    return this.coordinates.equals(((Island) o).getCoordinates()) && this.population == ((Island) o).getPopulation();
  }

  @Override
  public String toString() {
    return "Island at (" + this.coordinates.getX() + ", " + this.coordinates.getY() + ") with population: " + this.population;
  }

  public void setConstraint(Direction key, List<Integer> constraints) {
    this.getConstraints().put(key, new ArrayList<>(constraints));
  }
}
