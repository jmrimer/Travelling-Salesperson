package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

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
}
