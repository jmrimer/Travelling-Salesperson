package edu.louisville.traveler.genetics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
class Generation {
  private int generation;
  private List<LivingTour> population;
  private int births;
}
