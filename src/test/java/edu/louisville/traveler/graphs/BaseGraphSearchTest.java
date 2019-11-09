package edu.louisville.traveler.graphs;

import org.junit.jupiter.api.BeforeEach;;

public class BaseGraphSearchTest {
  Graph graphFromPrompt;
  Graph graphWithSolutionBeyondTieBreakers;
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
  boolean[][] adjacencyMatrix = new boolean[11][11];

@BeforeEach
  public void setUp() throws Exception {
    instantiateMatrix();
    graphFromPrompt = new Graph(adjacencyMatrix);
    graphWithSolutionBeyondTieBreakers = new Graph();

    instantiateNodes();
    addEdgesToPromptGraph();
    addEdgesToTieBreakerGraph();
  }

  private void addEdgesToTieBreakerGraph() {
    graphWithSolutionBeyondTieBreakers.addEdge(node1, node2);
    graphWithSolutionBeyondTieBreakers.addEdge(node1, node3);
    graphWithSolutionBeyondTieBreakers.addEdge(node1, node6);

    graphWithSolutionBeyondTieBreakers.addEdge(node2, node4);

    graphWithSolutionBeyondTieBreakers.addEdge(node3, node5);

    graphWithSolutionBeyondTieBreakers.addEdge(node4, node7);

    graphWithSolutionBeyondTieBreakers.addEdge(node5, node7);

    graphWithSolutionBeyondTieBreakers.addEdge(node6, node7);
  }

  private void instantiateMatrix() {
    adjacencyMatrix[0][1] = true;
    adjacencyMatrix[0][2] = true;
    adjacencyMatrix[0][3] = true;

    adjacencyMatrix[1][2] = true;

    adjacencyMatrix[2][3] = true;
    adjacencyMatrix[2][4] = true;

    adjacencyMatrix[3][4] = true;
    adjacencyMatrix[3][5] = true;
    adjacencyMatrix[3][6] = true;

    adjacencyMatrix[4][6] = true;
    adjacencyMatrix[4][7] = true;

    adjacencyMatrix[5][7] = true;

    adjacencyMatrix[6][8] = true;
    adjacencyMatrix[6][9] = true;

    adjacencyMatrix[7][8] = true;
    adjacencyMatrix[7][9] = true;
    adjacencyMatrix[7][10] = true;

    adjacencyMatrix[8][10] = true;

    adjacencyMatrix[9][10] = true;
  }

  private void addEdgesToPromptGraph() {
    graphFromPrompt.addEdge(node1, node2);
    graphFromPrompt.addEdge(node1, node3);
    graphFromPrompt.addEdge(node1, node4);

    graphFromPrompt.addEdge(node2, node3);

    graphFromPrompt.addEdge(node3, node4);
    graphFromPrompt.addEdge(node3, node5);

    graphFromPrompt.addEdge(node4, node5);
    graphFromPrompt.addEdge(node4, node6);
    graphFromPrompt.addEdge(node4, node7);

    graphFromPrompt.addEdge(node5, node7);
    graphFromPrompt.addEdge(node5, node8);

    graphFromPrompt.addEdge(node6, node8);

    graphFromPrompt.addEdge(node7, node9);
    graphFromPrompt.addEdge(node7, node10);

    graphFromPrompt.addEdge(node8, node9);
    graphFromPrompt.addEdge(node8, node10);
    graphFromPrompt.addEdge(node8, node11);

    graphFromPrompt.addEdge(node9, node11);

    graphFromPrompt.addEdge(node10, node11);
  }

  private void instantiateNodes() {
    node1 = graphFromPrompt.getNodeWithID(1);
    node2 = graphFromPrompt.getNodeWithID(2);
    node3 = graphFromPrompt.getNodeWithID(3);
    node4 = graphFromPrompt.getNodeWithID(4);
    node5 = graphFromPrompt.getNodeWithID(5);
    node6 = graphFromPrompt.getNodeWithID(6);
    node7 = graphFromPrompt.getNodeWithID(7);
    node8 = graphFromPrompt.getNodeWithID(8);
    node9 = graphFromPrompt.getNodeWithID(9);
    node10 = graphFromPrompt.getNodeWithID(10);
    node11 = graphFromPrompt.getNodeWithID(11);
  }
}