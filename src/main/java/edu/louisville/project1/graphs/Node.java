package edu.louisville.project1.graphs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node implements Comparable<Node>{
  private String name;
  private int depth = 0;
  private boolean visited = false;
  private boolean discovered = false;
  private Node parentNode;

  public Node(String name) {
    this.name = name;
  }

  @Override
  public int compareTo(Node node) {
    return this.name.compareTo(node.name);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Node)) {
      return false;
    }

    Node node = (Node) o;

    return this.compareTo(node) == 0;
  }
}
