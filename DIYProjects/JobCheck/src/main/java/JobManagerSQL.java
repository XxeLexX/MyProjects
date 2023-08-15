import java.sql.*;

public class JobManagerSQL {
    public void connectDB() {
        String url = "jdbc:mysql://localhost:3306/OfferCheck";
        String username = "root";
        String password = "password";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

}
