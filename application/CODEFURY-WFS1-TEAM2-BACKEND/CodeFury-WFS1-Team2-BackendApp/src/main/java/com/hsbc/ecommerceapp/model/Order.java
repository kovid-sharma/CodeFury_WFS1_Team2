package com.hsbc.ecommerceapp.model;

public class Order {
    private String order_id;
    private String user_id;
    private Double total_amount;
    private String order_date;
    private String status;

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    // Constructor
    public Order(String order_id, String user_id, Double total_amount, String order_date, String status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.total_amount = total_amount;
        this.order_date = order_date;
        this.status = status;
    }

    // Getters and Setters
    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method for easy debugging and logging

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + order_id + '\'' +
                ", customerId='" + user_id + '\'' +
                ", totalAmount=" + total_amount +
                ", order_date='" + order_date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
