package edu.louisville.project1.graphs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node implements Comparable<Node>{
  private String name;


  @Override
  public int compareTo(Node o) {
    return this.name.compareTo(o.name);
  }
}
