package com.controller;


import com.model.Product;
import com.model.User;
import com.exception.OrderNotFoundException;
import com.service.OrderManagementService;
import com.service.OrderManagementServiceImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class OrderManagementController {
	
//    private static OrderManagementService orderService = null;
//  private static Scanner scanner = null;
    
    public static void main(String[] args) throws SQLException {

    	Scanner scanner = new Scanner(System.in);
    	
        OrderManagementService os = new OrderManagementServiceImpl();
      
     
        
        while (true) {
            System.out.println("1. Create User");
            System.out.println("2. Create Product");
            System.out.println("3. Create Order");
            System.out.println("4. Cancel Order");
            System.out.println("5. Get All Products");
            System.out.println("6. Get Orders By User");
            System.out.println("7. Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
             
            switch (choice) {
                case 1:
                	System.out.println("Enter user ID:");
                    int userId = scanner.nextInt();
                   
                    System.out.println("Enter username:");
                    String username = scanner.next();
                    System.out.println("Enter password:");
                    String password = scanner.next(); 
                    System.out.println("Enter role:");
                    String role = scanner.next();
                    User user = new User(userId, username, password, role);                
				try {
					os.createUser(user);
				} catch (Exception e) {
					e.printStackTrace();
				}	
                    System.out.println("User created successfully.");
                    break;
                case 2:
                	System.out.println("Enter product ID:");
                    int productId = scanner.nextInt();
                	  System.out.println("Enter product name:");
                      String productName = scanner.next();
                      System.out.println("Enter description:");
                      String description = scanner.next();
                      System.out.println("Enter price:");
                      double price = scanner.nextFloat();
                      System.out.println("Enter quantity in stock:");
                      int quantityInStock = scanner.nextInt();
                      System.out.println("Enter type:");
                      String type = scanner.next();
                      Product product = new Product( productId ,productName, description, price, quantityInStock, type);
				try {
					os.createProduct(null, product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
                      System.out.println("Product created successfully.");
                    break;
                    
                case 3:
                	System.out.println("Enter user ID:");
                    int userId1 = scanner.nextInt();
				try {
					os.getUserById(userId1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    break;
                case 4:
                	System.out.println("Enter user ID:");
                    int userId11 = scanner.nextInt();
                    System.out.println("Enter order ID:");
                    int orderId = scanner.nextInt();

                    try {
                        os.cancelOrder(userId11, orderId);
                        System.out.println("Order cancelled successfully.");
                    } catch (OrderNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                	List<Product> products1 = os.getAllProducts();
                    if (products1.isEmpty()) {
                        System.out.println("No products found.");
                    } else {
                        System.out.println("All Products:");
                        for (Product product1 : products1) {
                            System.out.println(product1);
                        }
                    }

                    
                    break;
                case 6:
                	 System.out.println("Enter user ID:");
                     int userId111 = scanner.nextInt();
                     User user11 = new User();
				List<Product> orders = null;
				try {
					orders = os.getOrderByUser(user11);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                     if (orders.isEmpty()) {
                         System.out.println("No orders found for user ID: " + userId111);
                     } else {
                         System.out.println("Orders for User ID: " + userId111);
                         for (Product order : orders) {
                             System.out.println(order);
                         }
                     }
              
                    
                    break;
                    
                
                default:
                	
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
        }
    
    
      
      }
