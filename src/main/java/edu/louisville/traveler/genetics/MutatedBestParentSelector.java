package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MutatedBestParentSelector extends BestParentSelector {
  int parentMutationLength;

  MutatedBestParentSelector(int parentMutationLength) {
    super();
    this.parentMutationLength = parentMutationLength;
  }

  @Override
  public LivingTour[] selectFromPopulace(List<LivingTour> population) {
    LivingTour[] parents = super.selectFromPopulace(population);
    for (LivingTour parent : parents) {
      mutate(parent);
    }
    return parents;
  }

  private void mutate(LivingTour parent) {
    int max = parent.getCycle().size() - parentMutationLength - 1;
    int randomGeneSequenceIndex = new Random().nextInt((max - 1) + 1) + 1;
    List<City> newGeneSequence = new ArrayList<>();

    for (int i = 0; i < this.parentMutationLength; i++) {
      newGeneSequence.add(parent.getCycle().get(randomGeneSequenceIndex + i));
    }

    Collections.reverse(newGeneSequence);

    for (int i = 0; i < this.parentMutationLength; i++) {
      parent.getCycle().set(randomGeneSequenceIndex + i, newGeneSequence.get(i));
    }
  }
}
