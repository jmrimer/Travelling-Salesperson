package edu.louisville.traveler.genetics;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public
class Trial {
  private List<Generation> generations = new ArrayList<>();

  public void add(Generation generation) {
    this.generations.add(generation);
  }

  public LivingTour bestChild() {
    Generation lastGeneration = this.getGenerations().get(this.getGenerations().size() - 1);
    List<LivingTour> finalPopulation = lastGeneration.getPopulation();
    finalPopulation.sort(Comparator.comparingDouble(LivingTour::getWeight));
    return finalPopulation.get(0);
  }
}
