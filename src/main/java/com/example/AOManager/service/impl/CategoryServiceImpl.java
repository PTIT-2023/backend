package com.example.AOManager.service.impl;

import com.example.AOManager.dto.CategoryDto;
import com.example.AOManager.dto.RoleDto;
import com.example.AOManager.entity.CategoryEntity;
import com.example.AOManager.entity.RoleEntity;
import com.example.AOManager.repository.CategoryRepository;
import com.example.AOManager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryEntity> categoryList = this.categoryRepository.findAll();
        return categoryList.stream().map(CategoryDto::new).collect(Collectors.toList());
    }
}
