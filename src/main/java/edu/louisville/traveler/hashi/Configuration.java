package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Configuration {
  private Map<Direction, Integer> connections;

  public Configuration(int north, int east, int south, int west) {
    connections = new HashMap<>();
    connections.put(Direction.NORTH, north);
    connections.put(Direction.EAST, east);
    connections.put(Direction.SOUTH, south);
    connections.put(Direction.WEST, west);
  }
}
