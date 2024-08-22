package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.OrderNotFoundException;
import com.hsbc.ecommerceapp.model.Order;
import com.hsbc.ecommerceapp.service.OrderService;
import com.hsbc.ecommerceapp.dao.OrderStorage;
import com.hsbc.ecommerceapp.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderStorage orderStorage;

    // constructor
    public OrderServiceImpl(OrderStorage orderStorage) {
        this.orderStorage = orderStorage;
    }

    // overriding create order
    @Override
    public void createOrder(String customerId, Order order) {
        orderStorage.addOrder(customerId, order);
    }

    // overriding update order
    @Override
    public void updateOrder(String customerId, Order order) throws OrderNotFoundException {
        orderStorage.updateOrder(customerId, order);
    }

    // overriding cancel order
    @Override
    public void cancelOrder(String customerId, Order order) throws OrderNotFoundException {
        if (order != null) {
            order.setStatus("Cancelled");
            orderStorage.updateOrder(customerId, order);
        } else {
            throw new OrderNotFoundException("Order note found!");
        }
    }

    // overriding delete order
    @Override
    public void deleteOrder(String orderId) throws OrderNotFoundException {
        if(orderStorage.getOrderById(orderId) != null)
            orderStorage.deleteOrder(orderId);
        else
            throw new OrderNotFoundException("Order not found!");
    }

    // overriding get order by id
    @Override
    public Order getOrderById(String orderId) throws OrderNotFoundException {
        return orderStorage.getOrderById(orderId);
    }

    @Override
    public List<Order> getOrderByCustomerId(String customerId) {
        // list to fetch all orders
        List<Order> orderList = new ArrayList<Order>();
        // sql query
        String sql = "SELECT * FROM orders WHERE user_id = ?";

        // connecting to database
        try(Connection connection = DatabaseConnection.getConnection();
            // preparing sql query statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // injecting values into '?' placeholder
            preparedStatement.setString(1, customerId);

            // execute query and get result
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    // store result in new object reference
                    Order order = new Order(
                            resultSet.getString("order_id"),
                            resultSet.getString("user_id"),
                            resultSet.getDouble("total_amount"),
                            resultSet.getString("order_date"),
                            resultSet.getString("status")
                    );
                    orderList.add(order);
                }
            }
        }
        catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }

        return orderList;
    }
}
