package com.heg.bookstoreapp.service;

import com.heg.bookstoreapp.model.Book;

import java.util.List;

public interface BookService {

    Book insert(Book book);

    List<Book> findAll();

    Book findById(Long id);

    Book updateById(Long id,Book book);

    void delete(Long id);

    List<Book> getBooksByCategoryId(Long id);
}
