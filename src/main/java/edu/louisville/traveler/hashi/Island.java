package edu.louisville.traveler.hashi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Island {
  private Coordinates coordinates;
  private int population;
}
