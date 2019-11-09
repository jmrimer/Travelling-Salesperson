package edu.louisville.traveler.maps;

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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ComponentScan({"edu.louisville.traveler"})
@WebMvcTest
public class MapControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void postReturnsRouteAndWeight() throws Exception {
    City city1 = new City(1, 0.0d, 0.0d);
    City city2 = new City(2, 3.0d, 4.0d);
    City city3 = new City(3, 0d, 8.0d);
    City city4 = new City(4, -3.0d, 4.0d);
    List<City> route = List.of(city1, city4, city3, city2, city1);
    Tour tour = new Tour(route, 20f);
    Map map = new Map(List.of(city1, city2, city3, city4));

    String mapJSON = new ObjectMapper().writeValueAsString(map);
    String weightedRouteJSON = new ObjectMapper().writeValueAsString(tour);

    this.mockMvc.perform(MockMvcRequestBuilders
      .post("/api/weighted-cycle")
      .content(mapJSON)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string(weightedRouteJSON));
  }

  @Test
  public void postReturnsRouteAndWeightFromInsertionTour() throws Exception {
    City city1 = new City(1, 0, 0);
    City city2 = new City(2, 1, 1);
    City city3 = new City(3, 0, 2);
    City city4 = new City(4, 1, 3);
    List<City> cities = List.of(city1, city2, city3, city4);
    Map map = new Map(cities);

    List<City> route = new ArrayList<>(List.of(city1, city2, city3, city4, city1));
    Tour weigh = new Tour(
      route,
      7.404918347287666
    );

    String mapJSON = new ObjectMapper().writeValueAsString(map);
    String expectedRouteJSON = new ObjectMapper().writeValueAsString(weigh);

    this.mockMvc.perform(MockMvcRequestBuilders
      .post("/api/weighted-cycle-via-insertion")
      .content(mapJSON)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string(expectedRouteJSON));
  }
}