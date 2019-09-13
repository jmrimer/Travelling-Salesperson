package edu.louisville.traveler.maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MapController {
  @Autowired
  private final MappingService mappingService;

  public MapController(MappingService mappingService) {
    this.mappingService = mappingService;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/api/weighted-route")
  public @ResponseBody
  Tour newWeightedRouteViaBruteForce(
    @RequestBody Map map
  ) {
    return this.mappingService.routeFromMap(map);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/api/weighted-route-via-insertion")
  public @ResponseBody
  Tour newWeightedRouteViaInsertion(
    @RequestBody Map map
  ) {
    return this.mappingService.routeFromMapViaInsertion(map);
  }
}
