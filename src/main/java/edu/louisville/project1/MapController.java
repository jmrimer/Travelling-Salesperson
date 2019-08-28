package edu.louisville.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MapController {
  @Autowired
  private final MappingService mappingService;

  public MapController(MappingService mappingService) {
    this.mappingService = mappingService;
  }

  @RequestMapping("/api/map")
  public @ResponseBody WeightedRoute map() {
    return this.mappingService.route();
  }
}
