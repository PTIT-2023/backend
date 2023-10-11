package com.example.AOManager.service.impl;

import com.example.AOManager.common.CheckString;
import com.example.AOManager.dto.ProductDto;
import com.example.AOManager.entity.ProductEntity;
import com.example.AOManager.entity.ProductImageEntity;
import com.example.AOManager.payload.response.ApiResponse;
import com.example.AOManager.repository.CategoryRepository;
import com.example.AOManager.repository.ProductImageRepository;
import com.example.AOManager.repository.ProductRepository;
import com.example.AOManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Override
    public List<ProductDto> getProductsList(String categoryId, int page, int limit) {
        List<ProductEntity> productsList;
        if(CheckString.isValidUUID(categoryId)) {
            productsList = this.productRepository.getProductsListWithCategory(UUID.fromString(categoryId), page - 1, limit);
        } else {
            productsList = this.productRepository.getProductsList(page - 1, limit);
        }
        return productsList.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @Override
    public ApiResponse<?> getProduct(String id) {
        if(CheckString.stringIsNullOrEmpty(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad request", null);
        }
        ProductDto productDto;
        try {
            productDto = new ProductDto(this.productRepository.findById(UUID.fromString(id)).get());
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to get product", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Get product successfully", productDto);
    }

    @Override
    public ApiResponse<?> createProduct(ProductDto productDto) {
        if(null == productDto) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad request", null);
        }
        ProductEntity productEntity = productDto.toEntity();
        productEntity.setCategoryId(this.categoryRepository.findById(UUID.fromString(productDto.getCategoryId())).get());
        try {
            ProductEntity productEntityCreate = this.productRepository.save(productEntity);
            for(String productImage : productDto.getImageList()) {
                ProductImageEntity productImageEntity = new ProductImageEntity();
                productImageEntity.setUrl(productImage);
                productImageEntity.setProductId(productEntityCreate);
                try {
                    this.productImageRepository.save(productImageEntity);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to add picture for product", null);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to create product", null);
        }
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Create product successfully", null);
    }

    @Override
    public ApiResponse<?> updateProduct(ProductDto productDto) {
        if(null == productDto) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad request", null);
        }
        try {
            ProductEntity productEntityBef;
            try {
                productEntityBef = this.productRepository.findById(UUID.fromString(productDto.getId())).get();
            } catch (Exception e) {
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Not found product with this id", null);
            }
            ProductEntity productEntityAft = productDto.toEntity();
            productEntityAft.setCategoryId(this.categoryRepository.findById(UUID.fromString(productDto.getCategoryId())).get());
            ProductEntity productEntityUpdate = this.productRepository.save(productEntityAft);
            List<ProductImageEntity> listDelete = this.productImageRepository.findByProductId_Id(productEntityUpdate.getId()).get();
            this.productImageRepository.deleteAll(listDelete);
            System.out.println(listDelete);
            for(String productImage : productDto.getImageList()) {
                ProductImageEntity productImageEntity = new ProductImageEntity();
                productImageEntity.setUrl(productImage);
                productImageEntity.setProductId(productEntityUpdate);
                try {
                    this.productImageRepository.save(productImageEntity);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to update picture for product", null);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to update", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Updated", null);
    }

    @Override
    public ApiResponse<?> deleteProduct(String id) {
        if(CheckString.stringIsNullOrEmpty(id)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad request", null);
        }
        try {
            ProductEntity productEntity;
            try {
                productEntity = this.productRepository.findById(UUID.fromString(id)).get();
            } catch (Exception e) {
                System.out.println(e);
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Not found product with this id", null);
            }
            this.productRepository.delete(productEntity);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Faild to delete", null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Deleted", null);
    }
}
