package com.heg.bookstoreapp.api;

import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.BookStore;
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
    List<BookStore> getBookStores(){
        return bookStoreService.findAll();
    }

    @PostMapping("/addBookStore")
    BookStore createBookStore(@RequestBody BookStore bookStore){
        return bookStoreService.insert(bookStore);
    }

    @PutMapping("/put/{bookStoreId}/books/{bookId}")
    BookStore bookToBookStore(@PathVariable Long bookStoreId, @PathVariable Long bookId){
        BookStore bookStore = bookStoreService.findById(bookStoreId) ;
        Book book = bookService.findById(bookId);
        bookStore.getBookStoreBooks().add(book);
        return bookStoreService.updateById(bookStoreId, bookStore);
    }

    /*@GetMapping("/allBooksInBookstore/{id}")
    List<BookStore> getAllBooksById(Long id) {
        return bookStoreService.getAllBooks(id);
    }*/

}
