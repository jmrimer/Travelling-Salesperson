package edu.louisville.traveler.graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {

  private Node node1;
  private Node node2;
  private Node node3;
  private Node node4;
  private Graph graph;

@BeforeEach
  public void setup() {
    graph = new Graph();
    node1 = new Node(1);
    node2 = new Node(2);
    node3 = new Node(3);
    node4 = new Node(4);
  }

  @Test
  public void addEdge() {
    graph.addEdge(node1, node2);
    assertEquals(1, graph.getEdges().size());
    assertEquals(node2, graph.getEdges().get(node1).get(0));
  }

  @Test
  public void addManyEdges() {
    graph.addEdge(node1, node2);
    graph.addEdge(node1, node3);
    graph.addEdge(node1, node4);
    graph.addEdge(node2, node3);
    assertEquals(
      List.of(node2, node3, node4),
      graph.getEdges().get(node1)
    );
    assertEquals(
      List.of(node3),
      graph.getEdges().get(node2)
    );
  }

  @Test
  public void convertsAdjacencyMatrixToEdges() {
    boolean[][] adjacencyMatrix = new boolean[4][4];
    adjacencyMatrix[0][1] = true;
    adjacencyMatrix[0][2] = true;
    adjacencyMatrix[1][2] = true;
    adjacencyMatrix[1][3] = true;
    adjacencyMatrix[2][3] = true;
    graph = new Graph(adjacencyMatrix);

    LinkedHashMap<Node, List<Node>> expectedEdges = new LinkedHashMap<>();
    expectedEdges.put(node1, List.of(node2, node3));
    expectedEdges.put(node2, List.of(node3, node4));
    expectedEdges.put(node3, List.of(node4));
    assertTrue(graph.edgesAreEqual(expectedEdges));
  }
}