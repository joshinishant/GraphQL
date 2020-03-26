package com.communication.graphql.service;

import com.communication.graphql.Entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 public interface BookService {

     Book saveBook(Book book);
     Book readBook(String bookId);
     Book deleteBook(String bookId);
     Book updateBook(Book book);
     List<Book> readAllBook();
     List<Book> loadStaticData();

}
