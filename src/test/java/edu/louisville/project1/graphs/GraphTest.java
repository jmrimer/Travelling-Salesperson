package edu.louisville.project1.graphs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {
  @Test
  public void addEdge() {
    Graph graph = new Graph();
    Node nodeA = new Node("A");
    Node nodeB = new Node("B");

    graph.addEdge(nodeA, nodeB);
    assertEquals(1, graph.getEdges().size());
    assertEquals(nodeB, graph.getEdges().get(nodeA).get(0));
  }

  @Test
  public void addManyEdges() {
    Graph graph = new Graph();
    Node nodeA = new Node("A");
    Node nodeB = new Node("B");
    Node nodeC = new Node("C");
    Node nodeD = new Node("D");

    graph.addEdge(nodeA, nodeB);
    graph.addEdge(nodeA, nodeC);
    graph.addEdge(nodeA, nodeD);
    graph.addEdge(nodeB, nodeC);
    assertEquals(
      List.of(nodeB, nodeC, nodeD),
      graph.getEdges().get(nodeA)
    );
    assertEquals(
      List.of(nodeC),
      graph.getEdges().get(nodeB)
    );
  }
}