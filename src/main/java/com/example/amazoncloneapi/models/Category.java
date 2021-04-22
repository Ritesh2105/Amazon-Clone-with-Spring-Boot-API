package com.example.amazoncloneapi.models;

import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private String category_id;
    private String category_name;
    private String category_poster;

    public Category() {
    }

    public Category(String category_id, String category_name, String category_poster) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_poster = category_poster;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_poster() {
        return category_poster;
    }

    public void setCategory_poster(String category_poster) {
        this.category_poster = category_poster;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id='" + category_id + '\'' +
                ", Category_name='" + category_name + '\'' +
                ", Category_poster='" + category_poster + '\'' +
                '}';
    }
}
