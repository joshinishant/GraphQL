package com.communication.graphql.service.impl;

import com.communication.graphql.Entity.Book;
import com.communication.graphql.repository.BookRepo;
import com.communication.graphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class BookServiceImplementation implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book readBook(String bookId) {
        Optional<Book> fetchedOptionalBook= bookRepo.findById(bookId);
        return fetchedOptionalBook.isPresent()?fetchedOptionalBook.get():null;
    }

    @Override
    public List<Book> readAllBook() {
        List<Book> bookList=new  ArrayList<>();

        Iterator<Book> bookIterator=bookRepo.findAll().iterator();

        while(bookIterator.hasNext()){
            bookList.add(bookIterator.next());
        }

        return bookList;
    }

    @Override
    public Book deleteBook(String bookId) {

        Book fetchedBook=readBook(bookId);
        if(fetchedBook!=null){
            bookRepo.delete(fetchedBook);
            return fetchedBook;
        }

        return null;
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }



    public List<Book> loadStaticData(){
        List<Book> bookList= new ArrayList<>();
        bookList.add(new Book("1","Book1","P1",new String[]{"A1","A2"},"18-07-2018"));
        bookList.add(new Book("2","Book2","P2",new String[]{"A1","A3"},"18-07-2018"));
        bookList.add(new Book("3","Book3","P3",new String[]{"A4"},"18-07-2018"));
        bookList.add(new Book("4","Book4","P1",new String[]{"A5"},"18-07-2018"));
        bookList.add(new Book("5","Book5","P1",new String[]{"A2"},"18-07-2018"));
        bookList.add(new Book("6","Book6","P2",new String[]{"A2","A5"},"18-07-2018"));
        bookRepo.saveAll(bookList);
        return bookList;
    }

}
