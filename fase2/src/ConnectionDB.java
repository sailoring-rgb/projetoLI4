import java.sql.*;

public class ConnectionDB {
    private Connection connection;

    public ConnectionDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GuiaTuristico","root","root");
    }

    public ResultSet queryUsers(String operationType) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery(operationType+ " * FROM users");
        return rs;
    }

    public String printSelectUsers(ResultSet rs) throws SQLException {
        StringBuffer sb = new StringBuffer();
        while(rs.next()){
            sb.append(rs.getInt(1)).append(" ").
                    append(rs.getString(2)).append(" ").
                    append(rs.getString(3)).append(" ").
                    append(rs.getString(4)).append(" ").
                    append(rs.getString(5)).append(" ").append("\n");
        }
        return sb.toString();
    }


}
