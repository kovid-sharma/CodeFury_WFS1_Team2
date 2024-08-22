package com.hsbc.ecommerceapp.dao;

import com.hsbc.ecommerceapp.exceptions.ProductNotFoundException;
import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductStorage {
    // sql query
    String sql = "INSERT INTO products (productId, productName, productDescription, price, isActive) VALUES (?, ?, ?, ?, ?)";

    // method to add product
    public void addProduct(Product product) {
        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductDescription());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setBoolean(5, product.isActive());

            // execute query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }
    }

    public void updateProduct(Product product) {
        // sql query
        String sql = "UPDATE products SET productName = ?,, productDescription = ?, price = ?, isActive = ? WHERE productId = ?";

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setBoolean(4, product.isActive());
            preparedStatement.setString(5, product.getProductId());

            // fetch no. of affected records
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0)
                throw new ProductNotFoundException("Product not found!");

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }
    }

    public void deleteProduct(String productId) {
        // sql query
        String sql = "DELETE FROM products WHERE productId = ?";

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // execute query and get result
            preparedStatement.setString(1, productId);

            // fetch no. of affected records
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0)
                throw new ProductNotFoundException("Product not found with ID: " + productId);

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }
    }

    public Product getProductById(String productId) {
        // sql query
        String sql = "SELECT * FROM products WHERE productId = ?";
        Product product = null;

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // injecting values into '?' placeholder
            preparedStatement.setString(1, productId);
            // execute query and get result
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // create new product object reference
                product = new Product(
                        resultSet.getString("productId"),
                        resultSet.getString("productName"),
                        resultSet.getString("productDescription"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("isActive")
                );
            } else {
                throw new ProductNotFoundException("Product not found!");
            }

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }

        return product;
    }

    // method to get all products
    public List<Product> getAllProducts() {
        // sql query
        String sql = "SELECT * FROM products";
        // list to fetch all products
        List<Product> products = new ArrayList<>();

        // connecting to database
        try (Connection connection = DatabaseConnection.getConnection();
             // preparing sql query statement
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             // execute query
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // create new product object reference
                Product product = new Product(
                        resultSet.getString("productId"),
                        resultSet.getString("productName"),
                        resultSet.getString("productDescription"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("isActive")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            // handling exception
            System.out.println(e.getMessage());
        }

        return products;
    }
}
