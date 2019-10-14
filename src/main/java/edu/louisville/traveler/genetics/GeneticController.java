package edu.louisville.traveler.genetics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GeneticController {
  @Autowired
  private final GeneticTrialService geneticTrialService;

  public GeneticController(GeneticTrialService geneticTrialService) {
    this.geneticTrialService = geneticTrialService;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/api/genetic-trial")
  public @ResponseBody
  Trial newTrialFromModel(
    @RequestBody TrialRequestModel trialRequest
  ) {
    return this.geneticTrialService.trialFromMap(trialRequest);
  }
}
