package GuiaTuristicoLN;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IGestUser {
    Map<String, User> getUsers();

    void saveUser(User u) throws SQLException;

    void savePlan(Plan p) throws SQLException;

    void savePlace(Place p) throws SQLException;

    void saveReview(Review r) throws SQLException;

    boolean create_review(String userId, String placeName, float classification, String comment);

    List<Review> get_reviews_by_user(String userId);

    boolean create_plan(String userId, String name, LocalDateTime start_time, LocalDateTime finish_time, String day, String city);

    boolean update_plan(String userId, Plan plan);

    boolean remove_plan(String userId, String planName);

    User get_user(String userId);

    boolean update_user(User user);

    boolean delete_review(String userId, String placeId);

    Review getReviewUserPlace(String userId, String placeId);

    boolean login(String id, String password);

    boolean register(String password, String name, String email);

    public List<Plan> get_plans_by_user(String userId);
}
