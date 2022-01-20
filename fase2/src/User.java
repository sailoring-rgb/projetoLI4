import javax.xml.stream.Location;
import java.util.Map;
import java.util.stream.Collectors;

public class User {

    private String name;
    private String id;
    private String password;
    private String email;
    private Location location;
    private Map<String,Plan> plans; //key->name
    private Map<String,Review> reviews; //key->placeName

    public User(String name, String id, String password, String email, Location location, Map<String,Plan> plans, Map<String,Review> reviews){
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.location = location;
        this.plans = plans.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e->e.getValue().clone()));
        this.reviews = reviews.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    public User(User user){
        this.name = user.getName();
        this.id = user.getId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.location = user.getLocation();
        this.plans = user.getPlans();
        this.reviews = user.getReviews();
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

    public User clone(){ return new User(this); }

    public void add_plan(Plan plan){
        this.plans.put(plan.getName(),plan.clone());
    }

    public void remove_plan(String planName){
        if(this.plans.containsKey(planName)){
            this.plans.remove(planName);
        }
    }

    public void add_favourite(String placeName){
        Review rev = new Review();
        this.reviews.put(placeName, rev);
    }

    public void remove_favourite(String placeName){
        if(this.reviews.containsKey(placeName)){
            this.reviews.remove(placeName);
        }
    }
}
