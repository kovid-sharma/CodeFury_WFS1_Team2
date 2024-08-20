package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.model.Order;
import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.model.User;
import com.hsbc.ecommerceapp.service.CustomerService;
import com.hsbc.ecommerceapp.service.SubscriptionService;
import com.hsbc.ecommerceapp.dao.OrderStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    private CustomerService customerService;
    private SubscriptionService subscriptionService;
    private OrderStorage orderStorage;
    User user = null;
    Order order = null;

    // setup before each test
    @BeforeEach
    public void setUp() {
        subscriptionService = mock(SubscriptionService.class);
        orderStorage = mock(OrderStorage.class);
        customerService = new CustomerServiceImpl(subscriptionService, orderStorage);
        user = new User("User1", "john_wick", "password123", "john@wick.com", "customer","22-08-2024");
        order = new Order("Order1", "Customer1", 100.5, "22-08-2024","Delivered");
    }

    // testing place order
    @Test
    public void testPlaceOrder() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);

        customerService.placeOrder(user, order);

        verify(subscriptionService).addSubscription(subscription);
        verify(orderStorage).addOrder(user.getUser_id(), order);
    }

    // testing cancel order
    @Test
    public void testCancelOrder() {
        customerService.cancelOrder("Order1", order);

        verify(subscriptionService).cancelSubscription("Subscription1");
    }

    // testing view order
    @Test
    public void testViewOrders() {
        Subscription subscription1 = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        Subscription subscription2 = new Subscription("Subscription2", "Product2", "Customer1", "Bi-Weekly", LocalDate.parse("2024-02-02"), LocalDate.parse("2024-03-02"), true);

        when(orderStorage.getOrderByCustomerId("Customer1")).thenReturn(Arrays.asList(order));

        List<Order> orders = customerService.viewOrder("Customer1");

        assertEquals(2, orders.size());
        verify(orderStorage).getOrderByCustomerId("Customer1");
    }

    // testing change subscription plan
    @Test
    public void testChangeSubscriptionPlan() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);

        when(subscriptionService.getSubscriptionById("Subscription1")).thenReturn(subscription);

        customerService.changeSubscriptionPlan("Subscription1", "Monthly");

        assertEquals("Monthly", subscription.getType());
        verify(subscriptionService).updateSubscription(subscription);
    }
}
