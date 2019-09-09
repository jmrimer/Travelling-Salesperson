package edu.louisville.project1.graphs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DepthFirstSearcherTest extends BaseGraphSearchTest {
  @Test
  public void calculatesShortestPath() {
    Assert.assertEquals(
      List.of(node1, node3, node5, node8, node11),
      new DepthFirstSearcher().findShortestPath(graphFromPrompt, node1, node11)
    );
  }
}