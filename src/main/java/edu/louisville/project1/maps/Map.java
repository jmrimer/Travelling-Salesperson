package edu.louisville.project1.maps;

import edu.louisville.project1.maps.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Map {
  private List<City> cities;
}
