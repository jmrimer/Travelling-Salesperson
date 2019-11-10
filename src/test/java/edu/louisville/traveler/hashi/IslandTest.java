package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class IslandTest extends BaseHashiTest {
  @Test
  void cloningPreservesValues() {
    Map<Direction, Island> neighbors = new HashMap<>();
    neighbors.put(Direction.NORTH, islandNorth);
    neighbors.put(Direction.EAST, islandEast);
    neighbors.put(Direction.SOUTH, islandSouth);
    neighbors.put(Direction.WEST, islandWest);
    islandCenter.setNeighbors(neighbors);
    islandCenter.setPopulation(5);
    islandCenter.setConstraint(Direction.EAST, List.of(1));
    islandCenter.setConstraint(Direction.NORTH, List.of(2));
    islandCenter.setConstraint(Direction.SOUTH, List.of(3));
    islandCenter.setConstraint(Direction.WEST, List.of(4));

    Island clone = islandCenter.clone();

    assertEquals(4, clone.getConstraints().size());
    assertEquals(4, clone.getNeighbors().size());

    clone.decreaseAdjustedPopulation();
    assertEquals(4, clone.getAdjustedPopulation());
    assertEquals(5, islandCenter.getAdjustedPopulation());

    neighbors.remove(Direction.WEST);
    clone.setNeighbors(neighbors);

    assertEquals(3, clone.getNeighbors().size());
    assertEquals(4, islandCenter.getNeighbors().size());

    islandCenter.setConstraint(Direction.EAST, List.of(9));
    assertEquals(List.of(9), islandCenter.getConstraints().get(Direction.EAST));
    assertEquals(List.of(1), clone.getConstraints().get(Direction.EAST));
  }
}