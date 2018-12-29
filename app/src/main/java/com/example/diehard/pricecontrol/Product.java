package com.example.diehard.pricecontrol;

/**
 * Created by DieHard on 16/12/2018.
 */

public class Product {
    int prod_id;
    String name;
    String qty;
    Float price;

    public Product() {

    }

    public Product(int id, String name, String qty, Float price){
        this.prod_id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
