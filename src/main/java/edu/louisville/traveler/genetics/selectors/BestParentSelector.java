package edu.louisville.traveler.genetics.selectors;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.genetics.selectors.ParentSelector;

import java.util.Comparator;
import java.util.List;

public class BestParentSelector implements ParentSelector {
  @Override
  public LivingTour[] selectFromPopulace(List<LivingTour> population) {
    population.sort(Comparator.comparingDouble(LivingTour::getWeight));
    if (population.size() > 1) {
      return new LivingTour[]{population.get(0), population.get(1)};
    }
    return new LivingTour[0];
  }
}
