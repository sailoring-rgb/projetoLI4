package GuiaTuristicoLN;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public interface IGestPlace {
    Map<String, Place> getPlaces();

    Set<Place> placesOfCity(String city);

    Set<Place> filter_by_name(String name);

    Set<Place> filter_by_category(String category);

    Set<Place> filter_by_distance(String locationUser, double distance_max);

    Set<Place> filter_by_classification(float classification_min);

    List<Place> filter_by_city(String  city);

    List<Review> get_reviews_by_place(String placeId);
}
