package com.heg.bookstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heg.bookstoreapp.model.Book;
import lombok.Data;

import java.net.URL;
import java.util.Set;

@Data
public class BookStoreDto {

    private Long id;

    private String name;

    private String city;

    private String address;

    private URL bookStoreImage;

    @JsonIgnoreProperties({"bookStores"})
    private Set<Book> bookStoreBooks;
}
