package com.heg.bookstoreapp.service;

import com.heg.bookstoreapp.model.BookStore;

import java.util.List;

public interface BookStoreService {

    BookStore insert(BookStore bookStore);

    List<BookStore> findAll();

    BookStore findById(Long id);

    BookStore updateById(Long id, BookStore bookStore);

    void deleteById(Long id);


}
