package edu.louisville.traveler.maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class PolarCoordinates implements Comparable<PolarCoordinates> {
  double r;
  double theta;



  @Override
  public int compareTo(@NotNull PolarCoordinates o) {
    if (this.theta != o.getTheta()) {
      return Double.compare(this.theta, o.getTheta());
    }
    return Double.compare(this.r, o.getR());
  }
}
