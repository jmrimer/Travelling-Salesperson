package edu.louisville.traveler.crowds;

import edu.louisville.traveler.genetics.BaseGeneticsTest;
import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.City;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CrowdSourceServiceTest extends BaseGeneticsTest {
  @Test
  public void returnsWisdomWithPopulatedCrowdsAndAggregatedTour() {
//    make new wisdom request
//    run service
//    check size of crowds
//    check length of tour
    totalGenerations = 64;

    WisdomRequestModel wisdomRequestModel = new WisdomRequestModel(
      map100,
      startingParentsCount,
      populationCap,
      totalGenerations,
      maxGeneSequenceLength,
      (int) mutationChance,
      4,
      20,
      80
    );
    CrowdSourceService crowdSourceService = new CrowdSourceService();
    Wisdom wisdom = crowdSourceService.wisdomFromRequest(wisdomRequestModel);
    assertEquals(4, wisdom.getRegions().size());
    for (List<LivingTour> crowd : wisdom.getCrowds().values()) {
      assertEquals(20, crowd.size());
    }
    LivingTour livingTour = wisdom.getAggregatedTour();
//    for (City city : livingTour.getCycle()) {
//      System.out.println(city + ": " + Collections.frequency(livingTour.getCycle(), city));
//    }
    assertEquals(101, livingTour.getCycle().size());
    System.out.println("aggregated tour weight: " + wisdom.getAggregatedTour().getWeight());
  }
}