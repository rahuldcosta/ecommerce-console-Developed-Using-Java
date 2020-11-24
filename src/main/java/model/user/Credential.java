package model.user;

import model.enums.UserRole;

public class Credential {
    String username;
    String password;
    UserRole role;

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean equals(Credential credential) {
        return (credential.getUsername().equals(username) && credential.getPassword().equals(password));
    }
}
