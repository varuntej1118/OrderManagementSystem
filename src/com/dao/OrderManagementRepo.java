package com.dao;

import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;
import com.model.Product;
import com.model.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderManagementRepo {
    void createOrder(User user, List<Product> products) throws SQLException, UserNotFoundException;
    void cancelOrder(int userId, int orderId) throws SQLException, OrderNotFoundException;
    void createProduct(User user, Product product) throws SQLException;
    void createUser(User user) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
    List<Product> getOrderByUser(User user) throws SQLException;
}
