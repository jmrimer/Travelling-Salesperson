package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HashiSolver {
  private HashiMap map;

  public boolean isCorner(Island island) {
    return isLeftOrRight(island) && isTopOrBottom(island);
  }

  private boolean isTopOrBottom(Island island) {
    return island.getCoordinates().getY() == map.getGridSize() - 1 ||
      island.getCoordinates().getY() == 0;

  }

  private boolean isLeftOrRight(Island island) {
    return island.getCoordinates().getX() == map.getGridSize() - 1 ||
      island.getCoordinates().getX() == 0;
  }
}
