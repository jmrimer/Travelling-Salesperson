package edu.louisville.traveler.genetics;

import edu.louisville.traveler.genetics.crossers.GeneCrosser;
import edu.louisville.traveler.genetics.crossers.OrderedGeneCrosser;
import edu.louisville.traveler.genetics.seeders.PolarPopulationSeeder;
import edu.louisville.traveler.genetics.seeders.PopulationSeeder;
import edu.louisville.traveler.genetics.selectors.ParentSelector;
import edu.louisville.traveler.genetics.selectors.TournamentStyleParentSelector;
import org.junit.jupiter.api.Test;

public class PolarSeedTournamentParentOrderedCrossoverTrial extends BaseGeneticsTest {
  long timestamp;
  @Test
  public void run30Trials() {
    timestamp = System.currentTimeMillis();
    for (int i = 0; i < 32; i++) {
      initializesWithPolarSelectsTournamentParentsAndCrossesOrderedGenes();
    }
  }

  @Test
  public void initializesWithPolarSelectsTournamentParentsAndCrossesOrderedGenes()  {
    PopulationSeeder seeder = new PolarPopulationSeeder();
    ParentSelector parentSelector = new TournamentStyleParentSelector();
    GeneCrosser geneCrosser = new OrderedGeneCrosser(maxGeneSequenceLength);

    Breeder breeder = new Breeder(
      parentSelector,
      geneCrosser,
      map100,
      mutationChance
    );

    TrialGenerator trialGenerator = new TrialGenerator(
      map100,
      seeder,
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
