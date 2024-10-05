package org.example.service;

import org.example.dao.InventoryDAO;
import org.example.dao.OrderDAO;

import java.sql.SQLException;

public class OrderService {

    public static void placeOrder(int userId, String itemName, int quantity) throws SQLException {
        OrderDAO.insertOrder(userId,itemName,quantity);
        InventoryDAO.updateInventory(itemName,quantity);
    }

}
