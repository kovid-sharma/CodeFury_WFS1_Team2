package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.InvalidInputException;
import com.hsbc.ecommerceapp.exceptions.UserNotFoundException;
import com.hsbc.ecommerceapp.model.*;
import com.hsbc.ecommerceapp.service.CustomerService;
import com.hsbc.ecommerceapp.service.OrderService;
import com.hsbc.ecommerceapp.service.SubscriptionService;
import com.hsbc.ecommerceapp.dao.OrderStorage;
import com.hsbc.ecommerceapp.dao.UserStorage;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private SubscriptionService subscriptionService;
    private OrderStorage orderStorage;
    private OrderService orderService;
    private UserStorage userStorage;

    // constructor
    public CustomerServiceImpl(SubscriptionService subscriptionService, OrderStorage orderStorage) {
        this.subscriptionService = subscriptionService;
        this.orderStorage = orderStorage;
    }

    // overriding place order
    @Override
    public void placeOrder(User user, Order order) {
        if(user.isAdmin())
            throw new SecurityException(("User not a customer!"));
        orderStorage.addOrder(user.getUser_id(), order);
    }

    // overriding cancel order
    @Override
    public void cancelOrder(String customerId, Order order) {
        orderService.cancelOrder(customerId, order);
    }


    // overriding  update customer info
    @Override
    public void updateCustomerInfo(String customerId, Customer updatedCustomer) throws UserNotFoundException, InvalidInputException {
        // validate the updated customer information
        if(updatedCustomer == null || customerId == null || customerId.isEmpty())
            throw new InvalidInputException("Invalid input for customer update");

        // retrieve existing customer from storage
        Customer existingCustomer = (Customer) userStorage.getUserById(customerId);

        // update existing customer's details with new information
        existingCustomer.setUsername(updatedCustomer.getUsername());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());

        // save updated customer back to storage
        userStorage.updateUser(existingCustomer);
    }

    // overriding  change subscription plan
    @Override
    public void changeSubscriptionPlan(String subscriptionId, String newPlan) {
        Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId);
        subscription.setType(newPlan);
        subscriptionService.updateSubscription(subscription);
    }

    // overriding view all orders
    @Override
    public List<Order> viewOrder(String customerId) {
        return orderService.getOrderByCustomerId(customerId);
    }
}
