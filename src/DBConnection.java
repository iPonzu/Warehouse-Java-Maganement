import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    public static void main(String[] args) {
        try {
            Connection conn = createConnection();

            User user = new User(1, "admin", "admin");
            // INSERT User
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO user (username, name, password) VALUES (?, ?, ?)"
            );
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPassword());
            stmt.execute();

            // SELECT ALL User
            showUsers(conn);""
            // DELETE User
            stmt = conn.prepareStatement(
                    "DELETE FROM user WHERE id = ?"
            );
            stmt.setInt(1, user.getId());
            stmt.execute();
            // UPDATE User
            stmt = conn.prepareStatement(
                    "UPDATE user SET username = ?, name = ?, password = ? WHERE id = ?"
            );
            stmt.setString(1, "user.name");
            stmt.setString(2, "john");
            stmt.setString(3, "123456");
            stmt.setInt(4, user.getId());
            stmt.execute();

            showUsers(conn);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static Connection createConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/warehousemaganement";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }

    private static void showUsers(Connection conn) throws Exception {
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM user"
        );
        ResultSet rs = stmt.executeQuery();
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("password")
            );
            userList.add(user);
        }
        for (User user : userList) {
            System.out.println(user);
        }

        rs.close();
        stmt.close();
    }
}
