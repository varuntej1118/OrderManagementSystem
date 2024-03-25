package com.service;

import com.dao.OrderManagementRepo;
import com.model.Product;
import com.model.User;
import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public class OrderManagementServiceImpl implements OrderManagementRepo {
    private final OrderManagementRepo orderRepo;

    public OrderManagementServiceImpl(OrderManagementRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void createOrder(User user, List<Product> products) throws UserNotFoundException, SQLException {
        orderRepo.createOrder(user, products);
    }

    @Override
    public void cancelOrder(int userId, int orderId) throws OrderNotFoundException, SQLException {
        orderRepo.cancelOrder(userId, orderId);
    }

    @Override
    public void createProduct(User user, Product product) throws SQLException {
        orderRepo.createProduct(user, product);
    }

    @Override
    public void createUser(User user) throws SQLException {
        orderRepo.createUser(user);
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        return orderRepo.getAllProducts();
    }

    @Override
    public List<Product> getOrderByUser(User user) throws SQLException {
        return orderRepo.getOrderByUser(user);
    }
}
