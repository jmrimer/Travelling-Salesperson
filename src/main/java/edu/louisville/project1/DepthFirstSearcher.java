package edu.louisville.project1;

import edu.louisville.project1.graphs.Graph;
import edu.louisville.project1.graphs.Node;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DepthFirstSearcher {
  List<Node> traverseGraphToEnd(Graph graph, Node start, Node end) {
    List<Node> traversal = new ArrayList<>();
    traversal.add(start);
    List<Node> firstStops = graph.getEdges().get(start);
    for (Node node : firstStops) {
      traversal.add(node);
      for (Node subNode : graph.getEdges().get(node)) {
        traversal.add(subNode);
      }
    }
    return traversal;
  }

  Deque<Node> findShortestPath(Graph graph, Node start, Node end) {
    return new LinkedList<>();
  }
}
