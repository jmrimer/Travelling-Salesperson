package edu.louisville.project1;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BreadthFirstSearcherTest extends BaseGraphSearchTest {
  @Test
  public void returnsFullGraphTraversalSequenceFromProject2Prompt() {
    assertEquals(
      List.of(node1, node2, node3, node4, node5, node6, node7, node8, node9, node10, node11),
      new BreadthFirstSearcher().traverseGraph(graph, node1)
    );
  }

  @Test
  public void calculatesShortestPath() {
    assertEquals(
      List.of(node1, node3, node5, node8, node11),
      new BreadthFirstSearcher().findShortestPath(graph, node1)
    );
  }
}