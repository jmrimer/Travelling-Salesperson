package edu.louisville.traveler.genetics;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ComponentScan({"edu.louisville.traveler"})
@WebMvcTest
public class GeneticControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void postReturnsTrial() throws Exception {
    City city1 = new City(1, 0, 0);
    City city2 = new City(2, 1, 1);
    City city3 = new City(3, 0, 2);
    City city4 = new City(4, 1, 3);
    List<City> cities = List.of(city1, city2, city3, city4);
    Map map = new Map(cities);

    String mapJSON = new ObjectMapper().writeValueAsString(map);

    this.mockMvc.perform(MockMvcRequestBuilders
      .post("/api/genetic-trial")
      .content(mapJSON)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }
}