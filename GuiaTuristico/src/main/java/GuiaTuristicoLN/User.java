package GuiaTuristicoLN;

import java.util.*;
import java.util.stream.Collectors;

public class User implements Reviewable {

    private String name;
    private String id;
    private String password;
    private String email;
    private String location;
    private Map<String, Plan> plans; //key->name
    private Map<String, Review> reviews; //key->placeId
    private List<Place> favourites;

    public User(String name, String id, String password, String email){
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.location = null;
        this.plans = new HashMap<>();
        this.reviews = new HashMap<>();
        this.favourites = new ArrayList<>();
    }

    public User(String name, String id, String password, String email, String location){
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.location = location;
        this.plans = new HashMap<>();
        this.reviews = new HashMap<>();
        this.favourites = new ArrayList<>();
    }

    public User(String name, String id, String password, String email, String location, Map<String, Plan> plans, Map<String, Review> reviews, List<Place> favourites){
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

    public String getLocation(){ return this.location; }

    public Map<String, Plan> getPlans(){
        return this.plans.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public Map<String, Review> getReviews(){
        return this.reviews.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public List<Place> getFavourites(){ return this.favourites; }

    public User clone(){ return new User(this); }

    public void add_plan(Plan plan){
        this.plans.put(plan.getName(),plan.clone());
    }

    public void remove_plan(String planID){
        if(this.plans.containsKey(planID)){
            this.plans.remove(planID);
        }
    }

    public void add_favourite(String placeName){
        Place place = new Place(placeName);
        this.favourites.add(place);
    }

    public void remove_favourite(String placeName) {
        for (Place p : this.favourites) {
            if (p.getName().equals(placeName)) {
                this.favourites.remove(p);
            }
        }
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    @Override
    public void add_review(Review r) {
        this.reviews.put(r.getPlaceId(),r);
    }

    @Override
    public void remove_review(String userId, String placeId) {
        for (Review r : this.reviews.values()){
            if(r.getPlaceId().equals(placeId) && r.getUserId().equals(userId)) this.reviews.remove(r.getUserId());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(id, user.id) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(location, user.location) && Objects.equals(plans, user.plans) && Objects.equals(reviews, user.reviews) && Objects.equals(favourites, user.favourites);
    }
}
