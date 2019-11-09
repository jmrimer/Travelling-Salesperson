package edu.louisville.traveler.hashi;

import java.util.ArrayList;
import java.util.Iterator;
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

  public void solve() throws UnsolvableHashiMap {
//    for all single constraints, build a bridge
    for (Island island : this.hashiMap.getIslands()) {
      buildBridgesFor(island);
//      for (Map.Entry<Direction, List<Integer>> constraintEntry : island.getConstraints().entrySet()) {
//        adjust constraint to both island and neighbor
//      }
    }
//    if impossible to build, throw error

  }

  public void buildBridgesFor(Island island) throws UnsolvableHashiMap {
    Iterator<Map.Entry<Direction, List<Integer>>> constraintIterator = island.getConstraints().entrySet().iterator();

    while (constraintIterator.hasNext()) {
      Map.Entry<Direction, List<Integer>> constraintEntry = constraintIterator.next();

      List<Integer> constraints = constraintEntry.getValue();
      Direction direction = constraintEntry.getKey();
      Island neighbor = island.getNeighbors().get(direction);

      if (constraints.size() == 1) {
        buildBridgesForSingleConstraints(island, constraintEntry, constraintIterator);
        continue;
      }

      if (constraints.contains(1) && !constraints.contains(0)) {
        buildBridge(island, neighbor);
//        constraints.remove(Integer.valueOf(1));
//        neighbor.getConstraints().get(oppositeDirection(direction)).remove(Integer.valueOf(1));
      }
    }
  }

  private void buildBridgesForMandatoryConnection(Island island, Map.Entry<Direction, List<Integer>> constraintEntry, Iterator<Map.Entry<Direction, List<Integer>>> constraintIterator) throws UnsolvableHashiMap {
    Island neighbor = island.getNeighbors().get(constraintEntry.getKey());

    if (constraintEntry.getValue().contains(1) && !constraintEntry.getValue().contains(0)) {
      buildBridge(island, neighbor);
      constraintEntry.getValue().remove(Integer.valueOf(1));
      System.out.println(neighbor.getConstraints());
      neighbor.getConstraints().get(oppositeDirection(constraintEntry.getKey())).remove(Integer.valueOf(1));
    }
  }

  private void buildBridgesForSingleConstraints(Island island, Map.Entry<Direction, List<Integer>> constraintEntry, Iterator<Map.Entry<Direction, List<Integer>>> constraintIterator) throws UnsolvableHashiMap {
    List<Integer> constraint = constraintEntry.getValue();
    Island neighbor = island.getNeighbors().get(constraintEntry.getKey());

    if (constraint.size() == 1) {
      for (int i = 0; i < constraint.get(0); i++) {
        buildBridge(island, neighbor);
      }
//      adjustConstraints(island, constraintEntry, constraintIterator);
    }
  }

  private void adjustConstraints(Island island, Map.Entry<Direction, List<Integer>> constraintEntry, Iterator<Map.Entry<Direction, List<Integer>>> constraintIterator) {
    Island neighbor = island.getNeighbors().get(constraintEntry.getKey());
    constraintIterator.remove();
    neighbor.getConstraints().remove(oppositeDirection(constraintEntry.getKey()));
  }

  private void buildBridge(Island island, Island neighbor) throws UnsolvableHashiMap {
    if (island.getAdjustedPopulation() >= 1 && neighbor.getAdjustedPopulation() >= 1) {
      bridges.add(new Bridge(island, neighbor));
      island.decreaseAdjustedPopulation();
      neighbor.decreaseAdjustedPopulation();
      ConstraintAssigner.assignConstraints(hashiMap);
    } else {
      throw new UnsolvableHashiMap();
    }
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
