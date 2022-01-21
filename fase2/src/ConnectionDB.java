import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConnectionDB {
    private Connection connection;

    public ConnectionDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GuiaTuristico","root","root");
    }

    public void closeConnectionDB() throws SQLException {
        this.connection.close();
    }

    // Loading data from database when it starts up
    public Map<String,User> loadUsers() throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery(" SELECT * FROM users");
        Map<String, User> res = new HashMap<>();
        while(rs.next()) {
            User u = new User(rs.getString(2), String.valueOf(rs.getInt(1)),
                    rs.getString(3), rs.getString(4), rs.getString(5));
            res.put(String.valueOf(rs.getInt(1)), u);
        }
        statement.close();
        return res;
    }

    public Map<String,Place> loadPlaces() throws SQLException{
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM places");
        Map<String,Place> res = new HashMap<>();
        while(rs.next()){
            Place p = new Place(String.valueOf(rs.getInt(1)), rs.getString(2),
                    rs.getString(4),rs.getString(5),rs.getString(6));
            res.put(String.valueOf(rs.getInt(1)), p);
        }
        statement.close();
        return res;
    }

    public Map<String, Plan> loadPlans() throws SQLException{
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM plans");
        Map<String, Plan> res = new HashMap<>();
        while(rs.next()){
            Plan p = new Plan(rs.getString(1), rs.getTime(2),
                    rs.getTime(3),rs.getString(4));
            res.put(String.valueOf(rs.getInt(5)),p);
        }
        statement.close();
        return res;
    }

    public Map<String,Review> loadReviews() throws SQLException{
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT  * FROM reviews");
        Map<String, Review> res = new HashMap<>();
        while(rs.next()){
            Review r = new Review(String.valueOf(rs.getInt(4)),String.valueOf(rs.getInt(5)),
                    Float.parseFloat(rs.getString(2)), rs.getString(3));
            res.put(String.valueOf(rs.getInt(4)),r);
        }
        statement.close();
        return res;
    }

    //Storing data to database when it shuts down
    public void saveUsers(Map<String,User> allUsers) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE users SET name=? , password= ? , " +
                "email=? , location=? WHERE id = ?");
        for (User u: allUsers.values()) {
            preparedStatement.setString(1,u.getName());
            preparedStatement.setString(2,u.getPassword());
            preparedStatement.setString(3,u.getEmail());
            preparedStatement.setString(4,"Braga");
            preparedStatement.setInt(5,Integer.parseInt(u.getId()));
            preparedStatement.executeUpdate();
        }
    }
}
