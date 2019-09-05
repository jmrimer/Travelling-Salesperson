package edu.louisville.project1;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepthFirstSearcherTest extends BaseGraphSearchTest {
  @Test
  public void naivelyImplementsDFSTraversalWithNumericTieBreakers() {
    assertEquals(
      List.of(node1, node4, node5, node7, node9, node11),
      new DepthFirstSearcher().traverseGraphToEnd(graph, node1, node11)
    );
  }

  @Test
  public void calculatesShortestPath() {
    assertEquals(
      List.of(node1, node3, node5, node8, node11),
      new DepthFirstSearcher().findShortestPath(graph, node1, node11)
    );
  }
}