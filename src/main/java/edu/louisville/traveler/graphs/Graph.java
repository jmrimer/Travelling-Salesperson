package edu.louisville.traveler.graphs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
class Graph {
  private List<Node> nodes;
  private LinkedHashMap<Node, List<Node>> edges;

  Graph(){
    edges = new LinkedHashMap<>();
  }

  Graph(boolean[][] adjacencyMatrix) {
    this();
    this.nodes = adjacencyMatrixToNodeList(adjacencyMatrix);
    this.translateAdjacencyMatrixToEdges(adjacencyMatrix);
  }

  private static List<Node> adjacencyMatrixToNodeList(boolean[][] adjacencyMatrix) {
    List<Node> nodes = new ArrayList<>();
    for (int i = 0; i < adjacencyMatrix.length; i++) {
      nodes.add(new Node(i + 1));
    }
    return nodes;
  }

  void addEdge(Node start, Node end) {
    if (this.edges.containsKey(start)) {
      this.edges.get(start).add(end);
    } else {
      this.edges.put(start, new ArrayList<>(List.of(end)));
    }
  }

  void translateAdjacencyMatrixToEdges(boolean[][] adjacencyMatrix) {
    for (int i = 0; i < adjacencyMatrix.length; i++) {
      Node fromNode = this.getNodeWithID(i + 1);
      for (int j = 0; j < adjacencyMatrix.length; j++) {
        if (adjacencyMatrix[i][j]) {
          Node toNode = this.getNodeWithID(j + 1);
          this.addEdge(fromNode, toNode);
        }
      }
    }
  }

  Node getNodeWithID(int id) {
    for (Node node : this.nodes) {
      if (node.getId() == id) {
        return node;
      }
    }
    return new Node(id);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Graph)) {
      return false;
    }

    return nodesAreEqual((Graph) o) && edgesAreEqual(((Graph) o).getEdges());
  }

  boolean edgesAreEqual(LinkedHashMap<Node, List<Node>> first) {
    LinkedHashMap<Node, List<Node>> second = this.getEdges();
    if (first.size() != second.size()) {
      return false;
    }

    for (Map.Entry<Node, List<Node>> entry : first.entrySet()) {
      Node key = entry.getKey();
      List<Node> value = entry.getValue();
      if (!second.get(this.getNodeWithID(key.getId())).equals(value)) {
        return false;
      };
    }

    return true;
  }

  private boolean nodesAreEqual(Graph o) {
    return o.getNodes().equals(this.getNodes());
  }
}
