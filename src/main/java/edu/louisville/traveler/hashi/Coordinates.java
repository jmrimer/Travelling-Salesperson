package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinates {
  private int x;
  private int y;

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Coordinates)) {
      return false;
    }

    return x == ((Coordinates) o).getX() && y == ((Coordinates) o).getY();
  }
}
