package edu.louisville.traveler.genetics;

import org.junit.Test;

public class BestParentsBestCrossoverTrial extends BaseGeneticsTest {
  long timestamp;

  @Test
  public void run30Trials() {
    timestamp = System.currentTimeMillis();
    for (int i = 0; i < 32; i++) {
      selectsBestParentsAndBestGenes();
    }
  }

  @Test
  public void selectsBestParentsAndBestGenes()  {
    ParentSelector parentSelector = new BestParentSelector();
    GeneCrosser geneCrosser = new BestGeneCrosser(maxGeneSequenceLength);

    Breeder breeder = new Breeder(
      parentSelector,
      geneCrosser,
      map100,
      mutationChance
    );

    TrialGenerator trialGenerator = new TrialGenerator(
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
