package GuiaTuristicoLN;

import java.sql.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionDB {
    private Connection connection;

    public ConnectionDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GuiaTuristico", "root", "root");
    }

    public void closeConnectionDB() throws SQLException {
        this.connection.close();
    }

    // Loading data from database when it starts up
    public Map<String, User> loadUsers() throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery(" SELECT * FROM users");
        Map<String, User> res = new HashMap<>();
        while (rs.next()) {
            User u = new User(rs.getString(2), String.valueOf(rs.getInt(1)),
                    rs.getString(3), rs.getString(4), rs.getString(5));
            res.put(String.valueOf(rs.getInt(1)), u);
        }
        statement.close();
        return res;
    }

    public Map<String, Place> loadPlaces() throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM places");
        Map<String, Place> res = new HashMap<>();
        while (rs.next()) {
            Place p = new Place(String.valueOf(rs.getInt(1)), rs.getString(2),
                    rs.getString(4), rs.getString(5), rs.getString(6));
            res.put(String.valueOf(rs.getInt(1)), p);
        }
        statement.close();
        return res;
    }

    public Map<String, Plan> loadPlans() throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM plans");
        Map<String, Plan> res = new HashMap<>();
        while (rs.next()) {
            Plan p = new Plan(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(),
                    rs.getTimestamp(3).toLocalDateTime(), rs.getString(4), rs.getString(5));
            res.put(String.valueOf(rs.getInt(5)), p);
        }
        statement.close();
        return res;
    }

    public Map<String, Review> loadReviews() throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT  * FROM reviews");
        Map<String, Review> res = new HashMap<>();
        while (rs.next()) {
            Review r = new Review(String.valueOf(rs.getInt(4)), String.valueOf(rs.getInt(5)),
                    Float.parseFloat(rs.getString(2)), rs.getString(3));
            res.put(String.valueOf(rs.getInt(4)), r);
        }
        statement.close();
        return res;
    }

    //Storing data to database when it shuts down
    public void saveUsers(Map<String, User> allUsers) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE users SET name=? , password= ? , " +
                "email=? , location=? , id=? WHERE id = ?");
        for (User u : allUsers.values()) {
            preparedStatement.setString(1, u.getName());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setString(3, u.getEmail());
            preparedStatement.setString(4, u.getLocation());
            preparedStatement.setInt(5, Integer.parseInt(u.getId()));
            preparedStatement.setInt(6, Integer.parseInt(u.getId()));
            preparedStatement.executeUpdate();
        }
    }
    public void saveOneUser(User u) throws SQLException {
        PreparedStatement pStatement = this.connection.prepareStatement("INSERT into users(name, password, email, location, id) values (? , ? , " +
                "? , ? , ?)");
        pStatement.setString(1, u.getName());
        pStatement.setString(2, u.getPassword());
        pStatement.setString(3, u.getEmail());
        pStatement.setString(4, u.getLocation());
        pStatement.setInt(5, Integer.parseInt(u.getId()));
        pStatement.executeUpdate();
    }

    public void savePlaces(Map<String, Place> allPlaces) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE places SET name=? , placeId= ? , " +
                "category=? , location=? , city=? , id=? WHERE id = ?");
        for (Place p : allPlaces.values()) {
            preparedStatement.setString(1, p.getName());
            preparedStatement.setString(2, p.getId());
            preparedStatement.setString(3, p.getCategory());
            preparedStatement.setString(4, p.getLocation());
            preparedStatement.setString(5, p.getCity());
            preparedStatement.setInt(6, Integer.parseInt(p.getId()));
            preparedStatement.setInt(7, Integer.parseInt(p.getId()));
            preparedStatement.executeUpdate();
        }
    }

    public void saveOnePlace(Place p) throws SQLException{
        PreparedStatement pStatement = this.connection.prepareStatement("INSERT into places(name, placeId, category, location, city, id) values (? , ? , " +
                "? , ? , ? , ?)");
        pStatement.setString(1, p.getName());
        pStatement.setString(2, p.getId());
        pStatement.setString(3, p.getCategory());
        pStatement.setString(4, p.getLocation());
        pStatement.setString(5, p.getCity());
        pStatement.setInt(6, Integer.parseInt(p.getId()));
        pStatement.executeUpdate();
    }

    public void savePlans(Map<String, Plan> allPlans) throws SQLException, ParseException {
        PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE plans SET name=? , startTime= ? , " +
                "finishTime=? , city=? , users_id=? WHERE users_id = ?");
        for (Plan p : allPlans.values()) {
            preparedStatement.setString(1, p.getName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(p.getStartTime()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(p.getFinishTime()));
            preparedStatement.setString(4, p.getCity());
            preparedStatement.setString(5, p.getUserID());
            preparedStatement.setString(6, p.getUserID());
            preparedStatement.executeUpdate();
        }
    }

    public void saveOnePlan(Plan p) throws SQLException {
        PreparedStatement pStatement = this.connection.prepareStatement("INSERT into plans(name, startTime, finishTime, city, users_id) values (? ,  ? , " +
                "? , ? , ? )");
        pStatement.setString(1, p.getName());
        pStatement.setTimestamp(2, Timestamp.valueOf(p.getStartTime()));
        pStatement.setTimestamp(3, Timestamp.valueOf(p.getFinishTime()));
        pStatement.setString(4, p.getCity());
        pStatement.setString(5, p.getUserID());
        pStatement.executeUpdate();
    }

    public void saveReviews(Map<String, Review> allReviews) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE reviews SET placeName=? , classification= ? , " +
                "text=? , users_id=? , places_id =? WHERE users_id = ? && places_id=?");
        for (Review r : allReviews.values()) {
            preparedStatement.setString(1, r.getPlaceId());
            preparedStatement.setString(2, String.valueOf(r.getClassification()));
            preparedStatement.setString(3, r.getComment());
            preparedStatement.setString(4, r.getUserId());
            preparedStatement.setString(5, r.getPlaceId());
            preparedStatement.setString(6, r.getUserId());
            preparedStatement.setString(7, r.getPlaceId());
            preparedStatement.executeUpdate();
        }
    }

    public void saveOneReview(Review r) throws SQLException{
        PreparedStatement pStatement  = this.connection.prepareStatement("INSERT INTO reviews(classification, text, users_id, places_id ) values  (? , ? , " +
                "? , ?)");
        pStatement.setString(1, String.valueOf(r.getClassification()));
        pStatement.setString(2, r.getComment());
        pStatement.setString(3, r.getUserId());
        pStatement.setString(4, r.getPlaceId());
        pStatement.executeUpdate();
    }

    public void updateOnePlan(Plan p) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE plans SET name=? , startTime= ? , " +
                "finishTime=? , city=? , users_id=? WHERE users_id = ?");
        preparedStatement.setString(1, p.getName());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(p.getStartTime()));
        preparedStatement.setTimestamp(3, Timestamp.valueOf(p.getFinishTime()));
        preparedStatement.setString(4, p.getCity());
        preparedStatement.setString(5, p.getUserID());
        preparedStatement.setString(6, p.getUserID());
        preparedStatement.executeUpdate();
    }

    public void updateOneReview(Review r) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE reviews SET placeName=? , classification= ? , " +
                "text=? , users_id=? , places_id =? WHERE users_id = ? && places_id=?");
        preparedStatement.setString(1, r.getPlaceId());
        preparedStatement.setString(2, String.valueOf(r.getClassification()));
        preparedStatement.setString(3, r.getComment());
        preparedStatement.setString(4, r.getUserId());
        preparedStatement.setString(5, r.getPlaceId());
        preparedStatement.setString(6, r.getUserId());
        preparedStatement.setString(7, r.getPlaceId());
        preparedStatement.executeUpdate();

    }
}
