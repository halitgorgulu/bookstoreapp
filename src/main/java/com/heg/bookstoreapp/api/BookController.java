package com.heg.bookstoreapp.api;

import com.heg.bookstoreapp.dto.BookDto;
import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.BookStore;
import com.heg.bookstoreapp.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/allBooks")
    List<BookDto> getBooks() {
        return bookService.findAll();
    }

    @PostMapping("/addBook")
    BookDto createBook(@RequestBody BookDto book) {
        return bookService.insert(book);
    }

    @GetMapping("/getById/{id}")
    BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PutMapping("/update/{id}")
    BookDto updatePut(@PathVariable Long id, @RequestBody BookDto book) {
        return bookService.updateById(id, book);
    }

    @DeleteMapping("/delete/{id}")
    void deleteBookById(@PathVariable Long id) {
        try {
            bookService.delete(id);
        } catch (Exception ignored) {
            System.out.println("This book is available in some bookstores. Therefore, the deletion cannot be performed.");
        }
    }

    @GetMapping("/getCategories/{id}")
    List<Book> getBooksByCategoryId(@PathVariable Long id) {
        return bookService.getBooksByCategoryId(id);
    }

    @GetMapping("/getBookStores/{id}")
    List<BookStore> getBookStoreByContainsBook(@PathVariable Long id) {
        return bookService.getBookStoreByContainsBook(id);
    }

}
