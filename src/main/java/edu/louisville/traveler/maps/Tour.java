package edu.louisville.traveler.maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public
class Tour {
  List<City> cycle;
  double weight;

  public double getWeight() {
    return RouteWeightCalculator.calculateWeight(this.cycle);
  }
}
