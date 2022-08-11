package com.heg.bookstoreapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BookStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private String address;

    @ManyToMany
    @JoinTable(
            name="book_bookstore",
            joinColumns = @JoinColumn(name = "book_store_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> bookStoreBooks = new HashSet<>();

    public BookStore() {
    }

    public BookStore(Long id, String name, String city, String address, Set<Book> bookStoreBooks) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.bookStoreBooks = bookStoreBooks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Book> getBookStoreBooks() {
        return bookStoreBooks;
    }

    public void setBookStoreBooks(Set<Book> bookStoreBooks) {
        this.bookStoreBooks = bookStoreBooks;
    }
}
