package com.communication.graphql.service.DataFetcher;

import com.communication.graphql.Entity.Book;
import com.communication.graphql.service.BookService;
import com.communication.graphql.service.impl.BookServiceImplementation;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AllBookdataFetcher implements DataFetcher<List<Book>> {


    @Autowired
    private BookService bookService;

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return bookService.readAllBook();
    }
}
