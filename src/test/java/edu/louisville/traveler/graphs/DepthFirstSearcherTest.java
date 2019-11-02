package edu.louisville.traveler.graphs;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepthFirstSearcherTest extends BaseGraphSearchTest {
  @Test
  public void calculatesShortestPath() {
    assertEquals(
      List.of(node1, node3, node5, node8, node11),
      new DepthFirstSearcher().findShortestPath(graphFromPrompt, node1, node11)
    );
  }
}