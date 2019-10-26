package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.RouteWeightCalculator;
import edu.louisville.traveler.maps.Tour;
import lombok.Data;

import java.util.List;

@Data
public class LivingTour extends Tour {
  private int lifespan = 256;
  private int age = 0;
  private boolean bred = false;

  public LivingTour(List<City> cycle) {
    super(cycle, RouteWeightCalculator.calculateWeight(cycle));
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
