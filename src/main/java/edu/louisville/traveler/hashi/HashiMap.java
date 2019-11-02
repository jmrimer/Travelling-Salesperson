package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class HashiMap {
  private List<Island> islands = new ArrayList<>();
  private int gridSize;

  public HashiMap(int squareGridSize) {
    this.gridSize = squareGridSize;
  }

  public void add(Island island) {
    this.islands.add(island);
  }
}
