//package edu.louisville.traveler.genetics;
//
//import edu.louisville.traveler.maps.City;
//import edu.louisville.traveler.maps.Tour;
//
//import java.util.List;
//
//public class CompatibleParentSelector implements ParentSelector {
//  @Override
//  public LivingTour[] selectFromPopulace(List<LivingTour> population) {
//
//    return new LivingTour[0];
//  }
//
//  private static int[] indexOfSingleCompatibleGenePair(Tour parent1, Tour parent2) {
//    City fromCity = parent1.getCycle().get(0);
//    for (int i = 0; i < parent1.getCycle().size() - 1; i++) {
//      City toCity = parent1.getCycle().get(i + 1);
//      int indexFromOtherParent = otherParentSharesGenePair(parent2, fromCity, toCity);
//      if (compatible(indexFromOtherParent)) {
//        return new int[]{i, indexFromOtherParent};
//      }
//      fromCity = toCity;
//    }
//    return new int[]{-1, -1};
//  }
//
//  private static int otherParentSharesGenePair(Tour otherParent, City fromCity, City toCity) {
//    int indexOfFromCity = otherParent.getCycle().indexOf(fromCity);
//    City nextCity = otherParent.getCycle().get(indexOfFromCity + 1);
//    return nextCity.equals(toCity) ? indexOfFromCity : -1;
//  }
//
//
//  static boolean compatible(Tour parent1, Tour parent2) {
//    return indexOfSingleCompatibleGenePair(parent1, parent2)[0] > -1;
//  }
//
//  private static boolean compatible(int sequenceStart) {
//    return sequenceStart > -1;
//  }
//}
