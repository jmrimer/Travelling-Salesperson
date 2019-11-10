package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class ConfigurationGeneratorTest extends BaseHashiTest {
  @Test
  void generatesConfigurations() {
    islandCenter.setPopulation(4);
    islandCenter.setConstraint(Direction.EAST, List.of(0, 1, 2));
    islandCenter.setConstraint(Direction.NORTH, List.of(0, 1, 2));

    Configuration configuration1 = new Configuration(0, 2, 0, 0);
    Configuration configuration2 = new Configuration(1, 1, 0, 0);
    Configuration configuration3 = new Configuration(2, 0, 0, 0);


    List<Configuration> expectedConfigurations = List.of(
      configuration1, configuration2, configuration3
    );

    assertThat(
      ConfigurationGenerator.generate(islandCenter),
      containsInAnyOrder(List.of(
        configuration1, configuration2, configuration3
      ).toArray())
    );
  }
}