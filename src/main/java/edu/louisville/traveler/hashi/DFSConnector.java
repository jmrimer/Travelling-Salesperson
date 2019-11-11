package edu.louisville.traveler.hashi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static edu.louisville.traveler.hashi.HashiSolutionChecker.puzzleSolved;

public class DFSConnector {
  public static List<Bridge> connect(HashiMap hashiMap) {
    List<Bridge> bridges = new ArrayList<>();
    List<Island> remainingIslands = getRemainingUnconnectedIsland(hashiMap);
    List<Coordinates> coordinates = getCoordinatesOfIslands(remainingIslands); // used to avoid orphan islands from backtracking
    Island island = remainingIslands.get(0);
    bridges.addAll(connectByTrialAndError(island));

    return bridges;
  }

  private static List<Bridge> connectByTrialAndError(Island island) {
    List<Bridge> bridges = new ArrayList<>();

    return bridges;
  }

  private static List<Coordinates> getCoordinatesOfIslands(List<Island> remainingIslands) {
    List<Coordinates> coordinates = new ArrayList<>();

    for (Island island : remainingIslands) {
      coordinates.add(island.getCoordinates());
    }
    return coordinates;
  }

  @NotNull
  private static List<Island> getRemainingUnconnectedIsland(HashiMap hashiMap) {
    return hashiMap.getIslands()
      .stream()
      .filter(island -> island.getAdjustedPopulation() > 0)
      .collect(Collectors.toList());
  }
}
