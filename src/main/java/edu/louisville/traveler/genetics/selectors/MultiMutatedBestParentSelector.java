package edu.louisville.traveler.genetics.selectors;

import edu.louisville.traveler.genetics.LivingTour;

import java.util.List;
import java.util.Random;

public class MultiMutatedBestParentSelector extends SingleMutatedBestParentSelector {
  int maxMutations;

  public MultiMutatedBestParentSelector(int parentMutationLength, int maxMutations) {
    super(parentMutationLength);
    this.maxMutations = maxMutations;
  }

  @Override
  public LivingTour[] selectFromPopulace(List<LivingTour> population) {
    LivingTour[] parents = super.selectFromPopulace(population);
    for (LivingTour parent : parents) {
      int randomMutations = new Random().nextInt((maxMutations - 1) + 1);
      for (int i = 0; i < randomMutations; i++) {
        mutate(parent);
      }
    }
    return parents;
  }
}
