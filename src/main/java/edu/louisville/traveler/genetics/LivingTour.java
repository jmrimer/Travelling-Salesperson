package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.RouteWeightCalculator;
import edu.louisville.traveler.maps.Tour;
import lombok.Data;

import java.util.List;

@Data
class LivingTour extends Tour {
  private int lifespan = 16;
  private int age = 0;

  LivingTour(List<City> route) {
    super(route, RouteWeightCalculator.calculateWeight(route));
  }

  boolean isDead() {
    return age > lifespan;
  }

  boolean isAlive() {
    return age <= lifespan;
  }

  void age(){
    this.age++;
  }

  void revitalize() {
    this.age--;
  }

  @Override
  public String toString(){
    return "Living Tour: " + "route: " + this.getCycle() + " | age: " + this.getAge();
  }
}
