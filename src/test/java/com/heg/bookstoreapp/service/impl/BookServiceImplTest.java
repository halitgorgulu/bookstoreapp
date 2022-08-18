package com.heg.bookstoreapp.service.impl;

import com.heg.bookstoreapp.dto.BookDto;
import com.heg.bookstoreapp.model.Book;
import com.heg.bookstoreapp.model.Category;
import com.heg.bookstoreapp.repo.BookRepo;
import com.heg.bookstoreapp.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceImplTest {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepo bookRepo;


    @Test
    public void whenGetAllRequest_itShouldReturnBookList() throws MalformedURLException {
        URL url = new URL("https://m.media-amazon.com/images/I/41R0QL3uelL._AC_SY1000_.jpg");

        List<Book> bookList = Stream
                .of(new Book(24L, "Savaş-barışş", url, 19.1F, null, null),
                        new Book(25L, "Barış-Savaş", url, 17.1F, null, null)).collect(Collectors.toList());
        when(bookRepo.findAll()).thenReturn(bookList);
        List<BookDto> bookDtos = bookList.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
        Assert.assertEquals(bookDtos, bookService.findAll());
    }

    /*@Test
    public void whenCreateBookWithValidRequest_itShouldReturnValidBookDto() throws MalformedURLException {
        URL url = new URL("https://m.media-amazon.com/images/I/41R0QL3uelL._AC_SY1000_.jpg");
        Book book = new Book(24L,"Savaş-barışş",url,19.1F,null,null);
        when(bookRepo.save(book)).thenReturn(book);
        BookDto bookDto =  modelMapper.map(book,BookDto.class);
        Assert.assertEquals(bookDto,bookService.insert(bookDto));
    }*/

    @Test
    public void whenGetBooksByCategoryId_itShouldReturnBookList() throws MalformedURLException {
        URL url = new URL("https://m.media-amazon.com/images/I/41R0QL3uelL._AC_SY1000_.jpg");
        Category category1 = new Category(24L, "Masalımsı", null);
        Category category2 = new Category(25L, "kork", null);
        List<Book> bookList = Stream
                .of(new Book(24L, "Savaş-barışş", url, 19.1F, null, category1),
                        new Book(25L, "Barış-Savaş", url, 17.1F, null, category1),
                        new Book(26L, "Bişey", url, 11.1F, null, category2)).collect(Collectors.toList());
        when(bookRepo.getBooksByCategory_Id(24L)).thenReturn(bookList);
        Assert.assertEquals(bookList, bookService.getBooksByCategoryId(24L));
    }
}