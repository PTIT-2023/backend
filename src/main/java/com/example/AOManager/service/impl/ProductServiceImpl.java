package com.example.AOManager.service.impl;

import com.example.AOManager.common.CheckString;
import com.example.AOManager.dto.ProductDto;
import com.example.AOManager.entity.ProductEntity;
import com.example.AOManager.entity.ProductImageEntity;
import com.example.AOManager.repository.*;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.AOManager.common.Message.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ImportDetailRepository importDetailRepository;

    @Autowired
    OrderSupplierDetailRepository orderSupplierDetailRepository;

    @Autowired
    PriceDetailRepository priceDetailRepository;

    @Autowired
    DeductionRepository deductionRepository;

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Override
    public long getTotalRecord(String categoryId) {
        if (CheckString.isValidUUID(categoryId)) {
            return this.productRepository.findByCategoryId_Id(UUID.fromString(categoryId)).get().size();
        } else {
            return this.productRepository.findAll().size();
        }
    }

    @Override
    public List<ProductDto> getProductsList(String categoryId, int page, int limit) {
        List<ProductEntity> productsList;
        if (CheckString.isValidUUID(categoryId)) {
            productsList = this.productRepository.getProductsListWithCategory(UUID.fromString(categoryId), (page - 1) * limit, limit);
        } else {
            productsList = this.productRepository.getProductsList((page - 1) * limit, limit);
        }
        return productsList.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @Override
    public ApiResponse<?> getProduct(String id) {
        if (CheckString.stringIsNullOrEmpty(id) || !CheckString.isValidUUID(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try {
            ProductDto productDto = new ProductDto(this.productRepository.findById(UUID.fromString(id)).get());
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PRODUCT_SUCCESS, productDto);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PRODUCT_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> createProduct(ProductDto productDto) {
        if (null == productDto) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        ProductEntity productEntity = productDto.toEntity();
        productEntity.setStatus(false);
        productEntity.setCategoryId(this.categoryRepository.findById(UUID.fromString(productDto.getCategoryId())).get());
        try {
            ProductEntity productEntityCreate = this.productRepository.save(productEntity);
            for (String productImage : productDto.getImageList()) {
                ProductImageEntity productImageEntity = new ProductImageEntity();
                productImageEntity.setUrl(productImage);
                productImageEntity.setProductId(productEntityCreate);
                try {
                    this.productImageRepository.save(productImageEntity);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_ADD_PICTURE_PRODUCT_FAIL, null);
                }
            }
            return new ApiResponse<>(HttpStatus.CREATED.value(), MSG_CREATE_SUCCESS, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_CREATE_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> updateProduct(ProductDto productDto) {
        if (null == productDto) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        try {
            try {
                this.productRepository.findById(UUID.fromString(productDto.getId())).get();
            } catch (Exception e) {
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND_BY_ID, null);
            }
            ProductEntity productEntityAft = productDto.toEntity();
            productEntityAft.setCategoryId(this.categoryRepository.findById(UUID.fromString(productDto.getCategoryId())).get());
            ProductEntity productEntityUpdate = this.productRepository.save(productEntityAft);
            List<ProductImageEntity> listDelete = this.productImageRepository.findByProductId_Id(productEntityUpdate.getId()).get();
            this.productImageRepository.deleteAll(listDelete);
            System.out.println(listDelete);
            for (String productImage : productDto.getImageList()) {
                ProductImageEntity productImageEntity = new ProductImageEntity();
                productImageEntity.setUrl(productImage);
                productImageEntity.setProductId(productEntityUpdate);
                try {
                    this.productImageRepository.save(productImageEntity);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_UPDATE_PICTURE_PRODUCT_FAIL, null);
                }
            }
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_UPDATE_SUCCESS, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_UPDATE_FAIL, null);
        }
    }

    @Override
    public ApiResponse<?> deleteProduct(String id) {
        if (CheckString.stringIsNullOrEmpty(id) || !CheckString.isValidUUID(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), MSG_BAD_REQUEST, null);
        }
        ProductEntity productEntity;
        try {
            productEntity = this.productRepository.findById(UUID.fromString(id)).get();
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND_BY_ID, null);
        }
        if (this.importDetailRepository.existsByProductId_Id(productEntity.getId())) {
            productEntity.setStatus(false);
            this.productRepository.save(productEntity);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_DELETE_PRODUCT_FAIL_IMPORT, null);
        } else if (this.orderSupplierDetailRepository.existsByProductId_Id(productEntity.getId())) {
            productEntity.setStatus(false);
            this.productRepository.save(productEntity);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_DELETE_PRODUCT_FAIL_ORDER_SUPPLIER, null);
        } else if (this.priceDetailRepository.existsByProductId_Id(productEntity.getId())) {
            productEntity.setStatus(false);
            this.productRepository.save(productEntity);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_DELETE_PRODUCT_FAIL_PRICE, null);
        } else if (this.deductionRepository.existsByProductId_Id(productEntity.getId())) {
            productEntity.setStatus(false);
            this.productRepository.save(productEntity);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_DELETE_PRODUCT_FAIL_DEDUCTION, null);
        } else if (this.cartDetailRepository.existsByProductId_Id(productEntity.getId())) {
            productEntity.setStatus(false);
            this.productRepository.save(productEntity);
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_DELETE_PRODUCT_FAIL_CART, null);
        } else {
            try {
                List<ProductImageEntity> productImageEntityList = this.productImageRepository.findByProductId_Id(productEntity.getId()).get();
                this.productImageRepository.deleteAll(productImageEntityList);
                this.productRepository.delete(productEntity);
                return new ApiResponse<>(HttpStatus.OK.value(), MSG_DELETE_SUCCESS, null);
            } catch (Exception e) {
                return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_DELETE_FAIL, null);
            }
        }
    }
}
