package org.example.dao;

import java.sql.*;

import static org.example.constant.DBConstant.*;

public class UserDAO {

    public static void fetchUsers() throws SQLException {
        Connection localConn = DriverManager.getConnection(LOCAL_DB_URL, LOCAL_DB_USER, LOCAL_DB_PASS);
        Statement stmt = localConn.createStatement();
        String sql = "SELECT * FROM USER";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("Id " + rs.getInt("id") + "Name " + rs.getString("name") + ", Email: " + rs.getString("email")
                    + ", Phone: " + rs.getString("phone")+ ", Address: " + rs.getString("address"));
        }
        rs.close();
        stmt.close();
        localConn.close();
    }

    public static String fetchUserById(int id) throws SQLException{
        Connection localConn = DriverManager.getConnection(LOCAL_DB_URL, LOCAL_DB_USER, LOCAL_DB_PASS);
        String sql = "SELECT * FROM USER where id = ?";
        PreparedStatement pstmt = localConn.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();

        int recordCount = 0;
        String userName = "";
        while (rs.next()) {
            recordCount++;
            userName = rs.getString("name");
        }
        if(recordCount != 1){
            userName = "Invalid user id";
            throw new RuntimeException("Invalid user id");
        }

        rs.close();
        pstmt.close();
        localConn.close();

        return userName;
    }

}
