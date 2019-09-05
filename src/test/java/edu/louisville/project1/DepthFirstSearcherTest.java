package edu.louisville.project1;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepthFirstSearcherTest extends BaseGraphSearchTest {
  @Test
  public void calculatesShortestPath() {
    assertEquals(
      List.of(node1, node3, node5, node8, node11),
      new DepthFirstSearcher().findShortestPath(graph, node1, node11)
    );
  }
}