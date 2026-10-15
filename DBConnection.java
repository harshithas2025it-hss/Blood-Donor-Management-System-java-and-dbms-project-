import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/blood_donor_db";
        String username = "root";
        String password = "Har&221207";

        Connection con = DriverManager.getConnection(
            url,
            username,
            password
        );

        return con;
    }

    public static void main(String[] args) {

        try {
            Connection con = getConnection();

            System.out.println("MySQL connected successfully!");

            con.close();

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            System.out.println(e.getMessage());
        }
    }
}