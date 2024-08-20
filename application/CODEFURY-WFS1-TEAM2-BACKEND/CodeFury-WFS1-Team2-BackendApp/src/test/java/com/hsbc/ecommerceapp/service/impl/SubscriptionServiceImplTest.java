package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.dao.OrderStorage;
import com.hsbc.ecommerceapp.dao.SubscriptionStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SubscriptionServiceImplTest {

    private SubscriptionStorage subscriptionStorage;
    private OrderStorage orderStorage;
    private SubscriptionServiceImpl subscriptionService;
    private Product product = null;

    // setup before each test
    @BeforeEach
    public void setup() {
        subscriptionStorage = mock(SubscriptionStorage.class);
        orderStorage = mock(OrderStorage.class);
        subscriptionService = new SubscriptionServiceImpl(subscriptionStorage, orderStorage);
        product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
    }

    // testing add subscription
    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);

        subscriptionService.addSubscription(subscription);

        verify(subscriptionStorage).addSubscription(subscription);
    }

    // testing update subscription
    @Test
    public void testUpdateSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);

        when(subscriptionStorage.getSubscriptionById("Subscription1")).thenReturn(subscription);

        subscription.setType("Monthly");
        subscriptionService.updateSubscription(subscription);

        verify(subscriptionStorage).updateSubscription(subscription);
    }

    // testing cancel subscription
    @Test
    public void testCancelSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);

        when(subscriptionStorage.getSubscriptionById("Subscription1")).thenReturn(subscription);

        subscriptionService.cancelSubscription("Subscription1");

        verify(subscriptionStorage).cancelSubscription("Subscription1");
    }

    // testing get subscription by id
    @Test
    public void testGetSubscriptionById() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);

        when(subscriptionStorage.getSubscriptionById("Subscription1")).thenReturn(subscription);

        Subscription fetchedSubscription = subscriptionService.getSubscriptionById("Subscription1");

        assertNotNull(fetchedSubscription);
        assertEquals("Customer1", fetchedSubscription.getCustomerId());
    }

    // testing get all subscription
    @Test
    public void testGetAllSubscriptions() {
        Subscription subscription1 = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        Subscription subscription2 = new Subscription("Subscription2", "Product2", "Customer2", "Bi-Weekly", LocalDate.parse("2024-02-02"), LocalDate.parse("2024-03-02"), true);

        when(subscriptionStorage.getAllSubscriptions()).thenReturn(Arrays.asList(subscription1, subscription2));

        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();

        assertEquals(2, subscriptions.size());
        verify(subscriptionStorage).getAllSubscriptions();
    }
}
