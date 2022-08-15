package com.heg.bookstoreapp.service.impl;

import com.heg.bookstoreapp.dto.BookDto;
import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.BookStore;
import com.heg.bookstoreapp.repo.BookRepo;
import com.heg.bookstoreapp.repo.BookStoreRepo;
import com.heg.bookstoreapp.repo.CategoryRepo;
import com.heg.bookstoreapp.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    private final BookStoreRepo bookStoreRepo;

    private final CategoryRepo categoryRepo;

    private final ModelMapper modelMapper;



    public BookServiceImpl(BookRepo bookRepo, BookStoreRepo bookStoreRepo, CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.bookRepo = bookRepo;
        this.bookStoreRepo = bookStoreRepo;
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public BookDto insert(BookDto bookDto) {
        Book bookInstance = modelMapper.map(bookDto,Book.class);

        List<Book> bookList = bookRepo.findAll();
        for (Book bookIt : bookList) {
            if (bookInstance.getName().equals(bookIt.getName())) {
                throw new RuntimeException("This book is already inserted.");
            }
        }
        return modelMapper.map(bookRepo.save(bookInstance),BookDto.class);
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepo.findAll();
        return books.stream().map(book -> modelMapper.map(book,BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if(book.isPresent()){
            return modelMapper.map(book.get(),BookDto.class);
        }
        throw new RuntimeException("Not found book with id: " + id);
    }

    @Override
    public BookDto updateById(Long id, BookDto book) {
        Optional<Book> resultBook = bookRepo.findById(id);

        if(resultBook.isPresent() && categoryRepo.existsCategoryByName(book.getCategory().getName())){
            resultBook.get().setName(book.getName());
            resultBook.get().setPrice(book.getPrice());
            resultBook.get().setImageLink(book.getImageLink());
            resultBook.get().setCategory(categoryRepo.getCategoryByName(book.getCategory().getName()));
            bookRepo.save(resultBook.get());
            return modelMapper.map(resultBook.get(),BookDto.class);
        }
        throw new RuntimeException("Not found book with id: " + id);
    }

    @Override
    public void delete(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if(book.isPresent()){
            bookRepo.deleteById(id);
        }
        throw new RuntimeException("Not found book with id: " + id);
    }

    @Override
    public List<Book> getBooksByCategoryId(Long id) {
        return bookRepo.getBooksByCategory_Id(id);
    }

    @Override
    public List<BookStore> getBookStoreByContainsBook(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if(book.isPresent()){
            return bookStoreRepo.getBookStoresByBookStoreBooksContains(book);
        }
        throw new RuntimeException("Not found book with id: " + id);
    }

}
