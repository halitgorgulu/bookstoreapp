package com.heg.bookstoreapp.repo;

import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    List<Book> getBooksById(Long categoryId);
}
