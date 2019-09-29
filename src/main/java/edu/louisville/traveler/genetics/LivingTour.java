package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Tour;
import lombok.Data;

import java.util.List;

@Data
class LivingTour extends Tour {
  private int lifespan = 4;
  private int age = 0;

  LivingTour(List<City> route, double weight) {
    super(route, weight);
  }

  boolean isDead() {
    return age > lifespan;
  }

  void age(){
    this.age++;
  }

  void revitalize() {
    this.age--;
  }

  @Override
  public String toString(){
    return "Living Tour: " + "route: " + this.getRoute() + " | age: " + this.getAge();
  }
}
