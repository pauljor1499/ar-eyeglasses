package com.google.ar.sceneform.samples.augmentedfaces.product;

public class Product {
    public String name;
    public String price;
    public String color;
    public int quantity;
    public double total;

    public Product( String prod_name, String prod_price, String prod_color, int prod_quantity, double prod_total) {

        this.name = prod_name;
        this.price = prod_price;
        this.color = prod_color;
        this.quantity = prod_quantity;
        this.total = prod_total;
    }
}
