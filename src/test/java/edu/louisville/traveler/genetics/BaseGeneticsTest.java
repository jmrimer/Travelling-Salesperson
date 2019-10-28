package edu.louisville.traveler.genetics;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BaseGeneticsTest {
  public int startingParentsCount = 128;
  public int populationCap = 128;
  public int totalGenerations = (int) (Math.pow(2, 9));
  public int maxGeneSequenceLength = 32;
  public double mutationChance = 0;

  Trial trial;
  public City city1 = new City(1, 87.951292, 2.658162);
  public City city2 = new City(2, 33.466597, 66.682943);
  public City city3 = new City(3, 91.778314, 53.807184);
  public City city4 = new City(4, 20.526749, 47.633290);
  public City city5 = new City(5, 9.006012, 81.185339);
  public City city6 = new City(6, 20.032350, 2.761925);
  public City city7 = new City(7, 77.181310, 31.922361);
  public City city8 = new City(8, 41.059603, 32.578509);
  public City city9 = new City(9, 18.692587, 97.015290);
  public City city10 = new City(10, 51.658681, 33.808405);
  private City city11 = new City(11, 44.563128, 47.541734);
  private City city12 = new City(12, 37.806330, 50.599689);
  private City city13 = new City(13, 9.961241, 20.337535);
  private City city14 = new City(14, 28.186895, 70.415357);
  private City city15 = new City(15, 62.129582, 6.183050);
  private City city16 = new City(16, 50.376904, 42.796106);
  private City city17 = new City(17, 71.285134, 43.671987);
  private City city18 = new City(18, 34.156316, 49.113437);
  private City city19 = new City(19, 85.201575, 71.837519);
  private City city20 = new City(20, 27.466659, 1.394696);
  private City city21 = new City(21, 97.985778, 44.746239);
  private City city22 = new City(22, 40.730003, 98.400830);
  private City city23 = new City(23, 73.799860, 61.076693);
  private City city24 = new City(24, 85.076449, 17.029328);
  private City city25 = new City(25, 16.052736, 11.899167);
  private City city26 = new City(26, 20.160527, 67.238380);
  private City city27 = new City(27, 22.730186, 99.725333);
  private City city28 = new City(28, 77.196570, 88.503677);
  private City city29 = new City(29, 18.494217, 31.971191);
  private City city30 = new City(30, 72.743919, 16.071047);
  private City city31 = new City(31, 4.153569, 41.981262);
  private City city32 = new City(32, 79.027680, 95.034639);
  private City city33 = new City(33, 14.145329, 40.690329);
  private City city34 = new City(34, 66.258736, 70.360424);
  private City city35 = new City(35, 22.656941, 52.076785);
  private City city36 = new City(36, 82.680746, 31.058687);
  private City city37 = new City(37, 88.995025, 35.560167);
  private City city38 = new City(38, 87.939085, 36.567278);
  private City city39 = new City(39, 82.845546, 48.393200);
  private City city40 = new City(40, 5.371258, 3.466903);
  private City city41 = new City(41, 80.028687, 51.258889);
  private City city42 = new City(42, 8.908353, 80.703146);
  private City city43 = new City(43, 69.411298, 10.122990);
  private City city44 = new City(44, 10.129093, 91.378521);
  private City city45 = new City(45, 61.546678, 97.531053);
  private City city46 = new City(46, 61.156041, 69.313639);
  private City city47 = new City(47, 39.719840, 46.403394);
  private City city48 = new City(48, 38.999603, 68.407239);
  private City city49 = new City(49, 43.992431, 59.556871);
  private City city50 = new City(50, 26.963103, 73.021638);
  private City city51 = new City(51, 28.879666, 27.948851);
  private City city52 = new City(52, 58.751183, 87.429426);
  private City city53 = new City(53, 85.290078, 60.875271);
  private City city54 = new City(54, 40.879543, 32.523576);
  private City city55 = new City(55, 67.326884, 81.203650);
  private City city56 = new City(56, 19.064913, 27.845088);
  private City city57 = new City(57, 14.648885, 88.753929);
  private City city58 = new City(58, 4.153569, 87.118137);
  private City city59 = new City(59, 10.895108, 44.978179);
  private City city60 = new City(60, 23.258156, 5.346843);
  private City city61 = new City(61, 68.926054, 82.073428);
  private City city62 = new City(62, 11.713004, 65.706351);
  private City city63 = new City(63, 83.404035, 89.590136);
  private City city64 = new City(64, 11.471908, 44.187750);
  private City city65 = new City(65, 41.422773, 81.743828);
  private City city66 = new City(66, 91.595202, 40.324107);
  private City city67 = new City(67, 31.730094, 98.501541);
  private City city68 = new City(68, 56.382946, 11.935789);
  private City city69 = new City(69, 43.232521, 43.571276);
  private City city70 = new City(70, 56.904813, 42.152165);
  private City city71 = new City(71, 93.386639, 12.457656);
  private City city72 = new City(72, 71.395001, 16.754662);
  private City city73 = new City(73, 77.065340, 13.657033);
  private City city74 = new City(74, 70.278024, 40.021973);
  private City city75 = new City(75, 76.604511, 36.146123);
  private City city76 = new City(76, 31.351665, 67.159032);
  private City city77 = new City(77, 23.563341, 66.295358);
  private City city78 = new City(78, 20.822779, 81.447798);
  private City city79 = new City(79, 52.903836, 7.309183);
  private City city80 = new City(80, 5.746635, 94.280831);
  private City city81 = new City(81, 40.147099, 4.345836);
  private City city82 = new City(82, 13.583789, 96.127201);
  private City city83 = new City(83, 62.181463, 28.403577);
  private City city84 = new City(84, 4.409925, 36.637471);
  private City city85 = new City(85, 72.331919, 22.144230);
  private City city86 = new City(86, 71.483505, 61.964782);
  private City city87 = new City(87, 30.283517, 60.740989);
  private City city88 = new City(88, 35.721915, 87.408063);
  private City city89 = new City(89, 77.556688, 30.884732);
  private City city90 = new City(90, 49.781793, 33.585620);
  private City city91 = new City(91, 99.078341, 81.115146);
  private City city92 = new City(92, 77.309488, 4.168828);
  private City city93 = new City(93, 61.522263, 46.504105);
  private City city94 = new City(94, 63.026826, 33.359783);
  private City city95 = new City(95, 69.045076, 0.952177);
  private City city96 = new City(96, 59.254738, 81.203650);
  private City city97 = new City(97, 27.005829, 40.083010);
  private City city98 = new City(98, 24.509415, 4.898221);
  private City city99 = new City(99, 54.347362, 47.959838);
  private City city100 = new City(100, 59.797967, 84.215827);
  public Map map5 = new Map(List.of(
    city1,
    city2,
    city3,
    city4,
    city5
  ));

  public Map map10 = new Map(List.of(
    city1,
    city2,
    city3,
    city4,
    city5,
    city6,
    city7,
    city8,
    city9,
    city10
  ));

  public Map map100 = new Map(List.of(
    city1,
    city2,
    city3,
    city4,
    city5,
    city6,
    city7,
    city8,
    city9,
    city10,
    city11,
    city12,
    city13,
    city14,
    city15,
    city16,
    city17,
    city18,
    city19,
    city20,
    city21,
    city22,
    city23,
    city24,
    city25,
    city26,
    city27,
    city28,
    city29,
    city30,
    city31,
    city32,
    city33,
    city34,
    city35,
    city36,
    city37,
    city38,
    city39,
    city40,
    city41,
    city42,
    city43,
    city44,
    city45,
    city46,
    city47,
    city48,
    city49,
    city50,
    city51,
    city52,
    city53,
    city54,
    city55,
    city56,
    city57,
    city58,
    city59,
    city60,
    city61,
    city62,
    city63,
    city64,
    city65,
    city66,
    city67,
    city68,
    city69,
    city70,
    city71,
    city72,
    city73,
    city74,
    city75,
    city76,
    city77,
    city78,
    city79,
    city80,
    city81,
    city82,
    city83,
    city84,
    city85,
    city86,
    city87,
    city88,
    city89,
    city90,
    city91,
    city92,
    city93,
    city94,
    city95,
    city96,
    city97,
    city98,
    city99,
    city100
  ));

  @Before
  public void setup() {
    trial = new Trial();
  }

  List<LivingTour> finalPopulation;

  public void logResults(Trial trial, long duration, long timestamp) {
    finalPopulation = trial.getGenerations().get(trial.getGenerations().size() - 1).getPopulation();
    testLength();

    LivingTour child = trial.bestChild();
    System.out.println("best child weight: " + child.getWeight() + " & size: " + new HashSet<>(child.getCycle()).size());
    try {
      FileWriter csvWriter = new FileWriter("./testlogs/" + "Consolidated" + this.getClass().getSimpleName() + timestamp + ".csv", true);
      String row = timestamp + "," + child.getWeight() + "," + duration;
      csvWriter.append(row).append("\n");
      csvWriter.flush();
      csvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testLength() {
    for (LivingTour livingTour : finalPopulation) {
      assertEquals(101, livingTour.getCycle().size());
    }
  }

  public City cityAfter(City start, List<City> cycle) {
    int cityIndex = cycle.indexOf(start);
    if (cityIndex == cycle.size() - 1) {
      return cycle.get(1);
    }
    return cycle.get(cityIndex + 1);
  }

  public City cityBefore(City start, List<City> cycle) {
    int cityIndex = cycle.indexOf(start);
    if (cityIndex == 0) {
      return cycle.get(cycle.size() - 2);
    }
    return cycle.get(cityIndex - 1);
  }
}
