package edu.louisville.traveler.crowds;

import edu.louisville.traveler.maps.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WisdomRequestModel {
  private Map map;
  private int startingPopulation;
  private int populationCap;
  private int totalGenerations;
  private int maxMutationSize;
  private int mutationRate;
  private int regionCount;
  private int crowdSize;
  private int agreementThreshold;
}
