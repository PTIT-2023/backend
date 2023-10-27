package com.example.AOManager.dto.customer;

import com.example.AOManager.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDisplayDto {

    private String name;
    private Long price;
    private Integer soldQuantity;

    public ProductDisplayDto(ProductEntity productEntity) {
        this.setName(productEntity.getName());
        this.setPrice(productEntity.getCurrentPrice());
        this.setSoldQuantity(productEntity.getSoldQuantity());
    }
}
