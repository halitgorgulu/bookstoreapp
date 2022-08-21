package com.heg.bookstoreapp.service.impl;

import com.heg.bookstoreapp.dto.CategoryDto;
import com.heg.bookstoreapp.model.Category;
import com.heg.bookstoreapp.repo.CategoryRepo;
import com.heg.bookstoreapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) {
        Category categoryInstance = modelMapper.map(categoryDto, Category.class);

        List<Category> categoryList = categoryRepo.findAll();
        for (Category categoryIt : categoryList) {
            if (categoryInstance.getName().equals(categoryIt.getName())) {
                throw new RuntimeException("This category is already inserted.");
            }
        }
        return modelMapper.map(categoryRepo.save(categoryInstance), CategoryDto.class);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent()) {
            return modelMapper.map(category.get(), CategoryDto.class);
        }
        throw new RuntimeException("Not found category with id: " + id);
    }

    @Override
    public void deleteById(Long id) {

        Optional<Category> category = categoryRepo.findById(id);

        if (category.isPresent()) {
            categoryRepo.deleteById(id);
        }
        throw new RuntimeException("Not found category with id: " + id);


    }

    @Override
    public CategoryDto updateById(Long id, CategoryDto categoryDto) {
        Optional<Category> categoryInstance = categoryRepo.findById(id);

        if (categoryInstance.isPresent()) {
            categoryInstance.get().setName(categoryDto.getName());
            categoryInstance.get().setCategoryImage(categoryDto.getCategoryImage());
            categoryRepo.save(categoryInstance.get());
            return modelMapper.map(categoryInstance.get(), CategoryDto.class);
        }
        throw new RuntimeException("Not found category with id: " + id);
    }

}
