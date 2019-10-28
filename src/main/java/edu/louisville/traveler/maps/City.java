package edu.louisville.traveler.maps;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
  int name;
  double latitude;
  private boolean openLeft = true;
  private boolean openRight = true;

  public City(int name, double latitude, double longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  double longitude;

  @Override
  public String toString() {
    return String.valueOf(this.name);
  }
}
