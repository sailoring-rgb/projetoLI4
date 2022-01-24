import java.sql.SQLException;
import java.util.Map;

public class IgnoreThisClass {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionDB db = new ConnectionDB();

        Map<String, User> usr = db.loadUsers();
        for (User u : usr.values()) System.out.println(u.getLocation());
    }
}

