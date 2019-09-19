package edu.louisville.traveler.maps;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class CityAndEdge {
  private City city;
  private Edge edge;
}
