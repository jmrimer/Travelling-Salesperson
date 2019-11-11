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
  private Map<Direction, List<Integer>> constraints = new HashMap<>();

  public Island(Coordinates coordinates, int population) {
    this.coordinates = coordinates;
    this.population = population;
    this.adjustedPopulation = population;
  }

  public void setPopulation(int population) {
    this.population = population;
    this.adjustedPopulation = population;
  }

  public void setNeighbors(Map<Direction, Island> neighbors) {
    this.neighbors = new HashMap<>(neighbors);
  }

  public void setConstraints(Map<Direction, List<Integer>> constraints) {
    this.constraints = new HashMap<>(constraints);
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

    return
      this.coordinates.equals(((Island) o).getCoordinates())
        && this.population == ((Island) o).getPopulation()
        && this.adjustedPopulation == ((Island) o).getAdjustedPopulation();
  }

  @Override
  public String toString() {
    return
      "Island at (" + this.coordinates.getX() + ", " + this.coordinates.getY()
        + ") with total population: " + this.population
        + " and remaining population " + this.getAdjustedPopulation();
  }

  public void setConstraint(Direction key, List<Integer> constraints) {
    this.getConstraints().put(key, new ArrayList<>(constraints));
  }

  public Island clone() {
    Island clone = new Island(coordinates, population);
    clone.setNeighbors(neighbors);
    clone.setConstraints(constraints);
    clone.setAdjustedPopulation(adjustedPopulation);
    return clone;
  }
}
