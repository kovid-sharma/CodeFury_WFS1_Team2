package com.hsbc.ecommerceapp.model;

public class Admin extends User {
    private String adminRole;

    public Admin(String userId, String userName, String password, String email, String userType, String created_at) {
        super(userId, userName, password, email, userType, created_at);
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public void deactivateSubscription(Subscription subscription) {
        subscription.setActive(false);
    }

    public void activateSubscription(Subscription subscription) {
        subscription.setActive(true);
    }
}
