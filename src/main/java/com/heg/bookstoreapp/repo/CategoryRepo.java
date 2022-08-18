package com.heg.bookstoreapp.repo;

import com.heg.bookstoreapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    void deleteById(Long id);

    boolean existsCategoryByName(String name);

    Category getCategoryByName(String name);
}
