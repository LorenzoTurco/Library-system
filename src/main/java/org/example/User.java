package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private boolean admin;

    private List<Book> myBooks = new ArrayList<>();

    public User(String username, boolean admin) {
        this.username = username;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
