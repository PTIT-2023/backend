package com.example.AOManager.controller;

import com.example.AOManager.dto.ProductDto;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

import static com.example.AOManager.common.Message.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ApiResponse<?> getProduct(@PathVariable String id) {return this.productService.getProduct(id);}

    @GetMapping
    public ApiResponse<?> getProductsList(@RequestParam String categoryId, @RequestParam String orderByPrice, @RequestParam int page, @RequestParam int limit) {
        List<ProductDto> productsList;
        try {
            productsList = this.productService.getProductsList(categoryId, page, limit);
            if(orderByPrice.equals("ASC") && productsList.size() > 0) {
                productsList.sort(Comparator.comparingLong(ProductDto::getPrice));
            } else if(orderByPrice.equals("DESC") && productsList.size() > 0)  {
                productsList.sort((a1, a2) -> -Long.compare(a1.getPrice(), a2.getPrice()));
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MSG_GET_PRODUCTS_lIST_FAIL, null);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), MSG_GET_PRODUCTS_lIST_SUCCESS, productsList);
    }

    @PostMapping
    public ApiResponse<?> createProduct(@Validated @RequestBody ProductDto productDto) {return this.productService.createProduct(productDto);}

    @PutMapping()
    public ApiResponse<?> updateProduct(@Validated @RequestBody ProductDto productDto) {return this.productService.updateProduct(productDto);}

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteProduct(@PathVariable String id) {return this.productService.deleteProduct(id);}
}
