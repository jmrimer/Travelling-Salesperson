package edu.louisville.project1.graphs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DepthFirstSearcherTest extends BaseGraphSearchTest {
  @Test
  public void calculatesShortestPath() {
    Assert.assertEquals(
      List.of(node1, node3, node5, node8, node11),
      new DepthFirstSearcher().findShortestPath(graph, node1, node11)
    );
  }

  @Test
  public void calculatesShortestPathFromMatrix() {
    boolean[][] adjacencyMatrix = new boolean[3][3];
    adjacencyMatrix[0][1] = true;
    adjacencyMatrix[1][2] = true;

//    Node startNode = new Node(1);
//    Node endNode = new Node(3);
    graph = new Graph(List.of(node1, node2, node3));
    graph.translateAdjacencyMatrixToEdges(adjacencyMatrix);

    Assert.assertEquals(
      List.of(node1, node2, node3),
      new DepthFirstSearcher().findShortestPath(graph, node1, node3)
    );
  }
}