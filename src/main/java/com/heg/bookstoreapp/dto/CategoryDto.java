package com.heg.bookstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heg.bookstoreapp.model.Book;
import lombok.Data;

import java.util.Set;

@Data
public class CategoryDto {

    private String name;

    @JsonIgnoreProperties({"id", "category", "bookStores"})
    private Set<Book> books;

}
