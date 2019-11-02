package edu.louisville.traveler.crowds;

import edu.louisville.traveler.genetics.BaseGeneticsTest;
import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.genetics.Trial;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Edge;
import edu.louisville.traveler.maps.MapHelpers;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;

public class CrowdWisdomServiceTest extends BaseGeneticsTest {
  long timestamp;
  FileWriter csvWriter;

  @Test
  public void run30() throws IOException {
    for (int i = 0; i < 30; i++) {
      timestamp = System.currentTimeMillis();
      csvWriter = new FileWriter("./testlogs/" + "Wisdom" + timestamp + ".csv", true);

      returnsWisdomWithPopulatedCrowdsAndAggregatedTour();

    }
  }

  @Test
  public void returnsWisdomWithPopulatedCrowdsAndAggregatedTour() {
    totalGenerations = 256;

    WisdomRequestModel wisdomRequestModel = new WisdomRequestModel(
      map100,
      startingParentsCount,
      populationCap,
      totalGenerations,
      maxGeneSequenceLength,
      (int) mutationChance,
      4,
      5,
      90
    );
    CrowdWisdomService crowdWisdomService = new CrowdWisdomService();

    long start = System.currentTimeMillis();

    Wisdom wisdom = crowdWisdomService.wisdomFromRequest(wisdomRequestModel);

    assertEquals(4, wisdom.getCrowds().size());

    for (List<LivingTour> crowd : wisdom.getCrowds().values()) {
      assertEquals(5, crowd.size());
    }
    LivingTour livingTour = wisdom.getAggregatedTour();

    List<City> cycle = livingTour.getCycle();
    assertEquals(101, cycle.size());

    assertThat(wisdom.getAgreedEdges().size(), lessThan(101));

    for (Edge edge : wisdom.getAgreedEdges()) {
      assertTrue(
        MapHelpers.cityBefore(edge.getStart(), cycle).equals(edge.getEnd()) ||
          MapHelpers.cityAfter(edge.getStart(), cycle).equals(edge.getEnd()),
        "Wisdom tour incorrectly separated a wisdom edge for: " + edge);

    }
    System.out.println("aggregated tour weight: " + wisdom.getAggregatedTour().getWeight());

    long end = System.currentTimeMillis();
    long duration = end - start;
    try {
      String row = timestamp + "," + livingTour.getWeight() + "," + wisdom.getAgreedEdges().size() + "," + duration;
      csvWriter.append(row).append("\n");
      csvWriter.flush();
      csvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}