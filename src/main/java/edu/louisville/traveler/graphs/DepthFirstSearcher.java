package edu.louisville.traveler.graphs;

import java.util.*;

class DepthFirstSearcher {
  List<Node> findShortestPath(Graph graph, Node start, Node end) {
    LinkedHashSet<List<Node>> allPathsFromStartToEnd = this.traverseGraphToEnd(graph, start, end);
    return extractShortestPath(allPathsFromStartToEnd);
  }

  private LinkedHashSet<List<Node>> traverseGraphToEnd(Graph graph, Node start, Node end) {
    LinkedHashSet<List<Node>> pathCollector = new LinkedHashSet<>(new ArrayList<>());
    return recursiveExploration(graph, pathCollector, new ArrayList<>(), start, end);
  }

  private LinkedHashSet<List<Node>> recursiveExploration(
    Graph graph,
    LinkedHashSet<List<Node>> pathCollector,
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

  private int shortestPathLength(LinkedHashSet<List<Node>> paths) {
    int shortestPathLength = Integer.MAX_VALUE;
    for (List<Node> path : paths) {
      shortestPathLength = Math.min(path.size(), shortestPathLength);
    }
    return shortestPathLength;
  }

  private List<Node> extractShortestPath(LinkedHashSet<List<Node>> allPathsFromStartToEnd) {
    int shortestPathLength = this.shortestPathLength(allPathsFromStartToEnd);
    LinkedHashSet<List<Node>> shortestPaths = keepOnlyShortestPaths(allPathsFromStartToEnd, shortestPathLength);
    return breakSameLengthTiesByNodeNameOrder(shortestPaths);
  }

  private List<Node> firstList(LinkedHashSet<List<Node>> paths) {
    Iterator<List<Node>> iterator = paths.iterator();
    return iterator.next();
  }

  private List<Node> breakSameLengthTiesByNodeNameOrder(LinkedHashSet<List<Node>> paths) {
    if (paths.size() > 0) {
      for (int depth = 0; depth < firstList(paths).size(); depth++) {
        int lesserNodeValue = determineLeastNodeAtLevel(paths, depth);
        paths = removeAllPathsThatLoseTieBreaker(paths, depth, lesserNodeValue);
      }
      return firstList(paths);
    }
    return null;
  }

  private LinkedHashSet<List<Node>> removeAllPathsThatLoseTieBreaker(LinkedHashSet<List<Node>> paths, int depth, int leastNodeID) {
    LinkedHashSet<List<Node>> winningPaths = new LinkedHashSet<>(new ArrayList<>(paths));
    winningPaths.removeIf(path -> path.get(depth).getId() != leastNodeID);
    return winningPaths;
  }

  private int determineLeastNodeAtLevel(LinkedHashSet<List<Node>> paths, int depth) {
    int leastNodeID = Integer.MAX_VALUE;
    for (List<Node> path : paths) {
      int nodeName = path.get(depth).getId();
      leastNodeID = Math.min(nodeName, leastNodeID);
    }
    return leastNodeID;
  }

  private LinkedHashSet<List<Node>> keepOnlyShortestPaths(LinkedHashSet<List<Node>> paths, int shortestPathLength) {
    LinkedHashSet<List<Node>> shortestPaths = new LinkedHashSet<>(paths);
    shortestPaths.removeIf(nodes -> nodes.size() != shortestPathLength);
    return shortestPaths;
  }
}
