package org.example.dao;

import java.sql.*;

import static org.example.constant.DBConstant.*;

public class OrderDAO {

    public void fetchOrder() throws SQLException {
        Connection localConn = DriverManager.getConnection(LOCAL_DB_URL, LOCAL_DB_USER, LOCAL_DB_PASS);
        Statement stmt = localConn.createStatement();
        String sql = "SELECT * FROM ORDER_INFO";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("order_id " + rs.getInt("order_id") + "user_id " + rs.getInt("user_id") + ", Item Name: " + rs.getString("item_name")
                    + ", Available Quantity: " + rs.getInt("quantity")+ ", Date: " + rs.getDate("order_date"));
        }
        rs.close();
        stmt.close();
        localConn.close();
    }

    public static void insertOrder(int userId, String itemName, int quantity) throws SQLException {
        Connection localConn = DriverManager.getConnection(LOCAL_DB_URL, LOCAL_DB_USER, LOCAL_DB_PASS);
        String sql = "INSERT INTO Order_info (user_id, item_name, quantity, order_date) VALUES (?, ?, ?, NOW())";
        PreparedStatement pstmt = localConn.prepareStatement(sql);
        pstmt.setInt(1, userId);
        pstmt.setString(2, itemName);
        pstmt.setInt(3, quantity);
        pstmt.executeUpdate();
        pstmt.close();
        localConn.close();
    }
}
