package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.RouteWeightCalculator;
import edu.louisville.traveler.maps.Tour;
import lombok.Data;

import java.util.List;

@Data
class LivingTour extends Tour {
  private int lifespan = 256;
  private int age = 0;
  private boolean bred = false;

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

  boolean didBreed() {
    return bred;
  }

  @Override
  public String toString(){
    return "Living Tour: " + this.getWeight() + "cycle: " + this.getCycle();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof LivingTour)) {
      return false;
    }

    return this.getCycle().equals(((LivingTour) o).getCycle());
  }}
