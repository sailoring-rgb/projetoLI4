import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface IGestPlace {
    TreeSet<Place> places(String city);
    void filter_by_name(String name,Set<Place> places);
    void filter_by_category(String category,Set<Place> places);
    void filter_by_distance(float distance_max,Set<Place> places);
    void filter_by_classification(float classification_min,Set<Place> places);
    List<Review> get_reviews_by_place(String placeId);
}
