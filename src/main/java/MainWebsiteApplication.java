import model.Product;
import model.Website;
import model.user.Admin;
import model.user.Credential;
import model.user.Customer;
import model.user.User;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class MainWebsiteApplication {

    static Admin admin = new Admin(new Credential("admin", "admin"));
    static Website website = new Website(admin);
    static Long productCount = new Long(0);

    public static void main(String[] args) {
        Product p = new Product("Shoe", 1L, "Footware Collection", 5.4);
        website.addProductToCatalog(p);
        p = new Product("Book", 2L, "A4 size book", 10.00);
        website.addProductToCatalog(p);
        p = new Product("Bags", 3L, "Bag Collection", 6.00);
        website.addProductToCatalog(p);
        p = new Product("Laptop", 4L, "Laptop Collection", 15.39);
        website.addProductToCatalog(p);
        p = new Product("Chair", 5L, "4 leg Chair ", 79.12);
        website.addProductToCatalog(p);

        System.out.println("Welcome to E-Commerce Site");
        productCount = new Long(website.getProducts().size());
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 3) {
            System.out.println("Please Select option below");
            System.out.println("1.Sign Up");
            System.out.println("2.Log In");
            System.out.println("3.Exit Application");
            choice = scanner.nextInt();

            if (choice == 1) {
                doSignUp();
            }
            if (choice == 2) {
                doLogin();
            }
        }

    }

    private static void doLogin() {
        System.out.println("Please Select option below");
        System.out.println("1.Login In as Admin");
        System.out.println("2.Log In as Customer");
        System.out.println("3.Return to Home Page");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 3)
            return;

        if (choice == 1) {
            System.out.println("Enter Credentials");
            System.out.println("Enter Username");
            String userName = scanner.next();
            System.out.println("Enter Password");
            String password = scanner.next();
            Credential enteredCredentials = new Credential(userName, password);
            if (website.getAdmin().getCredential().equals(enteredCredentials)) {
                showAdminMenu();
            } else {
                System.out.println("Invalid Admin Credentials Entered.Please contact your Administration");
            }
        }
        if (choice == 2) {
            System.out.println("Enter Credentials");
            System.out.println("Enter Username");
            String userName = scanner.next();
            System.out.println("Enter Password");
            String password = scanner.next();
            Credential enteredCredentials = new Credential(userName, password);
            Optional<Customer> loggedInCustomer = website.getCustomers().stream().filter(customer -> customer.getCredential().equals(enteredCredentials)).findFirst();
            if (loggedInCustomer.isPresent())
                showCustomerMenu(loggedInCustomer.get());
            else
                System.out.println("Customer with username/password combination doesnt exists .Please signup");
        }


    }

    private static void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 6) {
            System.out.println("Please Select option below");
            System.out.println("1.Create Product");
            System.out.println("2.Show All Products");
            System.out.println("3.Read Product by Id");
            System.out.println("4.Delete Product by Id");
            System.out.println("5.Update Product by Id");
            System.out.println("6.Log Out");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    website.addProductToCatalog(getProduct());
                    break;
                case 2:
                    System.out.println(website.getProducts());
                    break;
                case 3:
                    System.out.println("Please Enter Product Id");
                    System.out.println(website.getProductFromCatalogById(scanner.nextLong()).toString());
                    break;
                case 4:
                    System.out.println("Please Enter Product Id");
                    website.deleteProductToCatalog(website.getProductFromCatalogById(scanner.nextLong()));
                    break;
                case 5:
                    System.out.println("Please Enter Product Id");
                    Long productId = scanner.nextLong();
                    // need to set product id also
                    // check why update doesnt delete object

                    website.updateProductToCatalog(getProduct(), productId);
                    System.out.println(website.getProductFromCatalogById(productId));
                    break;

            }
        }
    }

    private static Product getProduct() {
        String productName;
        String productDescription;
        Double productPrice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter Product Details");
        System.out.println("Enter Name");
        productName = scanner.next();

        System.out.println("Enter Description");
        productDescription = scanner.next();
        System.out.println("Enter Price");
        productPrice = scanner.nextDouble();

        Product product = new Product(productName, ++productCount, productDescription, productPrice);

        return product;
    }

    private static void showCustomerMenu(Customer loggedInCustomer) {
        System.out.println("Welcome " + loggedInCustomer.getUser().getName());
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 4) {
            System.out.println("Please Select option below");
            System.out.println("1.Search for Products");
            System.out.println("2.View Cart");
            System.out.println("3.Checkout");
            System.out.println("4.Log Out");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (website.getProducts().size() == 0) {
                        System.out.println("No more Products on Website");
                        return;
                    }
                    System.out.println(website.getProducts());
                    System.out.println("Please Enter Product Id to perform actions on it");
                    showProductInteractionMenu(loggedInCustomer, scanner.nextLong());
                    break;
                case 2:
                    if (loggedInCustomer.getShoppingCart().getProductList().size() == 0)
                        System.out.println("Your cart is Empty");
                    else
                        System.out.println(loggedInCustomer.getShoppingCart().getProductList());
                    break;
                case 3:
                    if (loggedInCustomer.getShoppingCart().getProductList().size() == 0) {
                        System.out.println("No Items in Cart to Checkout.Please add some to your cart");
                        return;
                    }
                    System.out.println("Below are the items selected for Checkout");
                    System.out.println(loggedInCustomer.getShoppingCart().getProductList());
                    System.out.println("Total amount of Rs." + loggedInCustomer.getShoppingCart().getTotalPrice());
                    System.out.print("Do you want to checkout and pay via cash. Y/N ?");
                    if (scanner.next().equalsIgnoreCase("Y")) {
                        System.out.println("Items Successfully Checked Out");
                        website.getProducts().removeAll(loggedInCustomer.getShoppingCart().getProductList());
                        loggedInCustomer.getShoppingCart().getProductList().clear();
                    }
                    break;
            }
        }

    }

    private static void showProductInteractionMenu(Customer loggedInCustomer, Long productId) {
        if (website.getProductFromCatalogById(productId) == null) {
            System.out.println("No such product with entered Id exists");
            return;
        } else if (loggedInCustomer.getShoppingCart().getProductList().contains(website.getProductFromCatalogById(productId))) {
            System.out.println("Product already in your cart");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("Please Select option below");
        System.out.println("1.Add to Cart");
        System.out.println("2.Remove from Cart");
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                loggedInCustomer.getShoppingCart().addProduct(website.getProductFromCatalogById(productId));
                System.out.println("Product  added to Cart");
                break;
            case 2:
                loggedInCustomer.getShoppingCart().removeProduct(website.getProductFromCatalogById(productId));
                System.out.println("Product  removed from Cart");
                break;
        }

    }

    private static void doSignUp() {

        String name;
        String address;
        Date dateOfBirth;
        String username;
        String password;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter Customer Details");
        System.out.println("Enter First Name");
        name = scanner.next();

        System.out.println("Enter Address");
        address = scanner.next();
        System.out.println("Enter Date of Birth in dd/mm/yyyy");
        dateOfBirth = new Date(scanner.next());

        System.out.println("Enter Username");
        username = scanner.next();
        System.out.println("Enter password");
        password = scanner.next();

        Credential credential = new Credential(username, password);
        User user = new User(name, new Long(website.getCustomers().size() + 1), address, dateOfBirth);
        Customer customer = new Customer(user, credential);
        website.getCustomers().add(customer);
        System.out.println("Sign Up Completed Successfully Now you can login using your username and password");
    }
}
