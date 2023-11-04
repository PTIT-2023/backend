package com.example.AOManager.dto.manager;

import com.example.AOManager.common.Function;
import com.example.AOManager.entity.PriceDetailEntity;
import com.example.AOManager.entity.ProductImageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDto {

    private String id;
    private String productId;
    private String url;

    public ProductImageDto(ProductImageEntity productImageEntity) {
        this.setId(productImageEntity.getId().toString());
        this.setProductId(productImageEntity.getProductId().getId().toString());
        this.setUrl(productImageEntity.getUrl());
    }
}
