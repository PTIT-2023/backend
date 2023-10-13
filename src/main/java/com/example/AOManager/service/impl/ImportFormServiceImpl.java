package com.example.AOManager.service.impl;

import com.example.AOManager.entity.ImportDetailEntity;
import com.example.AOManager.entity.ImportFormEntity;
import com.example.AOManager.repository.*;
import com.example.AOManager.request.CreateImportFormRequest;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.ImportFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.AOManager.common.Message.*;

@Service
public class ImportFormServiceImpl implements ImportFormService {
    @Autowired
    ImportFormRepository importFormRepository;

    @Autowired
    ImportDetailRepository importDetailRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    OrderSupplierRepository orderSupplierRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ApiResponse<?> createImportForm(CreateImportFormRequest createImportFormRequest) {
        ImportFormEntity importFormEntity;
        try {
            ImportFormEntity importFormToAdd = new ImportFormEntity();
            importFormToAdd.setCreateDate(createImportFormRequest.getCreateDate());
            importFormToAdd.setEmployeeId(this.usersRepository.findById(UUID.fromString(createImportFormRequest.getEmployeeId())).get());
            importFormToAdd.setOrderSupplierId(this.orderSupplierRepository.findById(UUID.fromString(createImportFormRequest.getOrderSupplierId())).get());
            importFormEntity = this.importFormRepository.save(importFormToAdd);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_CREATE_IMPORT_FORM_FAIL, null);
        }
        try {
            for (CreateImportFormRequest.ImportDetail importDetail : createImportFormRequest.getImportDetailList()) {
                ImportDetailEntity importDetailEntity = new ImportDetailEntity();
                importDetailEntity.setPrice(importDetail.getPrice());
                importDetailEntity.setQuantity(importDetail.getQuantity());
                importDetailEntity.setImportId(importFormEntity);
                importDetailEntity.setProductId(this.productRepository.findById(UUID.fromString(importDetail.getProductId())).get());
                this.importDetailRepository.save(importDetailEntity);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ADD_PRODUCT_FOR_IMPORT_FROM_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_CREATE_IMPORT_FORM_SUCCESS, null);
    }
}
