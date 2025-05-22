package com.tracker.task.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jpa_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "p_id"),
            inverseJoinColumns = @JoinColumn(name = "c_id")

    )
    private List<Category> categories = new ArrayList<>();

    private  double price;

    private  int discount;

    @Transient
    private  int finalPrice;


    public void addCategory(Category category){
            this.categories.add(category);
            category.getProducts().add(this);
    }


    @PrePersist
    public void test(){
        System.out.println("hi, going to save entity");
        //
        this.price=Math.floor(this.price);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
