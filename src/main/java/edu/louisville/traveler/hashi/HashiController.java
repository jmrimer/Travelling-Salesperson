package edu.louisville.traveler.hashi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HashiController {
  @Autowired
  private final HashiService hashiService;

  public HashiController(HashiService hashiService) {
    this.hashiService = hashiService;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/api/hashi-solver")
  public @ResponseBody
  HashiMap staticMap() {
    return this.hashiService.staticMap();
  }
}
