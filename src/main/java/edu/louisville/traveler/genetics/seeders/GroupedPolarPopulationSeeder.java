package edu.louisville.traveler.genetics.seeders;

import edu.louisville.traveler.genetics.LivingTour;
import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.MapHelpers;
import edu.louisville.traveler.maps.PolarCoordinates;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class GroupedPolarPopulationSeeder extends PolarPopulationSeeder {
  @Override
  public List<LivingTour> seed(int populationSize, Map map) {
    List<LivingTour> ungroupedGeneration = super.seed(populationSize, map);
    return separateIntoGroups(ungroupedGeneration, map);
  }

  private List<LivingTour> separateIntoGroups(List<LivingTour> ungroupedGeneration, Map map) {
    Point2D center = MapHelpers.centerOf(map);

    for (LivingTour livingTour : ungroupedGeneration) {
      LinkedHashMap<City, PolarCoordinates> polarizedCities = new LinkedHashMap<>();
      double sumR = 0;
      for (City city : livingTour.getCycle()) {
        PolarCoordinates polarCoordinates = MapHelpers.mapPolarPointFromCenter(city, center);
        polarizedCities.put(city, polarCoordinates);
        sumR += polarCoordinates.getR();
      }
      double averageR = sumR / livingTour.getCycle().size();

      List<City> groupClose = new ArrayList<>();
      List<City> groupFar = new ArrayList<>();

      for (Entry<City, PolarCoordinates> entry : polarizedCities.entrySet()) {
        if (entry.getValue().getR() < averageR) {
          groupClose.add(entry.getKey());
        } else {
          groupFar.add(entry.getKey());
        }
      }

      groupClose.addAll(groupFar);
      groupClose.add(groupClose.get(0));
      livingTour.setCycle(groupClose);
    }

    return ungroupedGeneration;
  }


}

