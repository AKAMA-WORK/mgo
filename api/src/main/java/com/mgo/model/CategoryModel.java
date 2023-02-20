package com.mgo.model;

import com.mgo.entity.Category;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;



@Schema(name = "Category")
public class CategoryModel{

    @Schema(name = "categoryId", description = "The id of category")
     String categoryId;

    @Schema(name = "name", description = "The name of category")
     String name;




    
    public CategoryModel() {
    }

    public CategoryModel(Category category) {
        this.categoryId = category.getCategoryId();
        this.name=category.getName();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
    

    
}