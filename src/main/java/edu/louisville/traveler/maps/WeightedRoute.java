package edu.louisville.traveler.maps;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
class WeightedRoute {
  List<City> route;
  double weight;
}
