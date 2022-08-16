package com.heg.bookstoreapp.repo;

import com.heg.bookstoreapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
    List<Book> getBooksByCategory_Id(Long categoryId);

    void deleteById(Long id);
}
