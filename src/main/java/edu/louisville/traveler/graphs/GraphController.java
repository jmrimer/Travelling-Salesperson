package edu.louisville.traveler.graphs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GraphController {
  @Autowired
  private final GraphService graphService;

  public GraphController(GraphService graphService) {
    this.graphService = graphService;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/api/traverse-graph-with-bfs")
  public @ResponseBody List<Node> shortestPathViaBFS(
    @RequestBody GraphRequest graphRequest
  ) {
    return this.graphService.bfsPathFromMatrix(
      graphRequest.getAdjacencyMatrix(),
      graphRequest.getStart(),
      graphRequest.getEnd()
    );
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/api/traverse-graph-with-dfs")
  public @ResponseBody List<Node> shortestPathViaDFS(
    @RequestBody GraphRequest graphRequest
  ) {
    return this.graphService.dfsPathFromMatrix(
      graphRequest.getAdjacencyMatrix(),
      graphRequest.getStart(),
      graphRequest.getEnd()
    );
  }
}
