package com.hsbc.ecommerceapp.dao;

import com.hsbc.ecommerceapp.exceptions.SubscriptionNotFoundException;
import com.hsbc.ecommerceapp.model.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionStorage {
    // method to add a subscription
    public void addSubscription(Subscription subscription) {
        // sql query
        String sql = "INSERT INTO subscriptions (subscriptionId, productId, customerId, startDate, endDate, isActive) VALUES (?, ?, ?, ?, ?, ?)";

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, subscription.getSubscriptionId());
            preparedStatement.setString(2, subscription.getProductId());
            preparedStatement.setString(3, subscription.getCustomerId());
            preparedStatement.setDate(4, Date.valueOf(subscription.getStartDate()));
            preparedStatement.setDate(5, Date.valueOf(subscription.getEndDate()));
            preparedStatement.setBoolean(6, subscription.isActive());

            // execute query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }
    }

    public void updateSubscription(Subscription subscription) {
        // sql query
        String sql = "UPDATE subscriptions SET productId = ?, customerId = ?, startDate = ?, endDate = ?, isActive = ? WHERE subscriptionId = ?";

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, subscription.getProductId());
            preparedStatement.setString(2, subscription.getCustomerId());
            preparedStatement.setDate(3, Date.valueOf(subscription.getStartDate()));
            preparedStatement.setDate(4, Date.valueOf(subscription.getEndDate()));
            preparedStatement.setBoolean(5, subscription.isActive());
            preparedStatement.setString(6, subscription.getSubscriptionId());

            // get no. of records affected
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0)
                throw new SubscriptionNotFoundException("Subscription not found!");

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }
    }

    public void cancelSubscription(String subscriptionId) {
//        Subscription subscription = subscriptionMap.get(subscriptionId);
//        if(subscription != null)
//            subscription.setActive(false);

        // sql query
        String sql = "DELETE FROM subscriptions WHERE subscriptionId  = ?";

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, subscriptionId);

            // get no. of rows affected
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SubscriptionNotFoundException("Subscription not found!");
            }

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }
    }

    // method to get subscription by id
    public Subscription getSubscriptionById(String subscriptionId) {
        // sql query
        String sql = "SELECT * FROM subscriptions WHERE subscriptionId = ?";
        Subscription subscription = null;

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, subscriptionId);
            // execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // create new subscription object reference
                subscription = new Subscription(
                        resultSet.getString("subscriptionId"),
                        resultSet.getString("productId"),
                        resultSet.getString("customerId"),
                        resultSet.getString("type"),
                        resultSet.getDate("startDate").toLocalDate(),
                        resultSet.getDate("endDate").toLocalDate(),
                        resultSet.getBoolean("isActive")
                );
            } else {
                throw new SubscriptionNotFoundException("Subscription not found!");
            }

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }

        return subscription;
    }

    // method to get all subscriptions
    public List<Subscription> getAllSubscriptions() {
        // sql query
        String sql = "SELECT * FROM subscriptions";
        // list to fetch all subscriptions
        List<Subscription> subscriptions = new ArrayList<>();

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             // execute query
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // create new subscription object reference
                Subscription subscription = new Subscription(
                        resultSet.getString("subscriptionId"),
                        resultSet.getString("productId"),
                        resultSet.getString("customerId"),
                        resultSet.getString("type"),
                        resultSet.getDate("startDate").toLocalDate(),
                        resultSet.getDate("endDate").toLocalDate(),
                        resultSet.getBoolean("isActive")
                );
                subscriptions.add(subscription);
            }

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }

        return subscriptions;
    }
}
