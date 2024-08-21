package com.hsbc.ecommerceapp.dao;

import com.hsbc.ecommerceapp.exceptions.OrderNotFoundException;
import com.hsbc.ecommerceapp.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderStorage {
    // method to add new order
    public void addOrder(String customerId, Order order) {
        // sql query
        String sql = "INSERT INTO orders (order_id, user_id, total_amount, order_date,status) VALUES (?, ?, ?, ?, ?)";

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing the sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholders
            preparedStatement.setString(1, order.getOrder_id());
            preparedStatement.setString(2, order.getUser_id());
            preparedStatement.setDouble(3, order.getTotal_amount());
            preparedStatement.setString(4, order.getOrder_date());
            preparedStatement.setString(5, order.getStatus());

            // executing query
            preparedStatement.executeUpdate();

        } catch (SQLException e) { // handling exception
            System.out.println(e.getMessage());
        }
    }

    // method to update an order
    public void updateOrder(String customerId, Order order) throws OrderNotFoundException {
        // sql query
        String sql = "UPDATE orders SET user_id = ?, total_amount = ?, status = ? WHERE order_id = ?";

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, customerId);
            preparedStatement.setDouble(2, order.getTotal_amount());
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setString(4, order.getOrder_id());

            // fetch no. of affected records
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                // if no order exists
                throw new OrderNotFoundException("Order not found!");
            }

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }
    }

    // method to delete an order
    public void deleteOrder(String orderId) throws OrderNotFoundException {
        // sql query
        String sql = "DELETE FROM orders WHERE order_id = ?";

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, orderId);

            // fetch no. of affected records
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                // if no order found
                throw new OrderNotFoundException("Order not found!");
            }

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }
    }

    // method to get an order by id
    public Order getOrderById(String orderId) throws OrderNotFoundException {
        // sql query
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        Order order = null;

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, orderId);
            // execute query and get result
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // store result into an object reference
                order = new Order(
                        resultSet.getString("order_id"),
                        resultSet.getString("user_id"),
                        resultSet.getDouble("total_amount"),
                        resultSet.getString("order_date"),
                        resultSet.getString("status")
                );
            } else {
                // if order not found
                throw new OrderNotFoundException("Order not found with ID: " + orderId);
            }

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }

        return order;
    }

    // method to get the order of a customer by id
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
