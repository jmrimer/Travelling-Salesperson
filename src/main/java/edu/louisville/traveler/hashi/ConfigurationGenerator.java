package edu.louisville.traveler.hashi;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationGenerator {
  public static List<Configuration> generate(Island island) {
    List<Configuration> configurations = new ArrayList<>();
    for (Integer northConnection : island.getConstraints().get(Direction.NORTH)) {
      int connections = 0;
      connections += northConnection;
      if (connections > island.getAdjustedPopulation()) {
        
      }
      if (connections == island.getAdjustedPopulation()) {
        configurations.add(
          new Configuration(northConnection, 0, 0, 0)
        );
      }
    }
//    for each north possibility
//    if == adjustedpop, done
//    if greater than, do not
//    if less than, get each other direction
//
//    for

    return null;
  }
}
