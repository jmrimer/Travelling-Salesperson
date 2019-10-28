package edu.louisville.traveler.maps;

import edu.louisville.traveler.genetics.Intersection;
import edu.louisville.traveler.genetics.LivingTour;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class MapHelpers {
  public static Point2D centerOf(Map map) {
    double sumX = 0;
    double sumY = 0;
    for (City city : map.getCities()) {
      sumX += city.latitude;
      sumY += city.longitude;
    }
    return new Point2D.Double(
      sumX / map.getCities().size(),
      sumY / map.getCities().size()
    );
  }

  public static PolarCoordinates mapPolarPointFromCenter(City city, Point2D center) {
    double opposite = city.getLongitude() - center.getY();
    double adjacent = city.getLatitude() - center.getX();
    double theta = Math.toDegrees(Math.atan(opposite / adjacent)) + quadrantAdjustment(adjacent, opposite);
    double r = Math.sqrt(Math.pow(adjacent, 2) + Math.pow(opposite, 2));

    return new PolarCoordinates(r, theta);
  }

  private static double quadrantAdjustment(double xVector, double yVector) {
    switch (findQuadrant(xVector, yVector)) {
      case 1:
        return 0;
      case 4:
        return 360;
      default:
        return 180;
    }
  }

  private static int findQuadrant(double xVector, double yVector) {
    if (xVector < 0 && yVector > 0) {
      return 2;
    } else if (xVector < 0 && yVector < 0) {
      return 3;
    } else if (xVector > 0 && yVector < 0) {
      return 4;
    }
    return 1;
  }

  public static boolean edgesIntersect(Edge edge1, Edge edge2) {
    City p1 = edge1.getStart();
    City q1 = edge1.getEnd();
    City p2 = edge2.getStart();
    City q2 = edge2.getEnd();

    if (edgesShareCities(p1, q1, p2, q2)) return false;
// Find the four orientations needed for general and
    // special cases
    int orientation1 = orientation(p1, q1, p2);
    int orientation2 = orientation(p1, q1, q2);
    int orientation3 = orientation(p2, q2, p1);
    int orientation4 = orientation(p2, q2, q1);

    // General case
    if (orientation1 != orientation2 && orientation3 != orientation4) return true;

// Special Cases
    // p1, q1 and p2 are collinear and p2 lies on segment p1q1
    if (orientation1 == 0 && onSegment(p1, p2, q1)) return true;

    // p1, q1 and q2 are collinear and q2 lies on segment p1q1
    if (orientation2 == 0 && onSegment(p1, q2, q1)) return true;

    // p2, q2 and p1 are collinear and p1 lies on segment p2q2
    if (orientation3 == 0 && onSegment(p2, p1, q2)) return true;

    // p2, q2 and q1 are collinear and q1 lies on segment p2q2
    return orientation4 == 0 && onSegment(p2, q1, q2);// Doesn't fall in any of the above cases   }
  }

  private static boolean edgesShareCities(City p1, City q1, City p2, City q2) {
    return p1.equals(p2) || p1.equals(q2) || q1.equals(p2) || q1.equals(q2);
  }

  // To find orientation of ordered triplet (p, q, r).
// The function returns following values
// 0 --> p, q and r are collinear
// 1 --> Clockwise
// 2 --> Counterclockwise
  static int orientation(City p, City q, City r) {

    // See https://www.geeksforgeeks.org/orientation-3-ordered-points/
    // for details of below formula.
    double val = (q.longitude - p.longitude) * (r.latitude - q.latitude) -
      (q.latitude - p.latitude) * (r.longitude - q.longitude);

    if (val == 0) return 0; // collinear

    return (val > 0) ? 1 : 2; // clock or counter clock wise
  }

  /*
  code sourced from: https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
 */
// Given three collinear points p, q, r, the function checks if
// point q lies on line segment 'pr'
  static boolean onSegment(City p, City q, City r) {
    return q.latitude <= Math.max(p.latitude, r.latitude)
      && q.latitude >= Math.min(p.latitude, r.latitude)
      && q.longitude <= Math.max(p.longitude, r.longitude)
      && q.longitude >= Math.min(p.longitude, r.longitude);
  }

  public static List<Edge> convertCityListToEdges(List<City> cycle) {
    List<Edge> edges = new ArrayList<>();
    for (int i = 0; i < cycle.size() - 1; i++) {
      edges.add(new Edge(cycle.get(i), cycle.get(i + 1)));
    }
    return edges;
  }

  public static List<Intersection> findIntersections(LivingTour child) {
    List<Intersection> intersections = new ArrayList<>();
    List<Edge> edges = convertCityListToEdges(child.getCycle());
    Iterator<Edge> edgeIterator = edges.iterator();
    while (edgeIterator.hasNext()) {
      Edge edge = edgeIterator.next();
      for (Edge e : edges) {
        if (edgesIntersect(edge, e) && !edge.equals(e)) {
          intersections.add(new Intersection(edge, e));
        }
      }
      edgeIterator.remove();
    }
    return intersections;
  }

  City findNearestCity(City start, List<City> cities) {
    City nearestCity = null;
    float bestDistance = Float.MAX_VALUE;
    List<City> citiesWithoutStartingCity = new ArrayList<>(cities);
    citiesWithoutStartingCity.remove(start);
    for (City city : citiesWithoutStartingCity) {
      double distance = calculateDistance(start, city);
      if (distance < bestDistance) {
        bestDistance = (float) distance;
        nearestCity = city;
      }
    }
    return nearestCity;
  }

  CityAndEdge findNearestCity(HashSet<Edge> edges, List<City> cities) {
    Edge closestEdge;
    City nearestCity = null;
    double bestDistance = Float.MAX_VALUE;
    List<Edge> closestEdges = new ArrayList<>();
    for (City city : cities) {
      for (Edge edge : edges) {
        double currDistance = calculateDistance(city, edge);
        if (currDistance < bestDistance) {
          bestDistance = currDistance;
          nearestCity = city;
        }
      }
    }
    for (Edge edge : edges) {
      assert nearestCity != null;
      double currDistance = calculateDistance(nearestCity, edge);
      if (currDistance == bestDistance) {
        closestEdges.add(edge);
      }
    }
    closestEdge = closestEdges.get(0);
    if (closestEdges.size() > 1) {
      closestEdge = determineClosestEdgeWhenSharedEndpoints(closestEdges, nearestCity);
    }
    return new CityAndEdge(nearestCity, closestEdge);
  }

  public Edge determineClosestEdgeWhenSharedEndpoints(List<Edge> closestEdges, City closestCity) {
    for (Edge edge1 : closestEdges) {
      for (Edge edge2 : closestEdges) {
        if (edge1.getStart().equals(edge2.getEnd())) {
          Point2D.Double edge1Point = generateNewPointOnLineFromEndpoint(edge1, edge1.getStart());
          Point2D.Double edge2Point = generateNewPointOnLineFromEndpoint(edge2, edge2.getEnd());
          if (point1CloserThanPoint2(closestCity, edge1Point, edge2Point)) {
            return edge1;
          } else {
            return edge2;
          }
        }
      }
    }
    return null;
  }

  private boolean point1CloserThanPoint2(City closestCity, Point2D.Double edge1Point, Point2D.Double edge2Point) {
    return calculateDistance(closestCity, new City(-1, edge1Point.x, edge1Point.y)) < calculateDistance(closestCity, new City(-1, edge2Point.x, edge2Point.y));
  }

  Point2D.Double generateNewPointOnLineFromEndpoint(Edge edge, City endpoint) {
    double x1;
    double x2;
    double y1;
    double y2;
    if (endpoint.equals(edge.getEnd())) {
      x1 = edge.getEnd().getLatitude();
      x2 = edge.getStart().getLatitude();
      y1 = edge.getEnd().getLongitude();
      y2 = edge.getStart().getLongitude();
    } else {
      x1 = edge.getStart().getLatitude();
      x2 = edge.getEnd().getLatitude();
      y1 = edge.getStart().getLongitude();
      y2 = edge.getEnd().getLongitude();
    }
    Point2D.Double vector = new Point2D.Double((x2 - x1), (y2 - y1));
    double length = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    Point2D.Double normalizedVector = new Point2D.Double(
      vector.x / length,
      vector.y / length
    );
    return new Point2D.Double(
      x1 + (normalizedVector.x),
      y1 + (normalizedVector.y)
    );
  }

  double calculateDistance(City start, City end) {
    return Point2D.distance(
      start.latitude,
      start.longitude,
      end.latitude,
      end.longitude
    );
  }

  /* Credit for the algorithm to calculate distance from a point to an edge goes to:
  Joshua's answer at
  https://stackoverflow.com/questions/849211/shortest-distance-between-a-point-and-a-line-segment
   */
  double calculateDistance(City city, Edge edge) {
    double x0 = city.latitude;
    double y0 = city.longitude;
    double x1 = edge.getStart().latitude;
    double y1 = edge.getStart().longitude;
    double x2 = edge.getEnd().latitude;
    double y2 = edge.getEnd().longitude;

    double A = x0 - x1;
    double B = y0 - y1;
    double C = x2 - x1;
    double D = y2 - y1;

    double dot = A * C + B * D;
    double lengthSquared = C * C + D * D;
    double param = -1;
    if (lengthSquared != 0) {
      param = dot / lengthSquared;
    }

    double xx, yy;

    if (param < 0) {
      xx = x1;
      yy = y1;
    } else if (param > 1) {
      xx = x2;
      yy = y2;
    } else {
      xx = x1 + param * C;
      yy = y1 + param * D;
    }

    double dx = x0 - xx;
    double dy = y0 - yy;
    return Math.sqrt(dx * dx + dy * dy);
  }

  public static City cityBefore(City start, List<City> cycle) {
    int cityIndex = cycle.indexOf(start);
    if (cityIndex < 0) {
      return start;
    }
    if (cityIndex == 0) {
      return cycle.get(cycle.size() - 2);
    }
    return cycle.get(cityIndex - 1);
  }

  public static City cityAfter(City start, List<City> cycle) {
    int cityIndex = cycle.indexOf(start);
    if (cityIndex == cycle.size() - 1) {
      return cycle.get(1);
    }
    return cycle.get(cityIndex + 1);
  }
}
