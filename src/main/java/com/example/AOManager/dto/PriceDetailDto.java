package com.example.AOManager.dto;

import com.example.AOManager.entity.PriceDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDetailDto {

    private String id;
    private long applyDate;
    private String note;
    private String employeeId;
    private String employeeName;
    private String productId;

    public PriceDetailDto(PriceDetailEntity priceDetailEntity) {
        this.setId(priceDetailEntity.getId().toString());
        this.setApplyDate(priceDetailEntity.getApplyDate());
        this.setNote(priceDetailEntity.getNote());
        this.setEmployeeId(priceDetailEntity.getEmployeeId().getId().toString());
        this.setEmployeeName(priceDetailEntity.getEmployeeId().getLastName() + " " + priceDetailEntity.getEmployeeId().getFirstName());
        this.setProductId(priceDetailEntity.getProductId().getId().toString());
    }
}
