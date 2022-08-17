package com.heg.bookstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heg.bookstoreapp.model.Book;
import lombok.Data;

import java.util.Set;

@Data
public class BookStoreDto {

    private String name;

    private String city;

    private String address;

    @JsonIgnoreProperties({"bookStores","id"})
    private Set<Book> bookStoreBooks;
}
