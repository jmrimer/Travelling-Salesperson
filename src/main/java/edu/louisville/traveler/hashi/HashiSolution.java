package edu.louisville.traveler.hashi;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HashiSolution {
  private HashiMap hashiMap;
  private List<Bridge> bridges = new ArrayList<>();
  private boolean isComplete = false;

  public HashiSolution(HashiMap hashiMap) {
    this.hashiMap = hashiMap;
  }

  public void addBridge(Island island, Island neighbor) {
    this.bridges.add(new Bridge(island, neighbor));
  }
}
