package edu.louisville.traveler.hashi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashiService {
  HashiMap staticMap() {
    HashiMap hashiMap = new HashiMap(
      7,
      List.of(
        new Island(new Coordinates(0, 0), 3),
        new Island(new Coordinates(0, 3), 2),
        new Island(new Coordinates(1, 1), 3),
        new Island(new Coordinates(1, 6), 3),
        new Island(new Coordinates(2, 0), 2),
        new Island(new Coordinates(3, 2), 1),
        new Island(new Coordinates(3, 6), 4),
        new Island(new Coordinates(5, 1), 3),
        new Island(new Coordinates(5, 5), 1),
        new Island(new Coordinates(6, 0), 2),
        new Island(new Coordinates(6, 2), 3),
        new Island(new Coordinates(6, 4), 3),
        new Island(new Coordinates(6, 6), 2)
      )
    );

    return hashiMap;
  }
}
