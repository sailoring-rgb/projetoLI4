import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface IGestUser {
    Map<String,User> getUsers();
    boolean create_review(String userId,String placeName,float classification,String comment);
    List<Review> get_reviews_by_user(String userId);
    boolean create_plan(String userId, LocalTime start_time, LocalTime finish_time, LocalDate date, String day, String city);
    void update_plan(String userId,Plan plan);
    void remove_plan(String userId,String planName);
    User get_user(String userId);
    void update_user(User user);
    void delete_review(String userId,String placeId);
    void add_favourite(String userId,String placeId);
    void remove_favourite(String userId,String placeId);
}
