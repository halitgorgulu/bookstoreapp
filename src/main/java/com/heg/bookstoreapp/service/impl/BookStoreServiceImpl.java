package com.heg.bookstoreapp.service.impl;

import com.heg.bookstoreapp.dto.BookStoreDto;
import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.BookStore;
import com.heg.bookstoreapp.repo.BookRepo;
import com.heg.bookstoreapp.repo.BookStoreRepo;
import com.heg.bookstoreapp.service.BookStoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private final BookStoreRepo bookStoreRepo;

    private final BookRepo bookRepo;

    private final ModelMapper modelMapper;

    public BookStoreServiceImpl(BookStoreRepo bookStoreRepo, BookRepo bookRepo, ModelMapper modelMapper) {
        this.bookStoreRepo = bookStoreRepo;
        this.bookRepo = bookRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public BookStoreDto insert(BookStoreDto bookStoreDto) {
        BookStore bookStoreInstance = modelMapper.map(bookStoreDto, BookStore.class);

        List<BookStore> bookStoreList = bookStoreRepo.findAll();
        for (BookStore bookStoreIt : bookStoreList) {
            if (bookStoreInstance.getName().equals(bookStoreIt.getName())) {
                throw new RuntimeException("This bookstore is already inserted.");
            }
        }
        return modelMapper.map(bookStoreRepo.save(bookStoreInstance), BookStoreDto.class);
    }

    @Override
    public List<BookStoreDto> findAll() {
        List<BookStore> bookStores = bookStoreRepo.findAll();
        return bookStores.stream().map(bookStore -> modelMapper.map(bookStore, BookStoreDto.class)).collect(Collectors.toList());
    }

    @Override
    public BookStoreDto findById(Long id) {
        Optional<BookStore> bookStore = bookStoreRepo.findById(id);
        if (bookStore.isPresent()) {
            return modelMapper.map(bookStore.get(), BookStoreDto.class);
        }
        throw new RuntimeException("Not found bookstore with id: " + id);
    }

    @Override
    public BookStoreDto updateById(Long id, BookStoreDto bookStoreDto) {
        Optional<BookStore> bookStore = bookStoreRepo.findById(id);

        if (bookStore.isPresent()) {
            bookStore.get().setName(bookStoreDto.getName());
            bookStore.get().setCity(bookStoreDto.getCity());
            bookStore.get().setAddress(bookStoreDto.getAddress());
            bookStore.get().setBookStoreImage(bookStoreDto.getBookStoreImage());
            bookStore.get().setBookStoreBooks(bookStoreDto.getBookStoreBooks());
            bookStoreRepo.save(bookStore.get());
            return modelMapper.map(bookStore.get(), BookStoreDto.class);
        }
        throw new RuntimeException("Not found bookstore with id: " + id);
    }

    @Override
    public void deleteById(Long id) {
        Optional<BookStore> bookStore = bookStoreRepo.findById(id);

        if (bookStore.isPresent()) {
            bookStoreRepo.deleteById(id);
        }
        throw new RuntimeException("Not found bookstore with id: " + id);
    }

    @Override
    public BookStoreDto putBookToBookStore(Long bookStoreId, Long bookId) {
        Optional<BookStore> bookStore = bookStoreRepo.findById(bookStoreId);
        Optional<Book> book = bookRepo.findById(bookId);
        if (bookStore.isPresent() && book.isPresent()) {
            bookStore.get().getBookStoreBooks().add(book.get());
            return updateById(bookStoreId, modelMapper.map(bookStore.get(), BookStoreDto.class));
        }
        throw new RuntimeException("Not found bookstore with id: " + bookStoreId);
    }

    @Override
    public BookStoreDto deleteBookFromBookstore(Long bookStoreId, Long bookId) {
        Optional<BookStore> bookStore = bookStoreRepo.findById(bookStoreId);
        Optional<Book> book = bookRepo.findById(bookId);
        if (bookStore.isPresent() && book.isPresent()) {
            bookStore.get().getBookStoreBooks().remove(book.get());
            return updateById(bookStoreId, modelMapper.map(bookStore.get(), BookStoreDto.class));
        }
        throw new RuntimeException("Not found book with id in bookstore: " + bookId);

    }


}
