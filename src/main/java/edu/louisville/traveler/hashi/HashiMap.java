package edu.louisville.traveler.hashi;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class HashiMap {
  private List<Island> islands;
  private int gridSize;
  private int bridgesRequired = 0;
  private int startingPopulation;

  public HashiMap(int gridSize, List<Island> islands) {
    this.islands = new ArrayList<>(islands);
    this.gridSize = gridSize;
    startingPopulation = calculatePopulation();
    calculateBridgeCount();
    assignNeighbors();
  }

  public int getRemainingPopulation() {
    int remainingPopulation = 0;
    for (Island island : islands) {
      remainingPopulation += island.getAdjustedPopulation();
    }
    return remainingPopulation;
  }

  private int calculatePopulation() {
    int population = 0;
    for (Island island : islands) {
      population += island.getPopulation();
    }
    return population;
  }

  private void assignNeighbors() {
    for (Island island : islands) {
      Map<Direction, Island> neighbors = NeighborChecker.findAllNeighbors(this, island);
      island.setNeighbors(neighbors);
    }
  }

  public void add(Island island) {
    this.islands.add(island);
  }

  public int getBridgesRequired() {
    calculateBridgeCount();
    return bridgesRequired;
  }

  private void calculateBridgeCount() {
    bridgesRequired = calculatePopulation() / 2;
  }

  public HashiMap clone() {
    List<Island> cloneIslands = new ArrayList<>();
    for (Island island : islands) {
      cloneIslands.add(island.clone());
    }
    HashiMap clone = new HashiMap(
      gridSize,
      cloneIslands
    );
    return clone;
  }

  @Override
  public String toString() {
    return "HashiMap with " + islands.size() + " islands, total population " + calculatePopulation() + ", remaining population " + getRemainingPopulation();
  }
}
