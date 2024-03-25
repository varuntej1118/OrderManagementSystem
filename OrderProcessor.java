package com.dao;

import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;
import com.model.Product;
import com.model.User;
import com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProcessor implements OrderManagementRepo {

	@Override
    public void createOrder(User user, List<Product> products) throws UserNotFoundException {
        try (Connection connection = DBUtil.getDBConn()) {
            // Check if user exists
            PreparedStatement checkUserStmt = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            checkUserStmt.setInt(1, user.getUserId());
            ResultSet userResultSet = checkUserStmt.executeQuery();
            if (!userResultSet.next()) {
                throw new UserNotFoundException("User with ID " + user.getUserId() + " not found.");
            }

            
            PreparedStatement createOrderStmt = connection.prepareStatement("INSERT INTO orders (user_id, product_id, quantity) VALUES (?, ?, ?)");
            for (Product product : products) {
                createOrderStmt.setInt(1, user.getUserId());
                createOrderStmt.setInt(2, product.getProductId());
                createOrderStmt.setInt(3, product.getQuantityInStock());
                createOrderStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelOrder(int userId, int orderId) throws OrderNotFoundException {
        try (Connection connection = DBUtil.getDBConn()) {
            
            PreparedStatement checkOrderStmt = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ? AND order_id = ?");
            checkOrderStmt.setInt(1, userId);
            checkOrderStmt.setInt(2, orderId);
            ResultSet orderResultSet = checkOrderStmt.executeQuery();
            if (!orderResultSet.next()) {
                throw  new OrderNotFoundException ("Order with ID " + orderId + " not found for user " + userId);
            }
            
            PreparedStatement cancelOrderStmt = connection.prepareStatement("DELETE FROM orders WHERE user_id = ? AND order_id = ?");
            cancelOrderStmt.setInt(1, userId);
            cancelOrderStmt.setInt(2, orderId);
            cancelOrderStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void createProduct(User user, Product product) {
        try (Connection connection = DBUtil.getDBConn()) {
            PreparedStatement createProductStmt = connection.prepareStatement("INSERT INTO product (product_name, description, price, quantity_in_stock, type) VALUES (?, ?, ?, ?, ?)");
            createProductStmt.setString(1, product.getProductName());
            createProductStmt.setString(2, product.getDescription());
            createProductStmt.setDouble(3, product.getPrice());
            createProductStmt.setInt(4, product.getQuantityInStock());
            createProductStmt.setString(5, product.getType());
            createProductStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUser(User user) {
        try (Connection connection = DBUtil.getDBConn()) {
            PreparedStatement createUserStmt = connection.prepareStatement("INSERT INTO user (user_id, username, password, role) VALUES (?, ?, ?, ?)");
            createUserStmt.setInt(1, user.getUserId());
            createUserStmt.setString(2, user.getUsername());
            createUserStmt.setString(3, user.getPassword());
            createUserStmt.setString(4, user.getRole());
            createUserStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBUtil.getDBConn()) {
            PreparedStatement getAllProductsStmt = connection.prepareStatement("SELECT * FROM product");
            ResultSet resultSet = getAllProductsStmt.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setQuantityInStock(resultSet.getInt("quantity_in_stock"));
                product.setType(resultSet.getString("type"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getOrderByUser(User user) {
        List<Product> orders = new ArrayList<>();
        try (Connection connection = DBUtil.getDBConn()) {
            PreparedStatement getOrdersByUserStmt = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ?");
            getOrdersByUserStmt.setInt(1, user.getUserId());
            ResultSet resultSet = getOrdersByUserStmt.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                product.setQuantityInStock(resultSet.getInt("quantity"));
                orders.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}