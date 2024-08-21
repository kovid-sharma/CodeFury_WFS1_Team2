package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.InvalidInputException;
import com.hsbc.ecommerceapp.exceptions.UserNotFoundException;
import com.hsbc.ecommerceapp.model.User;
import com.hsbc.ecommerceapp.service.UserService;
import com.hsbc.ecommerceapp.dao.UserStorage;

public class UserServiceImpl implements UserService {
    private UserStorage userStorage;

    // constructor
    public UserServiceImpl(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    // overriding add user
    @Override
    public void registerUser(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null)
            throw new InvalidInputException("Invalid user data");
        userStorage.addUser(user);
    }

    // overriding update user
    @Override
    public void updateUser(User user) {
        if (userStorage.getUserById(user.getUser_id()) == null)
            throw new UserNotFoundException("User not found!");
        userStorage.updateUser(user);
    }

    // overriding delete user
    @Override
    public void deleteUser(String userId) {
        if (userStorage.getUserById(userId) == null)
            throw new UserNotFoundException("User not found!");
        userStorage.deleteUser(userId);
    }

    // overriding login user
    @Override
    public User loginUser(String userId, String password) {
        User user = userStorage.getUserById(userId);
        if (user == null || !user.getPassword().equals(password))
            throw new UserNotFoundException("Invalid username or password");
        return user;
    }
}
