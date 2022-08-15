package com.heg.bookstoreapp.service;

import com.heg.bookstoreapp.dto.BookDto;
import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.BookStore;

import java.util.List;

public interface BookService {

    BookDto insert(BookDto book);

    List<BookDto> findAll();

    BookDto findById(Long id);

    BookDto updateById(Long id,BookDto book);

    void delete(Long id);

    List<Book> getBooksByCategoryId(Long id);

    List<BookStore> getBookStoreByContainsBook(Long id);
}
