package edu.louisville.project1.graphs;

import java.util.ArrayList;
import java.util.List;

class DepthFirstSearcher {
  List<Node> findShortestPath(Graph graph, Node start, Node end) {
    List<List<Node>> allPathsFromStartToEnd = this.traverseGraphToEnd(graph, start, end);
    return extractShortestPath(allPathsFromStartToEnd);
  }

  private List<List<Node>> traverseGraphToEnd(Graph graph, Node start, Node end) {
    List<List<Node>> pathCollector = new ArrayList<>(new ArrayList<>());
    return recursiveExploration(graph, pathCollector, new ArrayList<>(), start, end);
  }

  private List<List<Node>> recursiveExploration(
    Graph graph,
    List<List<Node>> pathCollector,
    List<Node> traveledPath,
    Node start,
    Node end
  ) {
    traveledPath.add(start);
    List<Node> children = discoverChildren(graph, start);
    if (!exists(children)) {
      if (lastNodeIn(traveledPath).equals(end)) {
        pathCollector.add(traveledPath);
      }
      return pathCollector;
    }
    for (Node child : children) {
      List<Node> pathForChild = new ArrayList<>(traveledPath);
      pathCollector = this.recursiveExploration(graph, pathCollector, pathForChild, child, end);
    }
    return pathCollector;
  }

  private Node lastNodeIn(List<Node> traveledPath) {
    return traveledPath.get(traveledPath.size() - 1);
  }

  private List<Node> discoverChildren(
    Graph graph,
    Node rootNode
  ) {
    List<Node> adjacentNodes = graph.getEdges().get(rootNode);
    if (exists(adjacentNodes)) {
      for (Node discoveredNode : adjacentNodes) {
        if (!discoveredNode.isDiscovered()) {
          discoveredNode.setDiscovered(true);
          discoveredNode.setDepth(rootNode.getDepth() + 1);
          discoveredNode.setParentNode(rootNode);
        }
      }
    }
    return adjacentNodes;
  }

  private boolean exists(List<Node> nodes) {
    return nodes != null && nodes.size() > 0;
  }

  private int shortestPathLength(List<List<Node>> paths) {
    int shortestPathLength = Integer.MAX_VALUE;
    for (List<Node> path : paths) {
      shortestPathLength = Math.min(path.size(), shortestPathLength);
    }
    return shortestPathLength;
  }

  private List<Node> extractShortestPath(List<List<Node>> allPathsFromStartToEnd) {
    int shortestPathLength = this.shortestPathLength(allPathsFromStartToEnd);
    List<List<Node>> shortestPaths = keepOnlyShortestPaths(allPathsFromStartToEnd, shortestPathLength);
    return breakSameLengthTiesByNodeNameOrder(shortestPaths);
  }

  private List<Node> breakSameLengthTiesByNodeNameOrder(List<List<Node>> paths) {
    if (paths.size() > 0) {
      for (int depth = 0; depth < paths.get(0).size(); depth++) {
        int lesserNodeValue = determineLeastNodeAtLevel(paths, depth);
        paths = removeAllPathsThatLoseTieBreaker(paths, depth, lesserNodeValue);
      }
      return paths.get(0);
    }
    return null;
  }

  private List<List<Node>> removeAllPathsThatLoseTieBreaker(List<List<Node>> paths, int depth, int leastNodeID) {
    List<List<Node>> winningPaths = new ArrayList<>(paths);
    winningPaths.removeIf(path -> path.get(depth).getId() != leastNodeID);
    return winningPaths;
  }

  private int determineLeastNodeAtLevel(List<List<Node>> paths, int depth) {
    int leastNodeID = Integer.MAX_VALUE;
    for (List<Node> path : paths) {
      int nodeName = path.get(depth).getId();
      leastNodeID = Math.min(nodeName, leastNodeID);
    }
    return leastNodeID;
  }

  private List<List<Node>> keepOnlyShortestPaths(List<List<Node>> paths, int shortestPathLength) {
    List<List<Node>> shortestPaths = new ArrayList<>(paths);
    shortestPaths.removeIf(nodes -> nodes.size() != shortestPathLength);
    return shortestPaths;
  }
}
