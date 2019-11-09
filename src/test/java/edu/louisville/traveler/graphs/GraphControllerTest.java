package edu.louisville.traveler.graphs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ComponentScan({"edu.louisville.traveler"})
@WebMvcTest
public class GraphControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void postReturnsBFSRouteFromAdjacencyMatrix() throws Exception {
    boolean[][] adjacencyMatrix = new boolean[3][3];
    adjacencyMatrix[0][1] = true;
    adjacencyMatrix[1][2] = true;

    Node startingNode = new Node(1);
    Node endingNode = new Node(3);
    GraphRequest graphRequest = new GraphRequest(adjacencyMatrix, startingNode, endingNode);

    String graphRequestJSON = new ObjectMapper().writeValueAsString(graphRequest);

    this.mockMvc.perform(MockMvcRequestBuilders
      .post("/api/traverse-graph-with-bfs")
      .content(graphRequestJSON)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("[0].id", is(1)))
      .andExpect(jsonPath("[1].id", is(2)))
      .andExpect(jsonPath("[2].id", is(3)));
  }

  @Test
  public void postReturnsDFSRouteFromAdjacencyMatrix() throws Exception {
    boolean[][] adjacencyMatrix = new boolean[3][3];
    adjacencyMatrix[0][1] = true;
    adjacencyMatrix[1][2] = true;

    Node startNode = new Node(1);
    Node endNode = new Node(3);
    GraphRequest graphRequest = new GraphRequest(adjacencyMatrix, startNode, endNode);

    String graphRequestJSON = new ObjectMapper().writeValueAsString(graphRequest);

    this.mockMvc.perform(MockMvcRequestBuilders
      .post("/api/traverse-graph-with-dfs")
      .content(graphRequestJSON)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("[0].id", is(1)))
      .andExpect(jsonPath("[1].id", is(2)))
      .andExpect(jsonPath("[2].id", is(3)));
  }
}