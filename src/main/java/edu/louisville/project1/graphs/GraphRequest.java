package edu.louisville.project1.graphs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class GraphRequest {
  private boolean[][] adjacencyMatrix;
  private Node start;
  private Node end;

  public GraphRequest(
    boolean[][] adjacencyMatrix,
    Node start
  ){
    this.adjacencyMatrix = adjacencyMatrix;
    this.start = start;
  }
}