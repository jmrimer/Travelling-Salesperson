package edu.louisville.traveler.maps;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
  int name;
  double latitude;
  double longitude;

  @Override
  public String toString() {
    return String.valueOf(this.name);
  }
}
