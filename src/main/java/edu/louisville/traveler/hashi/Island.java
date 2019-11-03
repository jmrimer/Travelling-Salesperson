package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Island {
  private Coordinates coordinates;
  private int population;
  //  todo create direction count instead of overall bridge count to allow > 2
  private Island neighborNorth;
  private Island neighborEast;
  private Island neighborSouth;
  private Island neighborWest;

  private int bridgeCountNorth;
  private int bridgeCountEast;
  private int bridgeCountSouth;
  private int bridgeCountWest;
}
