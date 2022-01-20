import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return false;
    }

    @Override
    public List<Review> get_reviews_by_user(String userId) {
        return null;
    }

    @Override
    public boolean create_plan(String userId, LocalTime start_time, LocalTime finish_time, LocalDate date, String day, String city) {
        return false;
    }

    @Override
    public void update_plan(String userId, Plan plan) {

    }

    @Override
    public void remove_plan(String userId, String planName) {

    }

    @Override
    public User get_user(String userId) {
        return null;
    }

    @Override
    public void update_user(User user) {

    }

    @Override
    public void delete_review(String userId, String placeId) {

    }

    @Override
    public void add_favourite(String userId, String placeId) {

    }

    @Override
    public void remove_favourite(String userId, String placeId) {

    }
}
