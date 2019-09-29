package edu.louisville.traveler.genetics;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class Trial {
  private List<Generation> generations = new ArrayList<>();

  public void add(Generation generation) {
    this.generations.add(generation);
  }
}
