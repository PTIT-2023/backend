package com.example.AOManager.dto.customer;

import com.example.AOManager.entity.CartDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOfCartDisplayDto {

    private String id;
    private String imageUrl;
    private String name;
    private Integer quantity;
    private Long unitPrice;

    public ProductOfCartDisplayDto(CartDetailEntity cartDetailEntity) {
        this.setId(cartDetailEntity.getId().toString());
        this.setImageUrl(cartDetailEntity.getProductId().getImageListString().size() > 0 ? cartDetailEntity.getProductId().getImageListString().get(0) : null);
        this.setName(cartDetailEntity.getProductId().getName());
        this.setQuantity(cartDetailEntity.getQuantity());
        this.setUnitPrice(cartDetailEntity.getPrice());
    }
}
