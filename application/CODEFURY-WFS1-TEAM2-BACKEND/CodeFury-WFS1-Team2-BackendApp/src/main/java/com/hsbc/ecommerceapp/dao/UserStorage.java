package com.hsbc.ecommerceapp.dao;

import com.hsbc.ecommerceapp.exceptions.UserNotFoundException;
import com.hsbc.ecommerceapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    // method to add a user
    public void addUser(User user) {
        // sql query
        String sql = "INSERT INTO users VALUES (?, ?, ?, ?,?)";

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, user.getUser_id());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUser_type());

            // execute query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }
    }

    // method to update a user
    public void updateUser(User user) {
        // sql query
        String sql = "UPDATE users SET username = ?, email = ?, password = ?, user_type = ? WHERE user_id = ?";

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUser_type());
            preparedStatement.setString(5, user.getUser_id());

            // get no. of records affected
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new UserNotFoundException("User not found!");
            }

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }
    }

    // method to delete a user
    public void deleteUser(String userId) {
        // sql query
        String sql = "DELETE FROM users WHERE user_id = ?";

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, userId);

            // get no. of records affected
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new UserNotFoundException("User not found!");
            }

        } catch (SQLException e) {
            //handling exception
            System.out.println(e.getMessage());
        }
    }

    // method to get a user by id
    public User getUserById(String userId) throws UserNotFoundException {
        // sql query
        String sql = "SELECT * FROM users WHERE user_id = ?";
        User user = null;

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, userId);
            // execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // create new user object reference
                user = new User(
                        resultSet.getString("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("user_type"),
                        resultSet.getString("created_at")
                );
            } else {
                throw new UserNotFoundException("User not found with ID: " + userId);
            }

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }

        return user;
    }

    // method to get all users
    public List<User> getAllUsers() {
        // sql query
        String sql = "SELECT * FROM users";
        // list to fetch all users
        List<User> users = new ArrayList<>();

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             // execute query
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // create new user object reference
                User user = new User(
                        resultSet.getString("user_id"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("user_type"),
                        resultSet.getString("created_at")
                );
                users.add(user);
            }

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }

        return users;
    }
}
