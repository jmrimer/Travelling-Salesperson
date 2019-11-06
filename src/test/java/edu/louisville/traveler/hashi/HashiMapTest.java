package edu.louisville.traveler.hashi;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class HashiMapTest {


  @Test
  public void constructorAssignsNeighbors() {
    Island island_0_0_3;
    Island island_0_3_2;
    Island island_2_0_2;
    island_0_0_3 = new Island(new Coordinates(0, 0), 3);
    island_0_3_2 = new Island(new Coordinates(0, 3), 2);
    island_2_0_2 = new Island(new Coordinates(2, 0), 2);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        island_0_0_3,
        island_0_3_2,
        island_2_0_2
      )
    );

    assertEquals(
      "HashiMap constructor failed to assign northern neighbor",
      island_0_3_2,
      island_0_0_3.getNeighborNorth()
    );

    assertEquals(
      "HashiMap constructor failed to assign eastern neighbor",
      island_2_0_2,
      island_0_0_3.getNeighborEast()
    );

    assertEquals(
      "HashiMap constructor failed to assign southern neighbor",
      island_0_0_3,
      island_0_3_2.getNeighborSouth()
    );

    assertEquals(
      "HashiMap constructor failed to assign western neighbor",
      island_0_0_3,
      island_2_0_2.getNeighborWest()
    );
  }

  @Test
  public void constructorAssignsConstraints() {
    Island island_0_0_3;
    Island island_0_3_2;
    Island island_2_0_1;
    island_0_3_2 = new Island(new Coordinates(0, 3), 2);
    island_0_0_3 = new Island(new Coordinates(0, 0), 3);
    island_2_0_1 = new Island(new Coordinates(2, 0), 1);

    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        island_0_0_3,
        island_0_3_2,
        island_2_0_1
      )
    );

    assertEquals(List.of(2), island_0_3_2.getConstraintsSouth());
    assertEquals(List.of(1, 2), island_0_0_3.getConstraintsNorth());
    assertEquals(List.of(1, 2), island_0_0_3.getConstraintsEast());
    assertEquals(List.of(1), island_2_0_1.getConstraintsWest());

  }


}