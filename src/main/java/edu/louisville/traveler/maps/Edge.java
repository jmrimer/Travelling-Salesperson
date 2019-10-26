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

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Edge)) {
      return false;
    }

    Edge edge = (Edge) o;

    if (edge.getStart().equals(this.getStart()) && edge.getEnd().equals(this.getEnd())) {
      return true;
    }

    if (edge.getStart().equals(this.getEnd()) && edge.getEnd().equals(this.getStart())) {
      return true;
    }

    return false;
  }

  public boolean contains(City city) {
    return this.getStart().equals(city) || this.getEnd().equals(city);
  }

  public boolean isConnected(Edge edge) {
    return this.contains(edge.getStart()) || this.contains(edge.getEnd());
  }
}
