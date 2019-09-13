package edu.louisville.traveler.maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Edge {
  private City start;
  private City end;

  public double getWeight() {
    return new MapHelpers().calculateDistance(this.start, this.end);
  }
}
