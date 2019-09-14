package edu.louisville.traveler.maps;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityToEdge {
  private Edge edge;
  private City city;
}
