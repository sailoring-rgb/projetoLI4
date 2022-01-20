import java.util.*;

public class SSPlacesFacade implements IGestPlace{
    private Map<String,Place> places;  // chave: placeId, objeto: Place

    public SSPlacesFacade(Map<String,User> users){
        this.places = new HashMap<>(places);
    }

    public SSPlacesFacade(SSPlacesFacade facade){
        this.places = facade.getPlaces();
    }

    public Map<String,Place> getPlaces() {
        return this.places;
    }

    @Override
    public TreeSet<Place> places(String city) {
        return null;
    }

    @Override
    public void filter_by_name(String name, Set<Place> places) {

    }

    @Override
    public void filter_by_category(String category, Set<Place> places) {

    }

    @Override
    public void filter_by_distance(float distance_max, Set<Place> places) {

    }

    @Override
    public void filter_by_classification(float classification_min, Set<Place> places) {

    }

    @Override
    public List<Review> get_reviews_by_place(String placeId) {
        return null;
    }
}
