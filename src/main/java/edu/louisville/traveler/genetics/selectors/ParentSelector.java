package edu.louisville.traveler.genetics.selectors;

import edu.louisville.traveler.genetics.LivingTour;

import java.util.List;

public interface ParentSelector {
  LivingTour[] selectFromPopulace(List<LivingTour> population);
}
