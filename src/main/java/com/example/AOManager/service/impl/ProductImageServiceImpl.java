package com.example.AOManager.service.impl;

import com.example.AOManager.entity.ProductImageEntity;
import com.example.AOManager.payload.request.AddImageRequest;
import com.example.AOManager.repository.ProductImageRepository;
import com.example.AOManager.repository.ProductRepository;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.AOManager.common.Message.*;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ApiResponse<?> addImage(AddImageRequest addImageRequest) {
        try {
            ProductImageEntity productImageEntity = new ProductImageEntity();
            productImageEntity.setUrl(addImageRequest.getUrl());
            productImageEntity.setProductId(this.productRepository.findById(UUID.fromString(addImageRequest.getProductId())).get());
            this.productImageRepository.save(productImageEntity);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_ADD_IMAGE_SUCCESS, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ADD_IMAGE_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> deleteImage(String imageId) {
        try {
            this.productImageRepository.deleteById(UUID.fromString(imageId));
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_DELETE_SUCCESS, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ERROR_PROCESSING, null);
        }
    }
}
