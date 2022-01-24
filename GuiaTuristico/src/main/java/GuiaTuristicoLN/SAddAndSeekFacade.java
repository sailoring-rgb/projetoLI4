package GuiaTuristicoLN;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class SAddAndSeekFacade implements IGestAddAndSeek {
    private String currentUser;
    private boolean functional;
    private IGestUser ssUsers;
    private IGestPlace ssPlaces;

    public SAddAndSeekFacade() {
        this.currentUser = "";
        this.functional = true;
    }

    @Override
    public boolean add_favourite(String userId, String placeId) {
        if (this.ssUsers.getUsers().containsKey(userId)) {
            Place place = this.ssPlaces.getPlaces().get(placeId);
            this.ssUsers.getUsers().get(userId).getFavourites().add(place.clone());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove_favourite(String userId, String placeId) {
        if (this.ssUsers.getUsers().containsKey(userId)) {
            Place place = this.ssPlaces.getPlaces().get(placeId);
            this.ssUsers.getUsers().get(userId).getFavourites().remove(place);
            return true;
        }
        return false;
    }

    public boolean login(String id, String password) throws IOException {
        boolean res = false;
        if (this.ssUsers.getUsers().containsKey(id)) {
            this.currentUser = id;
            User user = this.ssUsers.getUsers().get(id);
            if (password.equals(user.getPassword())) {
                res = true;
            }
        }
        return res;
    }

    public boolean register(String id, String password, String name, String email) {
        if (!this.ssUsers.getUsers().containsKey(id)) {
            User user = new User(id, password, name, email);
            this.currentUser = id;
            this.ssUsers.getUsers().put(this.currentUser, user.clone());
            return true;
        }
        return false;
    }

    public void logout() {
        this.functional = false;
    }

    public void startUp() throws SQLException, ClassNotFoundException {
        ConnectionDB database = new ConnectionDB();
        Map<String, User> allUsers = database.loadUsers();
        Map<String, Place> allPlaces = database.loadPlaces();
        Map<String, Review> allReviews = database.loadReviews();
        Map<String, Plan> allPlans = database.loadPlans();

        for (Plan p : allPlans.values())
            allUsers.get(p.getUserID()).add_plan(p);
        for (Review r : allReviews.values()) {
            allPlaces.get(r.getPlaceId()).add_review(r);
            allUsers.get(r.getUserId()).add_review(r);
        }
        database.closeConnectionDB();
        this.ssUsers = new SSUserFacade(allUsers);
        this.ssPlaces = new SSPlacesFacade(allPlaces);
        for (User user : this.ssUsers.getUsers().values()) {
            Map<String, Plan> plansOfUser = new HashMap<>();      // chave: userId
            Map<String, Review> reviewsOfUser = new HashMap<>();  // chave: userId

            Plan plan = allPlans.get(user.getId());
            Review rev = allReviews.get(user.getId());

            plansOfUser.put(plan.getName(), plan.clone());
            reviewsOfUser.put(rev.getPlaceId(), rev.clone());
            user.setPlans(plansOfUser);
            user.setReviews(reviewsOfUser);
        }
    }

    public void shutDown() throws SQLException, ClassNotFoundException, ParseException {
        ConnectionDB database = new ConnectionDB();
        database.saveUsers(this.ssUsers.getUsers());
        database.savePlaces(this.ssPlaces.getPlaces());
        for (User user : this.ssUsers.getUsers().values()) {
            Map<String, Plan> plansOfUser = new HashMap<>(); // chave: userId
            Map<String, Review> reviewsOfUser = new HashMap<>(); // chave: userId
            for (Plan plan : user.getPlans().values()) {
                plansOfUser.put(user.getId(), plan.clone());
            }
            for (Review rev : user.getReviews().values()) {
                reviewsOfUser.put(user.getId(), rev.clone());
            }
            database.savePlans(plansOfUser);
            database.saveReviews(reviewsOfUser);
        }
    }
}
