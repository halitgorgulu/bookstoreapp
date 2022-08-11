package com.heg.bookstoreapp.api;

import com.heg.bookstoreapp.model.Book;
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
    List<Book> getBooks(){
        return bookService.findAll();
    }

    @PostMapping("/addBook")
    Book createBook(@RequestBody Book book){
        return bookService.insert(book);
    }

    @GetMapping("/getById/{id}")
    Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PutMapping("/update/{id}")
    Book updatePut(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return bookService.updateById(id,book);
    }

    @DeleteMapping("/delete/{id}")
    void deleteBookById(@PathVariable Long id) {
        bookService.delete(id);
    }


}
