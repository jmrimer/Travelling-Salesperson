package edu.louisville.project1;

import edu.louisville.project1.graphs.Graph;
import edu.louisville.project1.graphs.Node;
import org.junit.Before;

import java.util.List;

public class BaseGraphSearchTest {
  Graph graph;
  Node node1;
  Node node2;
  Node node3;
  Node node4;
  Node node5;
  Node node6;
  Node node7;
  Node node8;
  Node node9;
  Node node10;
  Node node11;

  @Before
  public void setUp() throws Exception {
    graph = new Graph();
    instantiateNodes();
    addEdgesToGraph();
  }

  private void addEdgesToGraph() {
    graph.addEdge(node1, node2);
    graph.addEdge(node1, node3);
    graph.addEdge(node1, node4);

    graph.addEdge(node2, node3);

    graph.addEdge(node3, node4);
    graph.addEdge(node3, node5);

    graph.addEdge(node4, node5);
    graph.addEdge(node4, node6);
    graph.addEdge(node4, node7);

    graph.addEdge(node5, node7);
    graph.addEdge(node5, node8);

    graph.addEdge(node6, node8);

    graph.addEdge(node7, node9);
    graph.addEdge(node7, node10);

    graph.addEdge(node8, node9);
    graph.addEdge(node8, node10);
    graph.addEdge(node8, node11);

    graph.addEdge(node9, node11);

    graph.addEdge(node10, node11);
  }

  private void instantiateNodes() {
    node1 = new Node(1);
    node2 = new Node(2);
    node3 = new Node(3);
    node4 = new Node(4);
    node5 = new Node(5);
    node6 = new Node(6);
    node7 = new Node(7);
    node8 = new Node(8);
    node9 = new Node(9);
    node10 = new Node(10);
    node11 = new Node(11);
  }
}