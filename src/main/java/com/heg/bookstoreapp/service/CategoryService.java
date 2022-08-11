package com.heg.bookstoreapp.service;

import com.heg.bookstoreapp.model.Category;

import java.util.List;

public interface CategoryService {

    Category insert(Category category);

    List<Category> findAll();

    Category findById(Long id);

    void delete(Long id);

    Category updateById(Long id,Category category);
}
