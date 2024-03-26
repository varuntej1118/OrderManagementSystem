package com.service;

import com.model.Product;
import com.model.User;
import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface OrderManagementService {
    void createOrder(User user, List<Product> products) throws UserNotFoundException;

    void cancelOrder(int userId, int orderId) throws OrderNotFoundException;

    void createProduct(User user, Product product) throws SQLException;

    void createUser(User user) throws SQLException;

    List<Product> getAllProducts() throws SQLException;

    List<Product> getOrderByUser(User user) throws SQLException;

	void getUserById(int userId1) throws SQLException;

	
}
