package edu.louisville.traveler.hashi;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class HashiMap {
  private List<Island> islands;
  private int gridSize;

  public HashiMap(int squareGridSize, List<Island> islands) {
    this.islands = new ArrayList<>(islands);
    this.gridSize = squareGridSize;
    assignNeighbors();
    assignConstraints();
  }

  private void assignNeighbors() {
    for (Island island : islands) {
      Map<Direction, Island> neighbors = NeighborChecker.findAllNeighbors(this, island);
      island.setNeighbors(neighbors);
    }
  }

  private void assignConstraints() {
    for (Island island : islands) {
      List<Integer> constraints;
      if (isSinglePopulation(island)) {
        constraints = getConstraintsBasedOnNeighborCount(island);
      } else if (populationOneBelowCapacity(island)) {
        constraints = new ArrayList<>(List.of(1, 2));
      } else if (populationReachedCapacity(island)) {
        constraints = new ArrayList<>(List.of(2));
      } else {
        constraints = new ArrayList<>(List.of(0, 1, 2));
      }
      island.setConstraints(constraints);
    }
  }

  private List<Integer> getConstraintsBasedOnNeighborCount(Island island) {
    List<Integer> constraints = new ArrayList<>();
    if (hasSingleNeighbor(island)) {
      constraints = new ArrayList<>(List.of(1));
    }
    if (hasManyNeighbors(island)) {
      constraints = new ArrayList<>(List.of(0, 1));
    }
    return constraints;
  }

  private boolean populationOneBelowCapacity(Island island) {
    return (island.neighborCount() * 2) - 1 == island.getPopulation();
  }

  private boolean populationReachedCapacity(Island island) {
    return island.neighborCount() * 2 == island.getPopulation();
  }

  private boolean isSinglePopulation(Island island) {
    return island.getPopulation() == 1;
  }

  private boolean hasManyNeighbors(Island island) {
    return island.neighborCount() > 1;
  }

  private boolean hasSingleNeighbor(Island island) {
    return island.neighborCount() == 1;
  }

  public void add(Island island) {
    this.islands.add(island);
  }
}
