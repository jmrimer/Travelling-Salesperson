package edu.louisville.traveler.maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public
class Tour {
  List<City> route;
  double weight;
}
