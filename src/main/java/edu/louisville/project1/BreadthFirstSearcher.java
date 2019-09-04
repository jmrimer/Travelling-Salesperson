package edu.louisville.project1;

import edu.louisville.project1.graphs.Graph;
import edu.louisville.project1.graphs.Node;

import java.util.*;

class BreadthFirstSearcher {
  List<Node> traverseGraph(Graph graph, Node start) {
    List<Node> traveledPath = new ArrayList<>();
    Stack<Node> nodesToVisit = new Stack<>();
    nodesToVisit.push(start);
    while (!nodesToVisit.empty()) {
      Node currentStartingNode = nodesToVisit.remove(0);
      if (!traveledPath.contains(currentStartingNode)) {
        traveledPath.add(currentStartingNode);
        nodesToVisit = addConnectNodes(graph, currentStartingNode, nodesToVisit);
      }
    }
    return traveledPath;
  }

  private Stack<Node> addConnectNodes(
    Graph graph,
    Node visitingNode,
    Stack<Node> nodesToVisit
  ) {
    Stack<Node> nodesToVisitPlusConnectedNodes = (Stack<Node>) nodesToVisit.clone();
    List<Node> nextStops = graph.getEdges().get(visitingNode);
    if (nextStops != null) {
      Collections.sort(nextStops);
      for (Node connectedNode : nextStops) {
        nodesToVisitPlusConnectedNodes.push(connectedNode);
      }
    }
    return nodesToVisitPlusConnectedNodes;
  }
}
