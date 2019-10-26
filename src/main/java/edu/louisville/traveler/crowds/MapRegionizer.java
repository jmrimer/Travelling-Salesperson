package edu.louisville.traveler.crowds;

import edu.louisville.traveler.maps.City;
import edu.louisville.traveler.maps.Map;
import edu.louisville.traveler.maps.MapHelpers;
import edu.louisville.traveler.maps.PolarCoordinates;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MapRegionizer {
  public static List<Map> regionize(Map map, int regionCount) {
    List<Map> regions = new ArrayList<>();
    Point2D center = MapHelpers.centerOf(map);
    for (int i = 0; i < regionCount; i++) {
      Map region = new Map(new ArrayList<>());
      double thetaStart = i * (360 / (double) regionCount);
      double thetaEnd = (i + 1) * (360 / (double) regionCount);

      for (City city : map.getCities()) {
        PolarCoordinates polarCoordinates = MapHelpers.mapPolarPointFromCenter(city, center);
        if (thetaStart <= polarCoordinates.getTheta() && polarCoordinates.getTheta() < thetaEnd) {
          region.getCities().add(city);
        }
      }
      regions.add(region);
    }
    return regions;
  }
}
