package com.hsbc.ecommerceapp.model;

public class Admin extends User {
    private String adminRole;

    public Admin(String user_id, String password, String username, String email, String user_type, String created_at) {
        super(user_id, username, password, email, user_type, created_at);
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
