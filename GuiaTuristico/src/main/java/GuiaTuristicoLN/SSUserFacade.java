package GuiaTuristicoLN;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SSUserFacade implements IGestUser {
    private ConnectionDB db = new ConnectionDB();
    private Map<String, User> users;    // chave: userId, objeto: user

    public SSUserFacade() throws SQLException, ClassNotFoundException {
        this.users = db.loadUsers();
    }

    public Map<String,Plan> loadPlansUser(Map<String,Plan> plans, String userId){
        Map<String,Plan> plansOfUser = new HashMap<>();
        Plan plan = plans.get(userId);
        plansOfUser.put(plan.getName(),plan.clone());
        return plansOfUser;
    }

    public SSUserFacade(Map<String, User> users) throws SQLException, ClassNotFoundException {
        this.users = new HashMap<>(users);
    }

    public SSUserFacade(SSUserFacade facade) throws SQLException, ClassNotFoundException {
        this.users = facade.getUsers();
    }

    public Map<String, User> getUsers() {
        return this.users;
    }

    public boolean create_review(String userId, String placeId, float classification, String comment) {
        if (this.users.containsKey(userId)) {
            Review rev = new Review(userId, placeId, classification, comment);
            this.users.get(userId).getReviews().put(placeId, rev);
            return true;
        } else return false;
    }

    @Override
    public List<Review> get_reviews_by_user(String userId) {
        if (this.users.containsKey(userId)) {
            return this.users.get(userId).getReviews().values().stream().map(Review::clone).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public boolean create_plan(String userId, String name, LocalDateTime start_time, LocalDateTime finish_time, String day, String city) {
        //falta validar porcarias
        if (this.users.containsKey(userId)) {
            Plan plan = new Plan(userId, name, start_time, finish_time, day, city);
            this.users.get(userId).getPlans().put(name, plan);
        }
        return false;
    }

    @Override
    public boolean update_plan(String userId, Plan plan) {
        if (this.users.containsKey(userId) && this.users.get(userId).getPlans().containsKey(plan.getName())) {
            this.users.get(userId).getPlans().put(plan.getName(), plan);
            return true;
        } else return false;
    }

    @Override
    public boolean remove_plan(String userId, String planName) {
        if (this.users.containsKey(userId)) {
            this.users.get(userId).remove_plan(planName);
            return true;
        } else return false;
    }

    @Override
    public User get_user(String userId) {
        return this.users.getOrDefault(userId, null);
    }

    @Override
    public boolean update_user(User user) {
        if (this.users.containsKey(user.getId())) {
            this.users.put(user.getId(), user);
            return true;
        } else return false;
    }

    @Override
    public boolean delete_review(String userId, String placeId) {
        if (this.users.containsKey(userId)) {
            this.users.get(userId).getReviews().remove(placeId);
            return true;
        } else return false;
    }

}
