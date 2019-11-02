package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.crossers.BestGeneCrosser;
import edu.louisville.traveler.genetics.crossers.GeneCrosser;
import edu.louisville.traveler.genetics.seeders.RandomPopulationSeeder;
import edu.louisville.traveler.genetics.selectors.ParentSelector;
import edu.louisville.traveler.genetics.selectors.RandomParentSelector;
import org.junit.jupiter.api.Test;

public class RandomParentBestCrossoverTrial extends BaseGeneticsTest {
  long timestamp;
  @Test
  public void run30Trials() {
    timestamp = System.currentTimeMillis();
    for (int i = 0; i < 32; i++) {
      selectsRandomParentsAndCrossesBestGenes();
    }
  }

  @Test
  public void selectsRandomParentsAndCrossesBestGenes()  {
    ParentSelector parentSelector = new RandomParentSelector();
    GeneCrosser geneCrosser = new BestGeneCrosser(maxGeneSequenceLength);

    Breeder breeder = new Breeder(
      parentSelector,
      geneCrosser,
      map100,
      mutationChance
    );

    TrialGenerator trialGenerator = new TrialGenerator(
      map100,
      new RandomPopulationSeeder(),
      breeder,
      startingParentsCount,
      totalGenerations,
      populationCap
    );

    long start = System.currentTimeMillis();
    Trial trial = trialGenerator.runTrial();
    long end = System.currentTimeMillis();
    super.logResults(trial, end - start, timestamp);
  }
}
