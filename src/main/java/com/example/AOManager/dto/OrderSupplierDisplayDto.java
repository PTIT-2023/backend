package com.example.AOManager.dto;

import com.example.AOManager.entity.OrderSupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSupplierDisplayDto {

    @Data
    public static class Product {
        private String productImage;
        private String name;
        private int quantity;
        private long unitPrice;
        private long totalPrice;
    }

    private String id;
    private String supplierName;
    private long orderDate;
    private long deliverydate;
    private String status;
    private String employeeName;
    private long totalPriceOrder;
    private List<Product> productsList;
    private long createAt;
    private long updateAt;
}
