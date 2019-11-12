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
        break;
      }
      if (connections == island.getAdjustedPopulation()) {
        configurations.add(
          new Configuration(northConnection, 0, 0, 0)
        );
        break;
      }

      for (Integer eastConnection : island.getConstraints().get(Direction.EAST)) {
        connections += eastConnection;
        if (connections > island.getAdjustedPopulation()) {
          break;
        }
        if (connections == island.getAdjustedPopulation()) {
          configurations.add(
            new Configuration(northConnection, eastConnection, 0, 0)
          );
          break;
        }

        for (Integer southConnection : island.getConstraints().get(Direction.SOUTH)) {
          connections += southConnection;
          if (connections > island.getAdjustedPopulation()) {
            break;
          }
          if (connections == island.getAdjustedPopulation()) {
            configurations.add(
              new Configuration(northConnection, eastConnection, southConnection, 0)
            );
            break;
          }

          for (Integer westConnection : island.getConstraints().get(Direction.WEST)) {
            connections += westConnection;
            if (connections > island.getAdjustedPopulation()) {
              break;
            }
            if (connections == island.getAdjustedPopulation()) {
              configurations.add(
                new Configuration(northConnection, eastConnection, southConnection, westConnection)
              );
              break;
            }

          }
        }
      }
    }
    return configurations;
  }
}
