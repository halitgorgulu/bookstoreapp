package com.heg.bookstoreapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private URL imageLink;

    private Float price;

    @JsonIgnoreProperties({"bookStoreBooks"})
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "bookStoreBooks")
    private Set<BookStore> bookStores = new HashSet<>();

    @ManyToOne
    @JoinColumn
    private Category category;

    public Book() {
    }

    public Book(Long id, String name, URL imageLink, Float price, Set<BookStore> bookStores, Category category) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.price = price;
        this.bookStores = bookStores;
        this.category = category;
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

    public URL getImageLink() {
        return imageLink;
    }

    public void setImageLink(URL imageLink) {
        this.imageLink = imageLink;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<BookStore> getBookStores() {
        return bookStores;
    }

    public void setBookStores(Set<BookStore> bookStores) {
        this.bookStores = bookStores;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPriceWithCityInfo(String city) {
        switch (city) {
            case "İstanbul":
                this.price += 10;
                break;
            case "Ankara":
                this.price += 20;
                break;
            case "İzmir":
                this.price += 30;
                break;
            default:
        }
    }
}
