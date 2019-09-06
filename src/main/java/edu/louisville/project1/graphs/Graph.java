package edu.louisville.project1.graphs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@AllArgsConstructor
public class Graph {
  private LinkedHashMap<Node, List<Node>> edges;

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

  }
}
