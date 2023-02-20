package com.mgo.model.departure;

import com.mgo.entity.Category;

import com.mgo.model.CategoryModel;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(name = "DepartureCategory")
public class DepartureCategoryModel extends CategoryModel {


    @Schema(name = "price", description = "The price", type = SchemaType.NUMBER)
     Double price;



    
    public DepartureCategoryModel() {
    }

    public DepartureCategoryModel(Category category, Double price) {
        super(category);
        this.price = price;
    }

    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

   


  
    

    
}