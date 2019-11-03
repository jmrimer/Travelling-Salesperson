package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

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
}
