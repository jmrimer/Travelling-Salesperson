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

  static class SortByIsland implements Comparator<Island> {
    @Override
    public int compare(Island o1, Island o2) {
      return (o1.getCoordinates().getX() - o2.getCoordinates().getX())
        - (o1.getCoordinates().getY() - o2.getCoordinates().getY());
    }
  }

}
