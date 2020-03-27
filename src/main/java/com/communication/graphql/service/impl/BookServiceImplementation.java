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
        bookList.add(new Book("978-0134685991","Effective Java",null,new String[]{"Joshua Bloch"},null));
        bookList.add(new Book("978-0071808552","Java: The Complete Reference",null,new String[]{"Herbert Schildt"},null));
        bookList.add(new Book("978-0596009205","Head First Java","O'Reilly",new String[]{"Kathy Sierra","Bert Bates"},null));
        bookList.add(new Book("978-0596007126","Head First Design Patterns","O'Reilly",new String[]{"Kathy Sierra","Bert Bates","Elisabeth Robson","Eric Freeman"},null));
        bookList.add(new Book("978-0596008673","Head First Object-Oriented Analysis and Design","O'Reilly",new String[]{"Brett McLaughlin","David West","Gary Pollice"},null));
        bookList.add(new Book("978-1449340377","Python Cookbook: Recipes for Mastering Python 3","O'Reilly",new String[]{"Brian K. Jones","David Beazley"},null));
        bookRepo.saveAll(bookList);
        return bookList;
    }

}
