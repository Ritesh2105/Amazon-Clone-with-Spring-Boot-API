package com.example.amazoncloneapi.models;

import org.springframework.data.annotation.Id;


public class Product {
    @Id
//    private String id;
    private String product_id;
    private String category_id;
    private String title;
    private String poster;
    private String description;
    private String price;
    private String bestSeller;

    public Product() {
    }

    public Product(String id, String product_id, String category_id, String title, String poster, String description, String price, String bestSeller) {
//        this.id = id;
        this.product_id = product_id;
        this.category_id = category_id;
        this.title = title;
        this.poster = poster;
        this.description = description;
        this.price = price;
        this.bestSeller = bestSeller;
    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(String bestSeller) {
        this.bestSeller = bestSeller;
    }

    @Override
    public String toString() {
        return "Product{" +
//                "id='" + id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", category_id='" + category_id + '\'' +
                ", title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", bestSeller='" + bestSeller + '\'' +
                '}';
    }

}
