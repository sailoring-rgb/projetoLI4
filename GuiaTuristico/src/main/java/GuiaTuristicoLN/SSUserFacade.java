package GuiaTuristicoLN;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SSUserFacade implements IGestUser {
    private Map<String, User> users;    // chave: userId, objeto: user

    private Logger log = LoggerFactory.getLogger(SSUserFacade.class);


    ConnectionDB db = new ConnectionDB();


    public SSUserFacade() throws SQLException, ClassNotFoundException {
        this.users = db.loadUsers();
        for(User user: this.users.values()){
            Map<String,Plan> plansOfUser = new HashMap<>();      // chave: userId
            Map<String,Review> reviewsOfUser = new HashMap<>();  // chave: userId

            Plan plan = db.loadPlans().get(user.getId());
            Review rev = db.loadReviews().get(user.getId());

            if (plan != null) {
                plansOfUser.put(plan.getName(),plan.clone());
                //log.info(plan.getName());
            }

            if ( rev != null) {
                reviewsOfUser.put(rev.getPlaceId(),rev.clone());
            }

            user.setPlans(plansOfUser);
            user.setReviews(reviewsOfUser);
        }/*
        for(User user: this.users.values()){
            for(Plan p: user.getPlans().values()){
            log.info(p.getName());}
        }*/
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
            Map<String, Plan>  plans = this.users.get(userId).getPlans();
            for(Plan p : plans.values()){
                remove_plan(userId,p.getName());
            }
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

    @Override
    public Review getReviewUserPlace(String userId, String placeId){
        if (this.users.containsKey(userId)){
            return this.users.get(userId).getReviews().get(placeId).clone();
        }
        else return null;
    }

}
