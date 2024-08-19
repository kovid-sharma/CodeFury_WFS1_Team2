package com.hsbc.ecommerceapp.storage;

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
        String sql = "INSERT INTO users VALUES (?, ?, ?, ?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getUser_id());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUser_type());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    // method to update a user
    public void updateUser(User user) {
        String sql = "UPDATE users SET username = ?, email = ?, password = ?, user_type = ? WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUser_type());
            preparedStatement.setString(5, user.getUser_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new UserNotFoundException("User not found!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // method to delete a user
    public void deleteUser(String userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new UserNotFoundException("User not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    // method to get a user by id
    public User getUserById(String userId) throws UserNotFoundException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        User user = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
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
            System.out.println(e.getMessage());
        }

        return user;
    }

    // method to get all users
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
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
            System.out.println(e.getMessage());
        }

        return users;
    }
}
