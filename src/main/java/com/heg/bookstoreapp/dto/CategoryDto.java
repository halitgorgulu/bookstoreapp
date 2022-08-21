package com.heg.bookstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heg.bookstoreapp.model.Book;
import lombok.Data;

import java.net.URL;
import java.util.Set;

@Data
public class CategoryDto {

    private Long id;

    private String name;

    private URL categoryImage;

    @JsonIgnoreProperties({"category", "bookStores"})
    private Set<Book> books;

}
