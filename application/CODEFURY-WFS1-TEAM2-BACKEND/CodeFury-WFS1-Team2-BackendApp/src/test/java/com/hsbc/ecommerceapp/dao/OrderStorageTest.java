package com.hsbc.ecommerceapp.dao;

import com.hsbc.ecommerceapp.model.Order;
import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStorageTest {

    private OrderStorage orderStorage;
    private OrderService orderService;
    private Order order = null;

    // setup before each test
    @BeforeEach
    public void setup() {
        orderStorage = new OrderStorage();
        order = new Order("Order1", "Customer1", 100.5, "22-08-2024","Delivered");
    }

    // testing add order
    @Test
    public void testAddOrder() {
        orderStorage.addOrder("Customer1", order);

        List<Order> orders = orderService.getOrderByCustomerId("Customer1");
        assertEquals(1, orders.size());
    }

    // testing get order by customer id
    @Test
    public void testGetOrderByCustomerId() {
        Order order1 = new Order("Order1", "Customer1", 100.5, "22-08-2024","Delivered");
        Order order2 = new Order("Order2", "Customer2", 100.5, "22-08-2024","Delivered");
        orderStorage.addOrder("Customer1", order1);
        orderStorage.addOrder("Customer1", order2);

        List<Subscription> orders = (List<Subscription>) orderStorage.getOrderById("Customer1");
        assertEquals(2, orders.size());
    }

    // testing cancel order
    @Test
    public void testCancelOrder() {
        orderStorage.addOrder("Customer1", order);

        orderStorage.deleteOrder("Customer1");
        List<Order> orders = orderService.getOrderByCustomerId("Customer1");
        assertTrue(orders.isEmpty());
    }
}
