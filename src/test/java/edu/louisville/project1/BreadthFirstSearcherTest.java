package edu.louisville.project1;

import edu.louisville.project1.graphs.Graph;
import edu.louisville.project1.graphs.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BreadthFirstSearcherTest {
  Graph graph;
  private Node nodeA = new Node("A");
  private Node nodeB = new Node("B");
  private Node nodeC = new Node("C");
  private Node nodeD = new Node("D");
  private Node nodeE = new Node("E");
  private Node nodeF = new Node("F");
  private Node nodeG = new Node("G");
  private Node nodeH = new Node("H");
  private Node nodeI = new Node("I");

  @Before
  public void setup() {
    graph = new Graph();
    graph.addEdge(nodeA, nodeB);
    graph.addEdge(nodeA, nodeC);
  }

  @Test
  public void returnsSequentialNodeList() {
    graph.addEdge(nodeB, nodeD);
    graph.addEdge(nodeC, nodeE);
    graph.addEdge(nodeC, nodeF);

    assertEquals(
      List.of(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF),
      new BreadthFirstSearcher().traverseGraph(graph, nodeA)
    );
  }

  @Test
  public void returnsSequentialNodeListWithAlphabeticalTieBreaks() {
    graph.addEdge(nodeB, nodeD);
    graph.addEdge(nodeC, nodeF);
    graph.addEdge(nodeC, nodeE);

    assertEquals(
      List.of(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF),
      new BreadthFirstSearcher().traverseGraph(graph, nodeA)
    );
  }
  @Test
  public void returnsSequentialNodeListWithAlphabeticalTieBreaksAndMultipleEdgesToSameNode() {
    graph.addEdge(nodeB, nodeD);
    graph.addEdge(nodeC, nodeF);
    graph.addEdge(nodeC, nodeE);

    graph.addEdge(nodeD, nodeH);
    graph.addEdge(nodeE, nodeI);
    graph.addEdge(nodeF, nodeG);

    graph.addEdge(nodeH, nodeI);

    assertEquals(
      List.of(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG, nodeH, nodeI),
      new BreadthFirstSearcher().traverseGraph(graph, nodeA)
    );
  }
}