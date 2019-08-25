package edu.louisville.project1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
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

    this.mockMvc.perform(get("/api/map"))
      .andExpect(status().isOk())
      .andExpect(content().string(routeJSON));
  }
}