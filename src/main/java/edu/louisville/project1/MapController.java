package edu.louisville.project1;

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
  @RequestMapping("/api/weighted-route")
  public @ResponseBody WeightedRoute staticWeightedRoute() {
    return this.mappingService.route();
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/api/weighted-route")
  public @ResponseBody WeightedRoute newWeightedRoute(
    @RequestBody Map map
  ) {
    return this.mappingService.routeFromMap(map);
  }
}
