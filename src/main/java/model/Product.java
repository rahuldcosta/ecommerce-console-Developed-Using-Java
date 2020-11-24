package model;

public class Product {

    private String name;
    private Long productId;
    private String description;
    private Double price;

    public Product(String name, Long productId, String description, Double price) {
        this.name = name;
        this.productId = productId;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString(){

        return "Product Name: "+name +"\n"+
                "Product Id: "+productId+"\n"+
                "Description: "+description+"\n"+
                "Price: Rs."+price+"\n";
    }
}
