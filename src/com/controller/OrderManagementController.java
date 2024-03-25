package com.controller;


import com.model.Product;
import com.model.User;
import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;
import com.service.OrderManagementService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManagementController {
    private final OrderManagementService orderService;
    private final Scanner scanner;

    public OrderManagementController(OrderManagementService orderService, Scanner scanner) {
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Create User");
            System.out.println("2. Create Product");
            System.out.println("3. Create Order");
            System.out.println("4. Cancel Order");
            System.out.println("5. Get All Products");
            System.out.println("6. Get Orders By User");
            System.out.println("7. Exit");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    createProduct();
                    break;
                case 3:
                    createOrder();
                    break;
                case 4:
                    cancelOrder();
                    break;
                case 5:
                    getAllProducts();
                    break;
                case 6:
                    getOrdersByUser();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void createUser() {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter role:");
        String role = scanner.nextLine();

        User user = new User(userId, username, password, role);
        orderService.createUser(user);
        System.out.println("User created successfully.");
    }

    private void createProduct() {
        System.out.println("Enter product name:");
        String productName = scanner.nextLine();
        System.out.println("Enter description:");
        String description = scanner.nextLine();
        System.out.println("Enter price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter quantity in stock:");
        int quantityInStock = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter type:");
        String type = scanner.nextLine();

        Product product = new Product( productName, description, price, quantityInStock, type);
        orderService.createProduct(null, product); 
        System.out.println("Product created successfully.");
    }

    private void createOrder() {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        scanner.nextLine(); 
        User user = new User(); 
        System.out.println("Enter number of products:");
        int numProducts = scanner.nextInt();
        scanner.nextLine(); 
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < numProducts; i++) {
            System.out.println("Enter product ID for product " + (i + 1) + ":");
            int productId = scanner.nextInt();
            scanner.nextLine(); 
            Product product = new Product(); 
            products.add(product);
        }

        try {
            orderService.createOrder(user, products);
            System.out.println("Order created successfully.");
        } catch (UserNotFoundException e) {
            System.out.println("User not found: " + e.getMessage());
        }
    }

    private void cancelOrder() {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        System.out.println("Enter order ID:");
        int orderId = scanner.nextInt();

        try {
            orderService.cancelOrder(userId, orderId);
            System.out.println("Order cancelled successfully.");
        } catch (OrderNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllProducts() {
        List<Product> products = orderService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("All Products:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private void getOrdersByUser() {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        scanner.nextLine();
        User user = new User();
        List<Product> orders = orderService.getOrderByUser(user);
        if (orders.isEmpty()) {
            System.out.println("No orders found for user ID: " + userId);
        } else {
            System.out.println("Orders for User ID: " + userId);
            for (Product order : orders) {
                System.out.println(order);
            }
        }
    }

}