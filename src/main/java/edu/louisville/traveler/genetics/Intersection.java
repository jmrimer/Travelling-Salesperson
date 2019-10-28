package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Edge;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Intersection {
  private Edge edge1;
  private Edge edge2;
}
