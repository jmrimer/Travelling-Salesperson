package edu.louisville.traveler.hashi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HashiSolver {
  private HashiMap hashiMap;
  private List<Bridge> bridges;


  public HashiSolver(HashiMap hashiMap) throws UnsolvableHashiMap {
    this.hashiMap = hashiMap;
    this.bridges = new ArrayList<>();
    verifyIslandsHaveNeighbors();
    verifyNeighborsMeetPopulationCapacity();
    ConstraintAssigner.assignConstraints(hashiMap);
  }

  private void verifyNeighborsMeetPopulationCapacity() throws UnsolvableHashiMap {
    for (Island island : hashiMap.getIslands()) {
      int totalNeighboringCapacity = 0;

      for (Map.Entry<Direction, Island> neighborEntry : island.getNeighbors().entrySet()) {
        totalNeighboringCapacity += Math.min(neighborEntry.getValue().getPopulation(), 2);
      }

      if (totalNeighboringCapacity < island.getPopulation()) {
        throw new UnsolvableHashiMap();
      }
    }
  }

  private void verifyIslandsHaveNeighbors() throws UnsolvableHashiMap {
    for (Island island : hashiMap.getIslands()) {
      if (island.getNeighbors().size() == 0) {
        throw new UnsolvableHashiMap();
      }
    }
  }

  public void buildBridges() {
//    for all single constraints, build a bridge
    for (Island island : this.hashiMap.getIslands()) {
      for (Map.Entry<Direction, List<Integer>> constraintEntry : island.getConstraints().entrySet()) {
        Island neighbor = island.getNeighbors().get(constraintEntry.getKey());
        bridges.add(new Bridge(island, neighbor));
        island.getConstraints().remove(constraintEntry.getKey());
        neighbor.getConstraints().remove(oppositeDirection(constraintEntry.getKey()));

//        adjust constraint to both island and neighbor
      }
    }
//    if impossible to build, throw error

  }

  private Direction oppositeDirection(Direction direction) {
    switch (direction) {
      case NORTH:
        return Direction.SOUTH;
      case EAST:
        return Direction.WEST;
      case SOUTH:
        return Direction.NORTH;
      default:
        return Direction.EAST;
    }
  }

  public List<Bridge> getBridges() {
    return bridges;
  }
}
