package org.example.dao;

import java.sql.*;

import static org.example.constant.DBConstant.*;

public class InventoryDAO {

    public static void fetchInventory() throws SQLException {
        Connection gcpConn = DriverManager.getConnection(GCP_DB_URL, GCP_DB_USER, GCP_DB_PASS);
        Statement stmt = gcpConn.createStatement();
        String sql = "SELECT * FROM Inventory";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("Item ID: " + rs.getInt("item_id") + ", Item Name: " + rs.getString("item_name")
                    + ", Available Quantity: " + rs.getInt("available_quantity"));
        }
        rs.close();
        stmt.close();
        gcpConn.close();
    }



    public void insertInventory(int item_id, String itemName,int available_quantity) throws SQLException {
        Connection gcpConn = DriverManager.getConnection(GCP_DB_URL, GCP_DB_USER, GCP_DB_PASS);
        String sql = "INSERT INTO Inventory (item_id, item_name, available_quantity) VALUES (?,?,?)";
        PreparedStatement pstmt = gcpConn.prepareStatement(sql);
        pstmt.setInt(1, item_id);
        pstmt.setString(2, itemName);
        pstmt.setInt(3, available_quantity);
        pstmt.executeUpdate();
        pstmt.close();
        gcpConn.close();
    }

    public static void updateInventory(String itemName, int quantity) throws SQLException {
        Connection gcpConn = DriverManager.getConnection(GCP_DB_URL, GCP_DB_USER, GCP_DB_PASS);
        String sql = "UPDATE Inventory SET available_quantity = available_quantity - ? WHERE item_name = ?";
        PreparedStatement pstmt = gcpConn.prepareStatement(sql);
        pstmt.setInt(1, quantity);
        pstmt.setString(2, itemName);
        pstmt.executeUpdate();
        pstmt.close();
        gcpConn.close();
        System.out.println("Inventory updated successfully.");
    }

}
