import javax.xml.stream.Location;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class User implements Reviewable{

    private String name;
    private String id;
    private String password;
    private String email;
    private Location location;
    private Map<String,Plan> plans; //key->name
    private Map<String,Review> reviews; //key->placeId
    private List<Place> favourites;

    public User(String name, String id, String password, String email){
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public User(String name, String id, String password, String email, Location location, Map<String,Plan> plans, Map<String,Review> reviews, List<Place> favourites){
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.location = location;
        this.plans = plans.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e->e.getValue().clone()));
        this.reviews = reviews.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e->e.getValue().clone()));
        this.favourites = favourites;
    }

    public User(User user){
        this.name = user.getName();
        this.id = user.getId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.location = user.getLocation();
        this.plans = user.getPlans();
        this.reviews = user.getReviews();
        this.favourites = user.getFavourites();
    }

    public String getName(){ return this.name; }

    public String getId(){ return this.id; }

    public String getPassword(){ return this.password; }

    public String getEmail(){ return this.email; }

    public Location getLocation(){ return this.location; }

    public Map<String,Plan> getPlans(){
        return this.plans.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public Map<String,Review> getReviews(){
        return this.reviews.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public List<Place> getFavourites(){ return this.favourites; }

    public User clone(){ return new User(this); }

    public void add_plan(Plan plan){
        this.plans.put(plan.getName(),plan.clone());
    }

    /**
    public void remove_plan(String planName){
        if(this.plans.containsKey(planName)){
            this.plans.remove(planName);
        }
    }

    public void add_favourite(String placeName){
        Place place = new Place(placeName);
        this.favourites.add(place);
    }

    public void remove_favourite(String placeName){
        for(Place p : this.favourites){
            if(p.getName().equals(placeName)){
                this.favourites.remove(p);
            }
        }
    }*/

}
