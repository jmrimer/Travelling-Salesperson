package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class Island {
  private Coordinates coordinates;
  private int population;

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
  }

  public int neighborCount() {
    int neighborCount = 0;
    for (Island neighbor : neighbors.values()) {
      if (neighbor != null) neighborCount++;
    }
    return neighborCount;
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

  public Island onlyNeighbor() {
    for (Island neighbor : neighbors.values()) {
      if (neighbor != null) return neighbor;
    }
    return null;
  }

  public void setConstraints(List<Integer> constraints) {
    for (Direction direction : Direction.values()) {
      if (neighbors.get(direction) != null) {
        this.constraints.put(direction, constraints);
      }
    }
  }
}
