package edu.louisville.traveler.genetics.selectors;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.genetics.selectors.ParentSelector;

import java.util.List;

public class RandomParentSelector implements ParentSelector {

  @Override
  public LivingTour[] selectFromPopulace(List<LivingTour> population) {
    LivingTour[] parents = new LivingTour[2];
    parents[0] = randomParent(population);
    parents[1] = findRandomMate(population, parents[0]);
    return parents;
  }

  private LivingTour randomParent(List<LivingTour> population) {
    int randomIndex = (int) (Math.random() * population.size());
    return population.get(randomIndex);
  }

  private LivingTour findRandomMate(List<LivingTour> population, LivingTour parentSeekingMate) {
    LivingTour randomMate = randomParent(population);
    while (randomMate == parentSeekingMate) {
      randomMate = randomParent(population);
    }
    return randomMate;
  }
}
