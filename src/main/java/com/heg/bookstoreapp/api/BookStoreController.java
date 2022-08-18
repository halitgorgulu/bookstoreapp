package com.heg.bookstoreapp.api;

import com.heg.bookstoreapp.dto.BookStoreDto;
import com.heg.bookstoreapp.service.BookService;
import com.heg.bookstoreapp.service.BookStoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookstores")
public class BookStoreController {


    private final BookStoreService bookStoreService;

    private final BookService bookService;

    public BookStoreController(BookStoreService bookStoreService, BookService bookService) {
        this.bookStoreService = bookStoreService;
        this.bookService = bookService;
    }

    @GetMapping("/allBookStores")
    List<BookStoreDto> getBookStores() {
        return bookStoreService.findAll();
    }

    @PostMapping("/addBookStore")
    BookStoreDto createBookStore(@RequestBody BookStoreDto bookStore) {
        return bookStoreService.insert(bookStore);
    }

    @GetMapping("/getById/{id}")
    BookStoreDto getBookStoreById(@PathVariable Long id) {
        return bookStoreService.findById(id);
    }

    @PutMapping("/updateById/{id}")
    BookStoreDto updateBookstore(@PathVariable Long id, @RequestBody BookStoreDto bookStoreDto) {
        return bookStoreService.updateById(id, bookStoreDto);
    }

    @PutMapping("/put/{bookStoreId}/books/{bookId}")
    BookStoreDto bookToBookStore(@PathVariable Long bookStoreId, @PathVariable Long bookId) {

        return bookStoreService.putBookToBookStore(bookStoreId, bookId);
    }

    @PutMapping("/delete/{bookStoreId}/books/{bookId}")
    BookStoreDto deleteBookFromBookStore(@PathVariable Long bookStoreId, @PathVariable Long bookId) {

        return bookStoreService.deleteBookFromBookstore(bookStoreId, bookId);
    }

    @DeleteMapping("/delete/{id}")
    void deleteBookStoreById(@PathVariable Long id) {
        try {
            bookStoreService.deleteById(id);
        } catch (Exception ignored) {
            System.out.println("The deletion cannot be performed.");
        }
    }


    /*@GetMapping("/allBooksInBookstore/{id}")
    List<BookStore> getAllBooksById(Long id) {
        return bookStoreService.getAllBooks(id);
    }*/

}
