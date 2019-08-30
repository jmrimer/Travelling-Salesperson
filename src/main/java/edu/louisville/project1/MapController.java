package edu.louisville.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MapController {
  @Autowired
  private final MappingService mappingService;

  public MapController(MappingService mappingService) {
    this.mappingService = mappingService;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping("/api/weightedRoute")
  public @ResponseBody WeightedRoute weightedRoute() {
    return this.mappingService.route();
  }
}
