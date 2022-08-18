package com.heg.bookstoreapp.service;

import com.heg.bookstoreapp.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto insert(CategoryDto categoryDto);

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    void deleteById(Long id);

    CategoryDto updateById(Long id, CategoryDto categoryDto);

}
