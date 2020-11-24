package model.user;

import model.ShoppingCart;
import model.enums.UserRole;

public class Customer {
    User user;
    ShoppingCart shoppingCart;
    Credential credential;

    public Customer(User user, Credential credential) {
        this.user = user;
        this.shoppingCart = new ShoppingCart();
        this.credential = credential;
        this.credential.setRole(UserRole.USER);
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
