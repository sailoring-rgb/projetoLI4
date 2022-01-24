package GuiaTuristicoLN;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Place implements Reviewable, Comparable {
    private String id;
    private String name;
    private String category;
    private String location;
    private String city;
    private Map<String, Review> reviews;  // chave: userId, objeto: review

    public Place(String name) {
        this.id = "";
        this.name = name;
        this.category = "";
        this.location = "";
        this.city = "";
        this.reviews = new HashMap<>();
    }

    public Place(String id, String name, String category, String location, String city, Map<String, Review> reviews) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
        this.city = city;
        this.reviews = new HashMap<>(reviews);
    }

    public Place(Place place) {
        this.id = place.getId();
        this.name = place.getName();
        this.category = place.getCategory();
        this.location = place.getLocation();
        this.city = place.getCity();
        this.reviews = place.getReviews();
    }

    public Place(String id, String name, String category, String location, String city) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.city = city;
        this.location = location;
        this.reviews = new HashMap<>();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public String getLocation() {
        return this.location;
    }

    public String getCity() {
        return this.city;
    }

    public void setId(String id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setCategory(String category){
        this.category=category;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setCity(String city){
        this.city=city;
    }

    public void setReviews(Map<String,Review> reviews){
        this.reviews = new HashMap<>(reviews);
    }


    public float calculateClassification() {
        float total = 0;
        int size = reviews.size();
        for (Review rev : reviews.values()) {
            total += rev.getClassification();
        }
        return total / size;
    }

    public Map<String, Review> getReviews() {
        return this.reviews.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone()));
    }

    public Place clone() {
        return new Place(this);
    }

    @Override
    public void add_review(Review r) {
        this.reviews.put(r.getUserId(), r);
    }

    @Override
    public void remove_review(String userId, String placeId) {
        for (Review r : this.reviews.values()) {
            if (r.getPlaceId().equals(placeId) && r.getUserId().equals(userId)) this.reviews.remove(r.getUserId());
        }
    }

    @Override
    public int compareTo(Object that) {
        Place p = (Place) that;
        return this.id.compareTo(p.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(id, place.id) && Objects.equals(name, place.name) && Objects.equals(category, place.category) && Objects.equals(location, place.location) && Objects.equals(city, place.city) && Objects.equals(reviews, place.reviews);
    }
}
