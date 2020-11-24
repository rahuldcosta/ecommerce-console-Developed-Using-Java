package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> productList;
    private Double totalPrice;

    public ShoppingCart(){
        productList= new ArrayList<>();
        totalPrice= new Double(0);
    }
    public List<Product> getProductList() {
        return productList;
    }

    public Double getTotalPrice() {
        totalPrice= new Double(0);
        productList.stream().forEach(product -> totalPrice += product.getPrice());
        return totalPrice;
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

}
