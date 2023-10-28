package com.example.AOManager.service.impl;

import com.example.AOManager.dto.customer.ProductOfCartDisplayDto;
import com.example.AOManager.entity.CartDetailEntity;
import com.example.AOManager.repository.CartDetailRepository;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.AOManager.common.Message.*;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Override
    public ApiResponse<?> getCart(String customerId) {
        List<ProductOfCartDisplayDto> productsList = new ArrayList<>();
        try {
            List<CartDetailEntity> cartDetailEntityList = this.cartDetailRepository.findByCustomerId_Id(UUID.fromString(customerId)).get();
            cartDetailEntityList = cartDetailEntityList.stream()
                    .filter(cartDetail -> cartDetail.getOrderCustomerId() != null)
                    .collect(Collectors.toList());
            for (CartDetailEntity cartDetailEntity : cartDetailEntityList) {
                productsList.add(new ProductOfCartDisplayDto(cartDetailEntity));
            }
            return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PRODUCTS_lIST_SUCCESS, productsList);
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PRODUCTS_lIST_FAIL, null);
        }
    }
}
