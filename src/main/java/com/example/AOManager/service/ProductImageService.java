package com.example.AOManager.service;

import com.example.AOManager.payload.request.AddImageRequest;
import com.example.AOManager.response.ApiResponse;

public interface ProductImageService {
    ApiResponse<?> addImage(AddImageRequest addImageRequest);

    ApiResponse<?> deleteImage(String imageId);
}
