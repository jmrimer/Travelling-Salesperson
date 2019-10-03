package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.Tour;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
class Generation {
  private int generation;
  private List<Tour> parentsAliveAtEndOfGeneration;
  private List<Tour> childrenAliveAtEndOfGeneration;
  private List<Tour> childrenBornThisGeneration;
  private List<Tour> parentsDiedThisGeneration;

}
