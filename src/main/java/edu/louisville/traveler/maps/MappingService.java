package edu.louisville.traveler.maps;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingService {
  public Tour route() {
    TravelingSalespersonProblemSolver solver = new TravelingSalespersonProblemSolver();
    return solver.calculateShortestPath(
      List.of(
        new City(1, 87.951292, 2.658162),
        new City(2, 33.466597, 66.682943),
        new City(3, 91.778314, 53.807184),
        new City(4, 20.526749, 47.633290)
      )
    );
  }

  Tour routeFromMap(Map map) {
    TravelingSalespersonProblemSolver solver = new TravelingSalespersonProblemSolver();
    return solver.calculateShortestPath(map.getCities());
  }

  Tour routeFromMapViaInsertion(Map map) {
    ClosestEdgeInserter closestEdgeInserter = new ClosestEdgeInserter();
    return closestEdgeInserter.generateTour(map.getCities());
  }
}
