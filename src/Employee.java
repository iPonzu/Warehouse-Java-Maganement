import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee{
    private int id;
    private String name;
    private String code;

    public Employee(
        int id,
        String name,
        String code
    )throws SQLException{
        this.id = id;
        this.name = name;
        this.code = code;

        PreparedStatement stmt = DAO.createConnection().prepareStatement(
            "INSERT INTO employee (id, name, code) VALUES (?, ?, ?)"
        );
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setString(3, code);
        stmt.executeUpdate();
        stmt.close();
    }

    public static void listEmployee() throws SQLException{
        Connection conn = DAO.createConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT * FROM employee"
        );
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            System.out.println(
                rs.getInt("id") + " " +
                rs.getString("name") + " " +
                rs.getString("code")
            );
        }
        rs.close();
        stmt.close();
    }

    public static void updateEmployee() throws SQLException{
        Connection conn = DAO.createConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "UPDATE employee SET name = ?, code = ? WHERE id = ?"
        );
        stmt.setString(1, "John");
        stmt.setString(2, "123");
        stmt.setInt(3, 1);
        stmt.executeUpdate();
        stmt.close();
    }
    public static void deleteEmployee() throws SQLException{
        Connection conn = DAO.createConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM employee WHERE id = ?"
        );
        stmt.setInt(1, 1);
        stmt.executeUpdate();
        stmt.close();
    }

        public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getCode(){
        return code;
    }
    public int setId(int id){
        this.id = id;
        return id;
    }
    public String setName(String name){
        this.name = name;
        return name;
    }
    public String setCode(String code){
        this.code = code;
        return code;
    }
    // public static void registerEmployee() throws SQLException{
    //     Connection conn = DAO.createConnection();
    //     PreparedStatement stmt = conn.prepareStatement(
    //         "INSERT INTO employee (id, name, code) VALUES (?, ?, ?)"
    //     );
    //     stmt.setInt(1, 1);
    //     stmt.setString(2, "John");
    //     stmt.setString(3, "123");
    //     stmt.executeUpdate();
    //     stmt.close();
    // }
    @Override
    public String toString(){
        return "Id: " + id + "\n"
        + "Name: " + name + "\n"
        + "Code: " + code + "\n";
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null || obj instanceof Employee){
            return false;
        }
        final Employee other = (Employee) obj;
        return this.getId() == other.getId();
    }
}