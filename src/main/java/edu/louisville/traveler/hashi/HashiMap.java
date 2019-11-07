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
      if (island.neighborCount() == 1) {
        List<Integer> constraints = new ArrayList<>();
        if (island.getPopulation() == 1) {
          constraints.add(1);
        } else {
          constraints.add(2);
        }
        island.setConstraints(constraints);
      }

      if (island.neighborCount() == 2 && island.getPopulation() == 1) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1));
        island.setConstraints(constraints);
      }

      if (island.neighborCount() == 2 && island.getPopulation() == 2) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1, 2));
        island.setConstraints(constraints);
      }

      if (island.neighborCount() == 2 && island.getPopulation() == 3) {
        List<Integer> constraints = new ArrayList<>(List.of(1, 2));
        island.setConstraints(constraints);
      }

      if (island.neighborCount() == 2 && island.getPopulation() == 4) {
        List<Integer> constraints = new ArrayList<>(List.of(2));
        island.setConstraints(constraints);
      }

      if (island.neighborCount() == 3 && island.getPopulation() == 1) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1));
        island.setConstraints(constraints);
      }

      if (island.neighborCount() == 3 && island.getPopulation() == 2) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1, 2));
        island.setConstraints(constraints);
      }

      if (island.neighborCount() == 3 && island.getPopulation() == 3) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1, 2));
        island.setConstraints(constraints);
      }

      if (island.neighborCount() == 3 && island.getPopulation() == 4) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1, 2));
        island.setConstraints(constraints);
      }

      if (island.neighborCount() == 3 && island.getPopulation() == 5) {
        List<Integer> constraints = new ArrayList<>(List.of(1, 2));
        island.setConstraints(constraints);
      }

      if (island.neighborCount() == 3 && island.getPopulation() == 6) {
        List<Integer> constraints = new ArrayList<>(List.of(2));
        island.setConstraints(constraints);
      }

      if (island.neighborCount() == 4 && island.getPopulation() == 1) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1));
        island.setConstraints(constraints);
      }
      if (island.neighborCount() == 4 && island.getPopulation() == 2) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1, 2));
        island.setConstraints(constraints);
      }
      if (island.neighborCount() == 4 && island.getPopulation() == 3) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1, 2));
        island.setConstraints(constraints);
      }
      if (island.neighborCount() == 4 && island.getPopulation() == 4) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1, 2));
        island.setConstraints(constraints);
      }
      if (island.neighborCount() == 4 && island.getPopulation() == 5) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1, 2));
        island.setConstraints(constraints);
      }
      if (island.neighborCount() == 4 && island.getPopulation() == 6) {
        List<Integer> constraints = new ArrayList<>(List.of(0, 1, 2));
        island.setConstraints(constraints);
      }
      if (island.neighborCount() == 4 && island.getPopulation() == 7) {
        List<Integer> constraints = new ArrayList<>(List.of(1, 2));
        island.setConstraints(constraints);
      }
      if (island.neighborCount() == 4 && island.getPopulation() == 8) {
        List<Integer> constraints = new ArrayList<>(List.of(2));
        island.setConstraints(constraints);
      }
    }
  }

  public void add(Island island) {
    this.islands.add(island);
  }
}
