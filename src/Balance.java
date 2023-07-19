import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Balance{
    int id;
    String name;
    int quantity;

    public Balance(
        int id,
        String name,
        int quantity
    )throws SQLException{
        this.id = id;
        this.name = name;
        this.quantity = quantity;

        PreparedStatement stmt = DAO.createConnection().prepareStatement(
            "INSERT INTO balance (id, name, quantity) VALUES (?, ?, ?)"
        );
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setInt(3, quantity);
        stmt.executeUpdate();
        stmt.close();
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getQuantity(){
        return quantity;
    }
    public int setId(int id){
        this.id = id;
        return id;
    }
    public String setName(String name){
        this.name = name;
        return name;
    }
    public int setQuantity(int quantity){
        this.quantity = quantity;
        return quantity;
    }
    
    public static void listBalance() throws SQLException{
        Connection conn = DAO.createConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT * FROM balance"
        );
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            System.out.println(
                rs.getInt("id") + " " +
                rs.getString("name") + " " +
                rs.getInt("quantity")
            );
        }
        rs.close();
        stmt.close();
    }
    
    public static void updateBalance() throws SQLException{
        Connection conn = DAO.createConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "UPDATE balance SET quantity = ? WHERE id = ?"
        );
        stmt.setInt(1, 0);
        stmt.setInt(2, 1);
        stmt.executeUpdate();
        stmt.close();
    }

    public static void deleteBalance() throws SQLException{
        Connection conn = DAO.createConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM balance WHERE id = ?"
        );
        stmt.setInt(1, 1);
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public String toString(){
        return "Id: " + id + "\n"
        + "Name: " + name + "\n"
        + "Quantity: " + quantity + "\n";
    }
    @Override
    public boolean equals(Object obj){
        if(obj == null || obj instanceof Balance){
            return false;
        }
        final Balance other = (Balance) obj;
        return this.getId() == other.getId();
    }


}