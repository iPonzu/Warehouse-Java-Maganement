import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Warehouse{
    private int id;
    private String name;
    private String address;

    public Warehouse(
        int id,
        String name,
        String address
    )throws SQLException{
        this.id = id;
        this.name = name;
        this.address = address;

        PreparedStatement stmt = DAO.createConnection().prepareStatement(
            "INSERT INTO warehouse (id, name, address) VALUES (?, ?, ?)"
        );
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setString(3, address);
        stmt.executeUpdate();
        stmt.close();
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public int setId(int id){
        this.id = id;
        return id;
    }
    public String setName(String name){
        this.name = name;
        return name;
    }
    public String setAddress(String address){
        this.address = address;
        return address;
    }

    public static void listWarehouse() throws SQLException{
        Connection conn = DAO.createConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT * FROM warehouse"
        );
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            System.out.println(
                rs.getInt("id") + " " +
                rs.getString("name") + " " +
                rs.getString("address")
            );
        }
        rs.close();
        stmt.close();
    }
    public static void deleteWarehouse() throws SQLException{
        Connection conn = DAO.createConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM warehouse WHERE id = ?"
        );
        stmt.setInt(1, 1);
        stmt.executeUpdate();
        stmt.close();
    }
    public static void updateWarehouse() throws SQLException{
        Connection conn = DAO.createConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "UPDATE warehouse SET name = ?, address = ? WHERE id = ?"
        );
        stmt.setString(1, "Warehouse 1");
        stmt.setString(2, "Address 1");
        stmt.setInt(3, 1);
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public String toString(){
        return "Id: " + id + "\n"
        + "Name: " + name + "\n"
        + "Address: " + address + "\n";
    }
    @Override
    public boolean equals(Object obj){
        if(obj == null || obj instanceof Warehouse){
            return false;
        }
        final Warehouse other = (Warehouse) obj;
        return this.getId() == other.getId();
    }


}
