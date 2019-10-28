package edu.louisville.traveler.crowds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrowdController {
  @Autowired
  private final CrowdSourceService crowdSourceService;

  public CrowdController(CrowdSourceService crowdSourceService) {
    this.crowdSourceService = crowdSourceService;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/api/crowd-wisdom")
  public @ResponseBody
  Wisdom newWisdomFromModel(
    @RequestBody WisdomRequestModel wisdomRequest
  ) {
    return this.crowdSourceService.wisdomFromRequest(wisdomRequest);
  }
}

