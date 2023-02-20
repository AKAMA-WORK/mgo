package com.mgo.models;

import com.mgo.entities.Category;


public class CategoryInfo{
    private Integer idCategory;
    private String name;
    private Integer price;



    
    public CategoryInfo() {
    }

    public CategoryInfo(Category category, Integer price) {
        this.idCategory = category.getIdcategory();
        this.name=category.getName();
        this.price = price;
    }

    
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
    

    
}