package com.example.AOManager.dto;

import com.example.AOManager.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private String habitat;
    private double temperature;
    private double ph;
    private String position;
    private String reproductionMethod;
    private String foodType;
    private double maxSize;
    private int inventoryQuantity;
    private int soldQuantity;
    private boolean status;
    private String categoryId;
    private String categoryName;
    private List<String> imageList;
    private long price;

    public ProductDto(ProductEntity productEntity) {
        this.id = productEntity.getId().toString();
        this.name = productEntity.getName();
        this.description = productEntity.getDescription();
        this.habitat = productEntity.getHabitat();
        this.temperature = productEntity.getTemperature();
        this.ph = productEntity.getPh();
        this.position = productEntity.getPosition();
        this.reproductionMethod = productEntity.getReproductionMethod();
        this.foodType = productEntity.getFoodType();
        this.maxSize = productEntity.getMaxSize();
        this.inventoryQuantity = productEntity.getInventoryQuantity();
        this.soldQuantity = productEntity.getSoldQuantity();
        this.status = productEntity.getStatus();
        this.categoryId = productEntity.getCategoryId().getId().toString();
        this.categoryName = productEntity.getCategoryId().getName();
        this.imageList = productEntity.getImageListString();
        this.price = productEntity.getCurrentPrice();
    }
}
