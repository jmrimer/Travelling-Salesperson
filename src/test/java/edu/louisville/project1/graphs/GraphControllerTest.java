package edu.louisville.project1.graphs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ComponentScan({"edu.louisville.project1"})
@WebMvcTest
public class GraphControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GraphService graphService;

  @Test
  public void postReturnsBFSRouteFromAdjacencyMatrix() throws Exception {
    boolean[][] adjacencyMatrix = new boolean[3][3];
    Node startingNode = new Node(1);
    List<Node> path = List.of(startingNode, new Node(2));

    when(graphService.bfsPathFromMatrix(adjacencyMatrix, startingNode))
      .thenReturn(path);

    String matrixJSON = new ObjectMapper().writeValueAsString(adjacencyMatrix);
    String nodeJSON = new ObjectMapper().writeValueAsString(startingNode);
    String putJSON = matrixJSON.concat("," + nodeJSON);
    String pathJSON = new ObjectMapper().writeValueAsString(path);

    this.mockMvc.perform(MockMvcRequestBuilders
      .post("/api/traverse-graph-with-bfs")
      .content(putJSON)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
    )
      .andExpect(status().isOk())
      .andExpect(content().string(pathJSON));
  }
}