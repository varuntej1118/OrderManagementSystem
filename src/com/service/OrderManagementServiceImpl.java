package com.service;

import com.dao.OrderManagementRepo;
import com.dao.OrderProcessorImpl;
import com.model.Product;
import com.model.User;
import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public class OrderManagementServiceImpl implements OrderManagementService {
    OrderManagementRepo orderRepo = new OrderProcessorImpl() ;

   
    @Override
    public void createOrder(User user, List<Product> products) throws UserNotFoundException {
        try {
			orderRepo.createOrder(user, products);
		} catch (SQLException | UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void cancelOrder(int userId, int orderId) throws OrderNotFoundException {
        try {
			orderRepo.cancelOrder(userId, orderId);
		} catch (SQLException | OrderNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void createProduct(User user, Product product) throws SQLException{
        try {
			orderRepo.createProduct(user, product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    public void getUserById(int userId) throws SQLException{
    	 orderRepo.getUserById(userId);
    }
}
