package com.example.AOManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportFormDisplayDto {

    @Data
    public static class Product {
        private String productImage;
        private String name;
        private int quantity;
        private long unitPrice;
        private long totalPrice;
    }

    private String id;
    private String orderSupplierId;
    private long createDate;
    private String supplierName;
    private String employeeName;
    private List<Product> productList;
    private long totalPriceImportForm;
    private long createAt;
    private long updateAt;
}
