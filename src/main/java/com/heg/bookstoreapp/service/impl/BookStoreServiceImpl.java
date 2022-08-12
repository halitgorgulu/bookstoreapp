package com.heg.bookstoreapp.service.impl;

import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.BookStore;
import com.heg.bookstoreapp.repo.BookRepo;
import com.heg.bookstoreapp.repo.BookStoreRepo;
import com.heg.bookstoreapp.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    @Autowired
    private BookStoreRepo bookStoreRepo;

    @Autowired
    private BookRepo bookRepo;

    @Override
    public BookStore insert(BookStore bookStore) {
        List<BookStore> bookStoreList = bookStoreRepo.findAll();
        for(BookStore bookStoreIt:bookStoreList){
            if(bookStore.getName().equals(bookStoreIt.getName())){
                throw new RuntimeException("This book is already inserted.");
            }
        }
        this.bookStoreRepo.save(bookStore);
        return bookStore;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepo.findAll();
    }

    @Override
    public BookStore findById(Long id) {
        return bookStoreRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found book with id: " + id));
    }

    @Override
    public BookStore updateById(Long id, BookStore bookStore) {
        BookStore currentBookStore = this.findById(id);
        currentBookStore.setName(bookStore.getName());
        currentBookStore.setAddress(bookStore.getAddress());
        currentBookStore.setCity(bookStore.getCity());
        currentBookStore.setBookStoreBooks(bookStore.getBookStoreBooks());
        return bookStoreRepo.save(currentBookStore);
    }

    //public BookStore putBookToBookStore(Long bookStoreId, Long bookId) {
        //BookStore currentBookStore = this.findById(bookStoreId);
        //Book currentBook = bookRepo.findById(bookId);

    //}

    @Override
    public void deleteById(Long id) {
        BookStore currentBookStore =  this.findById(id);
        bookStoreRepo.delete(currentBookStore);
    }


    /*public List<BookStore> getAllBooks(Long id) {
       // return bookStoreRepo.getAllBookStoresByBookId(id);
    }*/
}
