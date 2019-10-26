package edu.louisville.traveler.crowds;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
public class Wisdom {
  private List<Map> regions;
  private HashMap<Map, List<LivingTour>> crowds;
  private LivingTour aggregatedTour;

}