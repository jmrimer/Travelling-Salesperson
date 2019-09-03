package edu.louisville.project1.graphs;

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

  Deque<Node> findShortestPath(Graph graph, Node startingNode) {
    List<Node> traversedGraphSequence = this.traverseGraph(graph, startingNode);
    int maxDepth = calculateMaxDepth(traversedGraphSequence);
    Node lastNode = extractLastNode(traversedGraphSequence, maxDepth);
    return backtrackParentNodes(lastNode, maxDepth);
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
          discoveredNode.setParentNode(nodeBeingVisited);
          oldAndNewNodes.push(discoveredNode);
        }
      }
    }
    return oldAndNewNodes;
  }

  private boolean exists(List<Node> nodes) {
    return nodes != null;
  }

  private Deque<Node> backtrackParentNodes(Node lastNode, int maxDepth) {
    Deque<Node> shortestPath = new LinkedList<>();
    shortestPath.add(lastNode);
    while (maxDepth > 0) {
      maxDepth--;
      shortestPath.addFirst(shortestPath.getFirst().getParentNode());
    }
    return shortestPath;
  }

  private int calculateMaxDepth(List<Node> fullGraphTraversalSequence) {
    int maxDepth = 0;
    for (Node node : fullGraphTraversalSequence) {
      maxDepth = Math.max(node.getDepth(), maxDepth);
    }
    return maxDepth;
  }

  private Node extractLastNode(List<Node> traversedGraphSequence, int maxDepth) {
    for (Node node : traversedGraphSequence) {
      if (node.getDepth() == maxDepth) {
        return node;
      }
    }
    return null;
  }
}
