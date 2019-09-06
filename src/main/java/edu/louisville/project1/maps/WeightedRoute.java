package edu.louisville.project1.maps;

import edu.louisville.project1.maps.City;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WeightedRoute {
  List<City> route;
  float weight;
}
