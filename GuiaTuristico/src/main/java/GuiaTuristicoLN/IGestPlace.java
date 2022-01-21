package GuiaTuristicoLN;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public interface IGestPlace {
    Map<String, Place> getPlaces();
    TreeSet<Place> placesOfCity(String city);
    Set<Place> filter_by_name(String name);
    Set<Place> filter_by_category(String category);
    Set<Place> filter_by_distance(float distance_max);
    Set<Place> filter_by_classification(float classification_min);
    List<Review> get_reviews_by_place(String placeId);
}
