package edu.louisville.traveler.genetics.selectors;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.genetics.selectors.BestParentSelector;
import edu.louisville.traveler.maps.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SingleMutatedBestParentSelector extends BestParentSelector {
  int parentMutationLength;

  public SingleMutatedBestParentSelector(int parentMutationLength) {
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

  public void mutate(LivingTour parent) {
    int max = parent.getCycle().size() - parentMutationLength - 1;
    int randomGeneSequenceIndex = new Random().nextInt((max - 1) + 1) + 1;
    List<City> newGeneSequence = new ArrayList<>();

    for (int i = 0; i < this.parentMutationLength; i++) {
      newGeneSequence.add(parent.getCycle().get(randomGeneSequenceIndex + i));
    }

    Collections.shuffle(newGeneSequence);

    for (int i = 0; i < this.parentMutationLength; i++) {
      parent.getCycle().set(randomGeneSequenceIndex + i, newGeneSequence.get(i));
    }
  }
}
