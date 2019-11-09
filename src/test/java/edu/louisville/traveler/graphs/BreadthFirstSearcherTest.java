package edu.louisville.traveler.graphs;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreadthFirstSearcherTest extends BaseGraphSearchTest {
  @Test
  public void calculatesShortestPath() {
    assertEquals(
      List.of(node1, node3, node5, node8, node11),
      new BreadthFirstSearcher().findShortestPath(graphFromPrompt, node1, node11)
    );
  }

  @Test
  public void calculatesShortestPathBeyondTieBreakers() {
    assertEquals(
      List.of(node1, node6, node7),
      new BreadthFirstSearcher().findShortestPath(graphWithSolutionBeyondTieBreakers, node1, node7)
    );
  }
}