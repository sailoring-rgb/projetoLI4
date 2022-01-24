import java.util.*;

public class SSPlacesFacade implements IGestPlace {
    private Map<String,Place> places;  // chave: placeId, objeto: Place

    public SSPlacesFacade(Map<String, User> users){
        this.places = new HashMap<>(places);
    }

    public SSPlacesFacade(SSPlacesFacade facade){
        this.places = facade.getPlaces();
    }

    public Map<String, Place> getPlaces() {
        return this.places;
    }

    public TreeSet<Place> placesOfCity(String city){
        TreeSet<Place> placesOfCity = new TreeSet<Place>();
        for(Place pl: places.values()){
            if(pl.getCity().equals(city))
                placesOfCity.add(pl);
        }
        return placesOfCity;
    }

    @Override
    public Set<Place> filter_by_name(String name){
        Set<Place> placesByName = new TreeSet<Place>();
        // name is valid?
        for(Place pl: places.values()){
            if(pl.getName().equals(name))
                placesByName.add(pl);
        }
        return placesByName;
    }

    @Override
    public Set<Place> filter_by_category(String category){
        Set<Place> placesByCategory = new TreeSet<Place>();
        // category is valid?
        for(Place pl: places.values()){
            if(pl.getCategory().equals(category))
                placesByCategory.add(pl);
        }
        return placesByCategory;
    }

    @Override
    public Set<Place> filter_by_distance(String locationUser, double distance_max) {
         RandomUtils rand = new RandomUtils();
         Set<GuiaTuristicoLN.Place> placesByLocation = new TreeSet<>();

        double latitudeUser = rand.parseLatitude(locationUser);
        double longitudeUser = rand.parseLongitude(locationUser);

         for(Place pl: places.values()) {
             String locationPlace = pl.getLocation();
             double latitudePlace = rand.parseLatitude(locationPlace);
             double longitudePlace = rand.parseLongitude(locationPlace);
             double distance = rand.calculateDistante(latitudeUser,longitudeUser,latitudePlace,longitudePlace);
             if(distance < distance_max)
                 placesByLocation.add(pl);
         }
         return placesByLocation;
    }

    @Override
    public Set<Place> filter_by_classification(float classification_min){
        Set<Place> placesByClassification = new TreeSet<Place>();
        // distance is valid?
        for(Place pl: places.values()){
            if(pl.calculateClassification() >= classification_min)
                placesByClassification.add(pl);
        }
        return placesByClassification;
    }

    @Override
    public List<Review> get_reviews_by_place(String placeId) {
        return (List<Review>)places.get(placeId).getReviews().values();
    }
}
