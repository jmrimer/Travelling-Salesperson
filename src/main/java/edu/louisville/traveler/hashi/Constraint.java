package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Constraint {
  private Island startingIsland;
  private Direction directionToNeighbor;
  private List<Integer> possibleConnections;
}
