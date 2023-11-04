package com.example.AOManager.controller.manager;

import com.example.AOManager.payload.request.AddImageRequest;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product-image")
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;

    @GetMapping
    ApiResponse<?> getProductImages(@RequestParam String productId) {return this.productImageService.getProductImages(productId);}

    @PostMapping
    ApiResponse<?> addImage(@RequestBody AddImageRequest addImageRequest) {return this.productImageService.addImage(addImageRequest);}

    @DeleteMapping("/{imageId}")
    ApiResponse<?> deleteImage(@PathVariable String imageId) {return this.productImageService.deleteImage(imageId);}
}
