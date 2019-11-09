package edu.louisville.traveler.graphs;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphServiceTest {
  @Test
  public void returnsBFSPathFromAdjacencyMatrix() {
    boolean[][] adjacencyMatrix = new boolean[4][4];
    adjacencyMatrix[0][1] = true;
    adjacencyMatrix[0][2] = true;
    adjacencyMatrix[1][2] = true;
    adjacencyMatrix[1][3] = true;
    adjacencyMatrix[2][3] = true;

    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node4 = new Node(4);

    assertEquals(
      List.of(node1, node2, node4),
      new GraphService().bfsPathFromMatrix(adjacencyMatrix, node1, node4)
    );
  }

  @Test
  public void returnsDFSPathFromAdjacencyMatrix() {
    boolean[][] adjacencyMatrix = new boolean[4][4];
    adjacencyMatrix[0][1] = true;
    adjacencyMatrix[0][2] = true;
    adjacencyMatrix[1][2] = true;
    adjacencyMatrix[1][3] = true;
    adjacencyMatrix[2][3] = true;

    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node4 = new Node(4);

    assertEquals(
      List.of(node1, node2, node4),
      new GraphService().dfsPathFromMatrix(adjacencyMatrix, node1, node4)
    );
  }
}