package edu.louisville.project1;

import org.paukov.combinatorics3.Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HamiltonPathMapper {
  public HashMap<String, Integer> map(List<Integer> cityNumbers) {
    HashMap<String, Integer> routes = new HashMap<>();

    Generator.permutation(cityNumbers)
      .simple()
      .stream()
      .forEach(route -> {
          List<String> cities = new ArrayList<>();
          for (int city : route) {
            cities.add(String.valueOf(city));
          }
          routes.put(String.join("-", cities), 0);
        }
      );
    return routes;
  }
}
