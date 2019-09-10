package edu.louisville.traveler.graphs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node implements Comparable<Node> {
  private int id;
  private int depth = 0;
  private boolean visited = false;
  private boolean discovered = false;
  private Node parentNode;

  public Node(int id) {
    this.id = id;
  }

  @Override
  public int compareTo(Node node) {
    return this.getId() == node.getId() ? 0 : -1;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Node)) {
      return false;
    }

    return this.compareTo((Node) o) == 0;
  }

  @Override
  public String toString() {
    return String.valueOf(this.getId());
  }
}
