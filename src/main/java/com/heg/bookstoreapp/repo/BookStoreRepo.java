package com.heg.bookstoreapp.repo;

import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStoreRepo extends JpaRepository<BookStore,Long> {
    //List<BookStore> getAllBookStoresByBookId(Long bookId);
    List<BookStore> getBookStoresByBookStoreBooksContains(Book book);
    BookStore getBookStoreByCityAndAddress(String city, String address);
}
