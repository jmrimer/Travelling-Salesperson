package edu.louisville.traveler.graphs;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphService {
  List<Node> bfsPathFromMatrix(boolean[][] adjacencyMatrix, Node start, Node end) {
    Graph graph = new Graph(adjacencyMatrix);
    start = graph.getNodeWithID(start.getId());
    end = graph.getNodeWithID(end.getId());
    return new BreadthFirstSearcher().findShortestPath(graph, start, end);
  }

  List<Node> dfsPathFromMatrix(boolean[][] adjacencyMatrix, Node start, Node end) {
    Graph graph = new Graph(adjacencyMatrix);
    start = graph.getNodeWithID(start.getId());
    end = graph.getNodeWithID(end.getId());
    return new DepthFirstSearcher().findShortestPath(graph, start, end);
  }
}
