import javax.xml.stream.Location;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Place {
    private String id;
    private String name;
    private String category;
    private Location location;
    private String city;
    private Map<String,Review> reviews;  // chave: userId, objeto: review

    public Place(String id,String name,String category,Location location,String city,Map<String,Review> reviews){
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
        this.city = city;
        this.reviews = new HashMap<>(reviews);
    }

    public Place(Place place){
        this.id = place.getId();
        this.name = place.getName();
        this.category = place.getCategory();
        this.location = place.getLocation();
        this.city = place.getCity();
        this.reviews = place.getReviews();
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getCategory(){
        return this.category;
    }

    public Location getLocation(){
        return this.location;
    }

    public String getCity(){
        return this.city;
    }

    public Map<String,Review> getReviews(){
        return this.reviews.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public Place clone(){
        return new Place(this);
    }
}
