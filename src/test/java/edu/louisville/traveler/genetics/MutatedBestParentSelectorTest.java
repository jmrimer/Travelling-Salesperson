package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.selectors.SingleMutatedBestParentSelector;
import edu.louisville.traveler.maps.City;
import org.junit.jupiter.api.BeforeEach;;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MutatedBestParentSelectorTest extends BaseGeneticsTest {
  private LivingTour parent1;
  private LivingTour parent2;

@BeforeEach
  public void childSetup() {
    super.setup();
    List<City> cycle1 = Arrays.asList(city1, city2, city3, city4, city5, city1);
    List<City> cycle2 = Arrays.asList(city1, city3, city2, city5, city4, city1);
    parent1 = new LivingTour(cycle1);
    parent2 = new LivingTour(cycle2);
  }

  @Test
  public void selectFromPopulace() {
    List<LivingTour> population = Arrays.asList(parent1, parent2);
    LivingTour[] mutatedParents = new SingleMutatedBestParentSelector(4).selectFromPopulace(population);

    assertEquals(mutatedParents.length, 2);
    assertEquals(mutatedParents[0].getCycle().size(), 6);
    assertEquals(city1, mutatedParents[0].getCycle().get(0));
    assertEquals(city1, mutatedParents[0].getCycle().get(5));
  }
}