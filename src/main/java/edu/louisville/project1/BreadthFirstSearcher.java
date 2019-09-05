package edu.louisville.project1;

import edu.louisville.project1.graphs.Graph;
import edu.louisville.project1.graphs.Node;
import edu.louisville.project1.graphs.NodeComparator;

import java.util.*;

class BreadthFirstSearcher {

  List<Node> traverseGraph(Graph graph, Node start) {
    Node nodeBeingVisited;
    List<Node> traveledPath = new ArrayList<>();
    Stack<Node> nodesToVisit = initializePathQueue(start);

    while (unvisitedNodesRemain(nodesToVisit)) {
      nodesToVisit = sortNodesAlphabeticallyByLevel(nodesToVisit);
      nodeBeingVisited = nodesToVisit.remove(0);
      traveledPath = addVisitedNodeToPath(nodeBeingVisited, traveledPath);
      nodesToVisit = discoverAdjacentNodes(graph, nodesToVisit, nodeBeingVisited);
    }
    return traveledPath;
  }

  private List<Node> addVisitedNodeToPath(Node visitedNode, List<Node> traveledPath) {
    visitedNode.setVisited(true);
    List<Node> path = new ArrayList<>(traveledPath);
    if (!path.contains(visitedNode)) {
      path.add(visitedNode);
    }
    return path;
  }

  private boolean unvisitedNodesRemain(Stack<Node> nodesToVisit) {
    return !nodesToVisit.empty();
  }

  private Stack<Node> initializePathQueue(Node start) {
    Stack<Node> nodeQueue = new Stack<>();
    nodeQueue.push(start);
    return nodeQueue;
  }

  private Stack<Node> sortNodesAlphabeticallyByLevel(Stack<Node> nodesToVisit) {
    @SuppressWarnings("unchecked")
    Stack<Node> sortedNodes = (Stack<Node>) nodesToVisit.clone();
    sortedNodes.sort(new NodeComparator());
    return sortedNodes;
  }

  private Stack<Node> discoverAdjacentNodes(
    Graph graph,
    Stack<Node> nodesToVisit,
    Node nodeBeingVisited
  ) {
    @SuppressWarnings("unchecked")
    Stack<Node> oldAndNewNodes = (Stack<Node>) nodesToVisit.clone();
    List<Node> adjacentNodes = graph.getEdges().get(nodeBeingVisited);
    if (exists(adjacentNodes)) {
      for (Node discoveredNode : adjacentNodes) {
        if (!discoveredNode.isDiscovered()) {
          discoveredNode.setDiscovered(true);
          discoveredNode.setDepth(nodeBeingVisited.getDepth() + 1);
          oldAndNewNodes.push(discoveredNode);
        }
      }
    }
    return oldAndNewNodes;
  }

  private boolean exists(List<Node> nodes) {
    return nodes != null;
  }
}
