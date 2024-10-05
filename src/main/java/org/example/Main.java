package org.example;

import org.example.dao.InventoryDAO;
import org.example.dao.OrderDAO;
import org.example.dao.UserDAO;
import org.example.service.OrderService;

import java.awt.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {

            System.out.println("Hey, Please select the user from the available users");

            //Fetching existing users
            UserDAO.fetchUsers();
            System.out.println();
            Scanner sc = new Scanner(System.in);
            int userId = sc.nextInt();
            System.out.println();

            //Fetch user by id and getting username.
            String userName = UserDAO.fetchUserById(userId);
            System.out.println("Hey, "+userName+"!, Please place an order with that are available in the inventory");

            //Fetching all the items available within the inventory.
            InventoryDAO.fetchInventory();

            //Getting details to place order from the user.
            System.out.println("Enter Item Name: ");
            String item_name = sc.next();
            System.out.println("Enter Quantity: ");
            int quantity = sc.nextInt();
            //Placing order
            OrderService.placeOrder(userId,item_name,quantity);
            System.out.println("Order Place Successfully !!!");

            //Displaying all the items available within the inventory after placing order.
            System.out.println();
            System.out.println("Inventory after placing order.");
            InventoryDAO.fetchInventory();


        }
        catch (SQLException e){
            e.printStackTrace();
        }



    }
}