package edu.louisville.project1;

import edu.louisville.project1.graphs.Graph;
import edu.louisville.project1.graphs.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BreadthFirstSearcherTest {
  Graph graph;
  private Node node1 = new Node("01");
  private Node node2 = new Node("02");
  private Node node3 = new Node("03");
  private Node node4 = new Node("04");
  private Node node5 = new Node("05");
  private Node node6 = new Node("06");
  private Node node7 = new Node("07");
  private Node node8 = new Node("08");
  private Node node9 = new Node("09");
  private Node node10 = new Node("10");
  private Node node11 = new Node("11");

  @Before
  public void setup() {
    graph = new Graph();
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