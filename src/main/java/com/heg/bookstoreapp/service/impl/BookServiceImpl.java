package com.heg.bookstoreapp.service.impl;

import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.repo.BookRepo;
import com.heg.bookstoreapp.service.BookService;
import com.heg.bookstoreapp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    private final CategoryService categoryService;


    public BookServiceImpl(BookRepo bookRepo, CategoryService categoryService) {
        this.bookRepo = bookRepo;
        this.categoryService = categoryService;
    }


    @Override
    public Book insert(Book book) {

        List<Book> bookList = bookRepo.findAll();
        for (Book bookIt : bookList) {
            if (book.getName().equals(bookIt.getName())) {
                throw new RuntimeException("This book is already inserted.");
            }
        }
        this.bookRepo.save(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found book with id: " + id));
    }

    @Override
    public Book updateById(Long id, Book book) {
        Book currentBook = this.findById(id);
        currentBook.setCategory(book.getCategory());
        currentBook.setName(book.getName());
        currentBook.setImageLink(book.getImageLink());
        currentBook.setPrice(book.getPrice());
        return bookRepo.save(currentBook);
    }

    @Override
    public void delete(Long id) {
        Book currentBook = this.findById(id);
        bookRepo.delete(currentBook);

    }

    @Override
    public List<Book> getBooksByCategoryId(Long id) {
        return bookRepo.getBooksByCategory_Id(id);
    }
}
