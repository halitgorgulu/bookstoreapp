package com.heg.bookstoreapp.service;

import com.heg.bookstoreapp.dto.BookStoreDto;

import java.util.List;

public interface BookStoreService {

    BookStoreDto insert(BookStoreDto bookStore);

    List<BookStoreDto> findAll();

    BookStoreDto findById(Long id);

    BookStoreDto updateById(Long id, BookStoreDto bookStoreDto);

    void deleteById(Long id);

    BookStoreDto putBookToBookStore(Long bookStoreId, Long bookId);

    BookStoreDto deleteBookFromBookstore(Long bookStoreId, Long bookId);
}
