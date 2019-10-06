package edu.louisville.traveler.genetics;

import java.util.List;

public interface ParentSelector {
  LivingTour[] selectFromPopulace(List<LivingTour> population);
}
