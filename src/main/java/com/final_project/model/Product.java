package com.final_project.model;

import java.text.NumberFormat;
import java.util.Locale;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.mail.Part;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private String description;
    private int quantity;
    private float price;
    

    public Product() {
        super();
        name = "";
        description = "";
      
        quantity = 0;
        price = 0;
    }

    public Product(int id, String name, String description, int quantity, int price) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    
        this.quantity = quantity;
        this.price = price;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return this.price;
    }

    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        return currency.format(price);
    }
}
