package edu.louisville.traveler.hashi;

import org.junit.Before;

public class BaseHashiTest {
  public HashiSolver hashiSolver;
  public HashiMap hashiMap7x7Easy;
  public HashiMap hashiMap7x7Empty;
  public Island island_0_0_3;
  public Island island_0_3_2;
  public Island island_6_2_3;
  public Island island_6_4_3;
  public Island island_5_2_3;

  @Before
  public void setup() {
    hashiSolver = new HashiSolver();
    hashiMap7x7Empty = new HashiMap(7);

    hashiMap7x7Easy = new HashiMap(7);
    island_0_0_3 = new Island(new Coordinates(0, 0), 3);
    island_0_3_2 = new Island(new Coordinates(0, 3), 2);
    island_5_2_3 = new Island(new Coordinates(5, 1), 3);
    island_6_2_3 = new Island(new Coordinates(6, 2), 3);
    island_6_4_3 = new Island(new Coordinates(6, 4), 3);

    hashiMap7x7Easy.add(island_0_0_3);
    hashiMap7x7Easy.add(island_0_3_2);
    hashiMap7x7Easy.add(new Island(new Coordinates(1, 1), 3));
    hashiMap7x7Easy.add(new Island(new Coordinates(1, 6), 3));
    hashiMap7x7Easy.add(new Island(new Coordinates(2, 0), 2));
    hashiMap7x7Easy.add(new Island(new Coordinates(3, 2), 1));
    hashiMap7x7Easy.add(new Island(new Coordinates(3, 6), 4));
    hashiMap7x7Easy.add(island_5_2_3);
    hashiMap7x7Easy.add(new Island(new Coordinates(5, 5), 1));
    hashiMap7x7Easy.add(new Island(new Coordinates(6, 0), 2));
    hashiMap7x7Easy.add(island_6_2_3);
    hashiMap7x7Easy.add(island_6_4_3);
    hashiMap7x7Easy.add(new Island(new Coordinates(6, 6), 2));
  }}
