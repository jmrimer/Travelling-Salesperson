package edu.louisville.traveler.genetics.selectors;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.genetics.selectors.ParentSelector;

import java.util.List;

public class TournamentStyleParentSelector implements ParentSelector {
  @Override
  public LivingTour[] selectFromPopulace(List<LivingTour> population) {
    LivingTour[] parents = new LivingTour[2];
    parents[0] = population.get(0);
    parents[1] = population.get(population.size() - 1);
    return parents;
  }
}
