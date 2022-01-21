package GuiaTuristicoLN;

import java.util.*;

public class SSPlacesFacade implements IGestPlace {
    private Map<String,Place> places;  // chave: placeId, objeto: GuiaTuristicoLN.Place

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
    public Set<Place> filter_by_distance(float distance_max){
        /**
         * FALTA IMPLEMENTAR A CLASSE LOCATION
        Set<GuiaTuristicoLN.Place> placesByCategory = new TreeSet<GuiaTuristicoLN.Place>();
        // distance is valid?
        for(GuiaTuristicoLN.Place pl: places.values()){
            if(pl.getLocation().equals(category))
                placesByCategory.add(pl);
        }
        return placesByCategory;
         */
        return null;
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
