package com.heg.bookstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heg.bookstoreapp.model.BookStore;
import com.heg.bookstoreapp.model.Category;
import lombok.Data;

import java.net.URL;
import java.util.Set;

@Data
public class BookDto {

    private String name;

    private URL imageLink;

    private Float price;

    @JsonIgnoreProperties({"id"})
    private Category category;

    @JsonIgnoreProperties({"bookStoreBooks", "id"})
    private Set<BookStore> bookStores;

}
