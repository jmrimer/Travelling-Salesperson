package edu.louisville.project1.graphs;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphService {
  List<Node> bfsPathFromMatrix(boolean[][] adjacencyMatrix, Node start) {
    List<Node> nodes = new ArrayList<>();
    nodes.add(start);
    for (int i = 0; i < adjacencyMatrix.length; i++) {
      Node node = new Node(i + 1);
      if (!nodes.contains(node)) {
        nodes.add(node);
      }
    }
    Graph graph = new Graph(nodes);
    graph.translateAdjacencyMatrixToEdges(adjacencyMatrix);
    return (List<Node>) new BreadthFirstSearcher().findShortestPath(graph, start);
  }
}
