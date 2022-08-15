package com.heg.bookstoreapp.repo;

import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookStoreRepo extends JpaRepository<BookStore,Long> {
    List<BookStore> getBookStoresByBookStoreBooksContains(Optional<Book> book);

    void deleteById(Long id);
}
