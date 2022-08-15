package com.heg.bookstoreapp.api;

import com.heg.bookstoreapp.dto.BookDto;
import com.heg.bookstoreapp.dto.CategoryDto;
import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.Category;
import com.heg.bookstoreapp.service.BookService;
import com.heg.bookstoreapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/allCategories")
    List<CategoryDto> getCategories() {
        return categoryService.findAll();
    }

    @PostMapping("/addCategory")
    CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.insert(categoryDto);
    }

    @GetMapping("/getById/{id}")
    CategoryDto getById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PutMapping("/update/{id}")
    CategoryDto updateById(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return categoryService.updateById(id, categoryDto);
    }

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id) {
        try{
            categoryService.deleteById(id);
        } catch (Exception ignored) {
            System.out.println("The deletion cannot be performed.");
        }
    }

}
