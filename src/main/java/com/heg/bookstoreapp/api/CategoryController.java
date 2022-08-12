package com.heg.bookstoreapp.api;

import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.Category;
import com.heg.bookstoreapp.service.BookService;
import com.heg.bookstoreapp.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final BookService bookService;

    public CategoryController(CategoryService categoryService, BookService bookService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping("/allCategories")
    List<Category> getCategories() {
        return categoryService.findAll();
    }

    @PostMapping("/addCategory")
    Category createCategory(@RequestBody Category category) {
        return categoryService.insert(category);
    }


    @GetMapping("/allBooksInCategory/{id}")
    List<Book> getBooksByCategory(@PathVariable Long id) {
        return categoryService.getBooksById(id);
    }
}
