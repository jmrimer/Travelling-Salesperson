package edu.louisville.traveler.hashi;

import org.springframework.stereotype.Service;

@Service
public class HashiService {
  HashiMap staticMap() {
    HashiMap hashiMap = new HashiMap(7);
    hashiMap.getIslands().add(new Island(new Coordinates(0, 0), 3));
    hashiMap.getIslands().add(new Island(new Coordinates(0, 3), 2));
    hashiMap.getIslands().add(new Island(new Coordinates(1, 1), 3));
    hashiMap.getIslands().add(new Island(new Coordinates(1, 6), 3));
    hashiMap.getIslands().add(new Island(new Coordinates(2, 0), 2));
    hashiMap.getIslands().add(new Island(new Coordinates(3, 2), 1));
    hashiMap.getIslands().add(new Island(new Coordinates(3, 6), 4));
    hashiMap.getIslands().add(new Island(new Coordinates(5, 1), 3));
    hashiMap.getIslands().add(new Island(new Coordinates(5, 5), 1));
    hashiMap.getIslands().add(new Island(new Coordinates(6, 0), 2));
    hashiMap.getIslands().add(new Island(new Coordinates(6, 2), 3));
    hashiMap.getIslands().add(new Island(new Coordinates(6, 4), 3));
    hashiMap.getIslands().add(new Island(new Coordinates(6, 6), 2));
    return hashiMap;
  }
}
