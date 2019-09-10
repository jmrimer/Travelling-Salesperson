package edu.louisville.traveler.graphs;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
  @Override
  public int compare(Node n1, Node n2) {
    return new CompareToBuilder()
      .append(n1.getDepth(), n2.getDepth())
      .append(n1.getId(), n2.getId())
      .toComparison();
  }
}
