package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Tour;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
class Generation {
  private int generation;
  private List<Tour> parents;
  private List<Tour> children;
}
