package edu.louisville.project1.maps;

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

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ComponentScan({"edu.louisville.project1"})
@WebMvcTest
public class MapControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MappingService mappingService;

  @Test
  public void getReturnsRouteAndWeight() throws Exception {
    WeightedRoute route = new WeightedRoute(
      List.of(new City(1, 0f, 0f)),
      0f
    );

    when(mappingService.route())
      .thenReturn(route);

    String routeJSON = new ObjectMapper().writeValueAsString(route);

    this.mockMvc.perform(get("/api/weighted-route"))
      .andExpect(status().isOk())
      .andExpect(content().string(routeJSON));
  }

  @Test
  public void postReturnsRouteAndWeight() throws Exception {
    WeightedRoute route = new WeightedRoute(
      List.of(new City(1, 0f, 0f)),
      0f
    );


    Map map = new Map(List.of(new City(1, 1f, 1f), new City(2, 2f, 2f)));

    when(mappingService.routeFromMap(map))
      .thenReturn(route);

    String mapJSON = new ObjectMapper().writeValueAsString(map);
    String routeJSON = new ObjectMapper().writeValueAsString(route);

    this.mockMvc.perform(MockMvcRequestBuilders
      .post("/api/weighted-route")
      .content(mapJSON)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string(routeJSON));
  }

}