package edu.louisville.project1.graphs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GraphController {
  @Autowired
  private final GraphService graphService;

  public GraphController(GraphService graphService) {
    this.graphService = graphService;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping("api/traverse-graph-with-bfs")
  List<Node> shortestPathViaBFS(
    @RequestBody boolean[][] adjacencyMatrix,
    Node start
  ) {
    return this.graphService.bfsPathFromMatrix(adjacencyMatrix, start);
  }
}
