package edu.louisville.traveler.hashi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashiSolverTest extends BaseHashiTest {
  @Test
  public void isCorner() {
    hashiSolver = new HashiSolver(hashiMap7x7Empty);

    Island islandTopRight = new Island(new Coordinates(6, 6), 1);
    Island islandBottomRight = new Island(new Coordinates(6, 0), 1);
    Island islandBottomLeft = new Island(new Coordinates(0, 0), 1);
    Island islandTopLeft = new Island(new Coordinates(0, 6), 1);

    assertTrue(hashiSolver.isCorner(islandTopRight));
    assertTrue(hashiSolver.isCorner(islandBottomRight));
    assertTrue(hashiSolver.isCorner(islandBottomLeft));
    assertTrue(hashiSolver.isCorner(islandTopLeft));
  }
}