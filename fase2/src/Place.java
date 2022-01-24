import javax.xml.stream.Location;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Place implements Reviewable {
    private String id;
    private String name;
    private String category;
    private String location;
    private String city;
    private Map<String,Review> reviews;  // chave: userId, objeto: review

    // ainda falta alterar as variaveis, exceto a variavel name, porque o resto das variaveis penso que temos que
    //ser nós a escrever
    public Place(String name){
        this.id = "";
        this.name = name;
        this.category = "";
        //this.location = null;
        this.city = "";
        this.reviews = new HashMap<>();
    }

    public Place(String id,String name, String category, String location, String city, Map<String, Review> reviews){
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

    public Place(String id, String name, String category, String location, String city) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.city = city;
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

    public String getLocation(){
        return this.location;
    }

    public String getCity(){
        return this.city;
    }

    public float calculateClassification(){
        float total = 0;
        int size = reviews.size();
        for(Review rev: reviews.values()){
            total += rev.getClassification();
        }
        return total / size;
    }

    public Map<String, Review> getReviews(){
        return this.reviews.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public Place clone(){
        return new Place(this);
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void add_review(Review r) {
        this.reviews.put(r.getUserId(),r);
    }

    @Override
    public void remove_review(String userId, String placeId) {
        for (Review r : this.reviews.values()){
            if(r.getPlaceId().equals(placeId) && r.getUserId().equals(userId)) this.reviews.remove(r.getUserId());
        }
    }
}
