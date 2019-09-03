package edu.louisville.project1.graphs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@AllArgsConstructor
public class Graph {
  private List<Node> nodes;
  private LinkedHashMap<Node, List<Node>> edges;

  public Graph(List<Node> nodes) {
    edges = new LinkedHashMap<>();
    this.nodes = nodes;
    for (Node node : nodes) {
      this.edges.put(node, new ArrayList<>());
    }
  }

  public Graph(){
    edges = new LinkedHashMap<>();
  }

  public void addEdge(Node start, Node end) {
    if (this.edges.containsKey(start)) {
      this.edges.get(start).add(end);
    } else {
      this.edges.put(start, new ArrayList<>(List.of(end)));
    }
  }

  public void translateAdjacencyMatrixToEdges(boolean[][] adjacencyMatrix) {
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

  private Node getNodeWithID(int id) {
    for (Node node : this.nodes) {
      if (node.getId() == id) {
        return node;
      }
    }
    return new Node(id);
  }
}
