package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrialRequestModel {
  private Map map;
  private int startingPopulation;
  private int populationCap;
  private int totalGenerations;
  private int maxMutationSize;
  private int mutationRate;
}
