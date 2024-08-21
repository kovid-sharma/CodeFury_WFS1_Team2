package com.hsbc.ecommerceapp.dao;

import com.hsbc.ecommerceapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserStorageTest {

    private UserStorage userStorage;
    private User user = null;

    // setup before each test
    @BeforeEach
    public void setup() {
        userStorage = new UserStorage();
        user = new User("User1", "john_wick", "user1@example.com", "john@123", "customer","22-08-2024");
    }

    // testing add user
    @Test
    public void testAddUser() {
        userStorage.addUser(user);

        User fetchedUser = userStorage.getUserById("User1");
        assertNotNull(fetchedUser);
        assertEquals("user1@example.com", fetchedUser.getEmail());
    }

    // testing update user
    @Test
    public void testUpdateUser() {
        user.setEmail("updated@example.com");
        userStorage.updateUser(user);

        User updatedUser = userStorage.getUserById("User1");
        assertEquals("updated@example.com", updatedUser.getEmail());
    }

    // testing delete user
    @Test
    public void testDeleteUser() {
        userStorage.addUser(user);
        userStorage.deleteUser("User1");

        User deletedUser = userStorage.getUserById("User1");
        assertNull(deletedUser);
    }

    // testing get user by username
    @Test
    public void testGetUserByUserName() {
        userStorage.addUser(user);

        User fetchedUser = userStorage.getUserById("User1");
        assertNotNull(fetchedUser);
        assertEquals("user1@example.com", fetchedUser.getEmail());
    }
}
