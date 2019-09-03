package edu.louisville.project1.graphs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GraphServiceTest {
  @Test
  public void returnsPathFromAdjacencyMatrix() {
    boolean[][] adjacencyMatrix = new boolean[4][4];
    adjacencyMatrix[0][1] = true;
    adjacencyMatrix[0][2] = true;
    adjacencyMatrix[1][2] = true;
    adjacencyMatrix[1][3] = true;
    adjacencyMatrix[2][3] = true;

    assertEquals(
      List.of(new Node(1), new Node(2), new Node(4)),
      new GraphService().bfsPathFromMatrix(adjacencyMatrix, new Node(1))
    );
  }
}