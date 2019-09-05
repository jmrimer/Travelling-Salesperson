package edu.louisville.project1;

import edu.louisville.project1.graphs.Node;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepthFirstSearcherTest extends BaseGraphSearchTest {
  @Test
  public void naivelyImplementsDFSTraversalOfAllPaths() {
    assertEquals(
      40,
      new DepthFirstSearcher().traverseGraphToEnd(graph, node1, node11).size()
    );
  }

  @Test
  public void calculatesShortestPath() {
    List<List<Node>> allPaths = new DepthFirstSearcher().traverseGraphToEnd(graph, node1, node11);
    assertEquals(
      List.of(node1, node3, node5, node8, node11),
      new DepthFirstSearcher().findShortestPath(allPaths)
    );
  }
}