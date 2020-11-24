package model;

import model.user.Admin;
import model.user.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Website {

    List<Product> products;
    List<Customer> customers;
    Admin admin;

    public Website(Admin admin) {
        this.admin = admin;
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public Admin getAdmin() {
        return admin;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProductToCatalog(Product product) {
        this.products.add(product);
    }

    public Product getProductFromCatalogById(Long productId) {
        Optional<Product> product = this.products.stream().filter(p -> p.getProductId().equals(productId)).findFirst();
        if (product.isPresent())
            return product.get();
        return null;
    }

    public void deleteProductToCatalog(Product product) {
        this.products.remove(product);
    }

    public void updateProductToCatalog(Product product, Long productId) {
        for (Product productItem : products
        ) {
            if (productItem.getProductId().equals(productId)) {
                products.remove(productItem);
                product.setProductId(productId);
                products.add(product);
                break;
            }
        }
    }
}
