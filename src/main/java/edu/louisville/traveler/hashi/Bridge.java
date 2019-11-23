package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class Bridge {
  private Island island1;
  private Island island2;

  public boolean contains(Island island) {
    return this.island1.equals(island) || this.island2.equals(island);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Bridge)) {
      return false;
    }

    return this.contains(((Bridge) o).island1) && this.contains(((Bridge) o).island2);
  }

  @Override
  public int hashCode() {
    List<Island> islands = new ArrayList<>(List.of(island1, island2));
    islands.sort(new SortByIsland());
    return Objects.hash(islands);
  }

  public boolean isVertical() {
    return island1.getCoordinates().getX() == island2.getCoordinates().getX();
  }

  public Island getLeft() {
    return island1.getCoordinates().getX() < island2.getCoordinates().getX() ? island1 : island2;
  }

  public Island getRight() {
    return getLeft().equals(island1) ? island2 : island1;
  }

  public Island getTop() {
    return island1.getCoordinates().getY() > island2.getCoordinates().getY() ? island1 : island2;
  }

  public Island getBottom() {
    return getTop().equals(island1) ? island2 : island1;
  }

  public boolean intersects(Bridge bridge) {
    if (this.isVertical()) {
      return checkCrossing(bridge, this);
    } else {
      return checkCrossing(this, bridge);
    }
  }

  private boolean crosses(int x, int y, Island leftIsland, Island rightIsland, Island topIsland, Island bottomIsland) {
    return xWithinRange(x, leftIsland, rightIsland) && yWithinRange(y, topIsland, bottomIsland);
  }

  private boolean yWithinRange(int y, Island topIsland, Island bottomIsland) {
    return bottomIsland.getCoordinates().getY() < y && y < topIsland.getCoordinates().getY();
  }

  private boolean xWithinRange(int x, Island leftIsland, Island rightIsland) {
    return leftIsland.getCoordinates().getX() < x && x < rightIsland.getCoordinates().getX();
  }

  private boolean checkCrossing(Bridge horizontal, Bridge vertical) {
    int x = vertical.getIsland1().getCoordinates().getX();
    int y = horizontal.getIsland1().getCoordinates().getY();
    Island leftIsland = horizontal.getLeft();
    Island rightIsland = horizontal.getRight();
    Island topIsland = vertical.getTop();
    Island bottomIsland = vertical.getBottom();
    return crosses(x, y, leftIsland, rightIsland, topIsland, bottomIsland);
  }

  static class SortByIsland implements Comparator<Island> {
    @Override
    public int compare(Island o1, Island o2) {
      return (o1.getCoordinates().getX() - o2.getCoordinates().getX())
        - (o1.getCoordinates().getY() - o2.getCoordinates().getY());
    }
  }

}
