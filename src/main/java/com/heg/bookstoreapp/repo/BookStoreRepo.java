package com.heg.bookstoreapp.repo;

import com.heg.bookstoreapp.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStoreRepo extends JpaRepository<BookStore,Long> {
    //List<BookStore> getAllBookStoresByBookId(Long bookId);
}
