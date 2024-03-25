package com.service;

import com.model.Product;
import com.model.User;
import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;

import java.util.List;

public interface OrderManagementService {
    void createOrder(User user, List<Product> products) throws UserNotFoundException;

    void cancelOrder(int userId, int orderId) throws OrderNotFoundException;

    void createProduct(User user, Product product);

    void createUser(User user);

    List<Product> getAllProducts();

    List<Product> getOrderByUser(User user);
}
