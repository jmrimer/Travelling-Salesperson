package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Island {
  private Coordinates coordinates;
  private int population;

  private Island neighborNorth;
  private Island neighborEast;
  private Island neighborSouth;
  private Island neighborWest;

  private int bridgeCountNorth;
  private int bridgeCountEast;
  private int bridgeCountSouth;
  private int bridgeCountWest;

  private List<Integer> constraintsNorth;
  private List<Integer> constraintsEast;
  private List<Integer> constraintsSouth;
  private List<Integer> constraintsWest;

  public Island(Coordinates coordinates, int population) {
    this.coordinates = coordinates;
    this.population = population;
  }

  public void acceptNeighbors(Map<Direction, Island> neighbors) {
    for (Map.Entry<Direction, Island> entry : neighbors.entrySet()) {
      switch (entry.getKey()) {
        case NORTH:
          this.neighborNorth = entry.getValue();
          break;
        case EAST:
          this.neighborEast = entry.getValue();
          break;
        case SOUTH:
          this.neighborSouth = entry.getValue();
          break;
        case WEST:
          this.neighborWest = entry.getValue();
          break;
      }
    }
  }

  public int neighborCount() {
    int neighborCount = 0;
    if (this.neighborNorth != null) neighborCount++;
    if (this.neighborEast != null) neighborCount++;
    if (this.neighborSouth != null) neighborCount++;
    if (this.neighborWest != null) neighborCount++;
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
    if (this.neighborNorth != null) return neighborNorth;
    if (this.neighborEast != null) return neighborEast;
    if (this.neighborSouth != null) return neighborSouth;
    if (this.neighborWest != null) return neighborWest;
    return null;
  }

  public List<Island> getAllNeighbors() {
    List<Island> neighbors = new ArrayList<>();
    neighbors.add(neighborNorth);
    neighbors.add(neighborEast);
    neighbors.add(neighborSouth);
    neighbors.add(neighborWest);
    neighbors.removeIf(Objects::isNull);
    return neighbors;
  }

  public void setConstraints(List<Integer> constraints) {
    if (neighborNorth != null) {
      constraintsNorth = constraints;
    }
    if (neighborEast != null) {
      constraintsEast = constraints;
    }
    if (neighborSouth != null) {
      constraintsSouth = constraints;
    }
    if (neighborWest != null) {
      constraintsWest = constraints;
    }
  }
}
