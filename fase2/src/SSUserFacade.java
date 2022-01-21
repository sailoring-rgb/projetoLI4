import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SSUserFacade implements IGestUser{
    private Map<String,User> users;    // chave: userId, objeto: user

    public SSUserFacade(Map<String,User> users){
        this.users = new HashMap<>(users);
    }

    public SSUserFacade(SSUserFacade facade){
        this.users = facade.getUsers();
    }

    public Map<String,User> getUsers() {
        return this.users;
    }

    @Override
    public boolean create_review(String userId, String placeName, float classification, String comment) {
        if(this.users.containsKey(userId)){
            Review rev = new Review(userId,,classification,comment); //placeId
            this.users.get(userId).getReviews().put(placeName, rev);
            return true;
        }
        else return false;
    }

    @Override
    public List<Review> get_reviews_by_user(String userId) {
        if(this.users.containsKey(userId)){
            List<Review> reviews = new ArrayList<Review>();
            return reviews = this.users.get(userId).getReviews().values().stream().map(Review::clone).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public boolean create_plan(String userId, LocalTime start_time, LocalTime finish_time, LocalDate date, String day, String city) {
        if(this.users.containsKey(userId)){
            Plan plan = new Plan(start_time, finish_time, day, city);
            this.users.get(userId).getPlans().put(name, plan);
        }
        return false;
    }

    @Override
    public void update_plan(String userId, Plan plan) {
        if(this.users.containsKey(userId)){
            this.users.get(userId).getPlans().put(plan.getName(), plan);
        }
    }

    @Override
    public void remove_plan(String userId, String planName) {
        if(this.users.containsKey(userId)){
            this.users.get(userId).remove_plan(planName);
        }
    }

    @Override
    public User get_user(String userId) {
        if(this.users.containsKey(userId)){
            return this.users.get(userId);
        }
        else return null;
    }

    @Override
    public void update_user(User user) {
        if(this.users.containsKey(user.getId())){
            this.users.put(user.getId(),user);
        }
    }

    @Override
    public void delete_review(String userId, String placeId) {
        if(this.users.containsKey(userId)){
            this.users.get(userId).remove_review(placeId);
        }
    }

    @Override
    public void add_favourite(String userId, String placeId) {
        if(this.users.containsKey(userId)){

        }
    }

    @Override
    public void remove_favourite(String userId, String placeId) {
        if(this.users.containsKey(userId)){
            for(Place place : this.users.get(userId).getFavourites()){
                if(place.getId().equals(placeId)){
                    this.users.get(userId).getFavourites().remove(place);
                }
            }
        }
    }
}
