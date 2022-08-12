package com.heg.bookstoreapp.service.impl;

import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.Category;
import com.heg.bookstoreapp.repo.BookRepo;
import com.heg.bookstoreapp.repo.CategoryRepo;
import com.heg.bookstoreapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    private final BookRepo bookRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo, BookRepo bookRepo) {
        this.categoryRepo = categoryRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public Category insert(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found category with id: " + id));
    }

    @Override
    public void delete(Long id) {
        Category category = this.findById(id);
        categoryRepo.delete(category);
    }

    @Override
    public Category updateById(Long id, Category category) {
        Category currentCategory = this.findById(id);
        currentCategory.setName(category.getName());
        return categoryRepo.save(currentCategory);
    }

    @Override
    public List<Book> getBooksById(Long id) {
        return bookRepo.getBooksByCategory_Id(id);
    }
}
