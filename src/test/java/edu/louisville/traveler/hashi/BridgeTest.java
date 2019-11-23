package edu.louisville.traveler.hashi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BridgeTest extends BaseHashiTest {
  @Test
  void isVertical() {
    Bridge bridge = new Bridge(
      islandNorth,
      islandSouth
    );
    assertTrue(bridge.isVertical());

    bridge = new Bridge(
      islandEast,
      islandWest
    );
    assertFalse(bridge.isVertical());
  }

  @Test
  void getTop() {
    Bridge bridge = new Bridge(
      islandNorth,
      islandSouth
    );
    assertEquals(islandNorth, bridge.getTop());
  }

  @Test
  void getBottom() {
    Bridge bridge = new Bridge(
      islandNorth,
      islandSouth
    );
    assertEquals(islandSouth, bridge.getBottom());
  }

  @Test
  void getLeft() {
    Bridge bridge = new Bridge(
      islandWest,
      islandEast
    );
    assertEquals(islandWest, bridge.getLeft());
  }

  @Test
  void getRight() {
    Bridge bridge = new Bridge(
      islandWest,
      islandEast
    );
    assertEquals(islandEast, bridge.getRight());
  }

  @Test
  void intersects() {
    Bridge bridge1 = new Bridge(islandWest, islandEast);
    Bridge bridge2 = new Bridge(islandNorth, islandSouth);

    assertTrue(bridge1.intersects(bridge2));

  }
}