package com.communication.graphql.service.DataFetcher;

import com.communication.graphql.Entity.Book;
import com.communication.graphql.service.impl.BookServiceImplementation;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteBookDataFetcher implements DataFetcher<Book> {
    @Autowired
    private BookServiceImplementation bookService;


    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {

        Book deletedBook=bookService.deleteBook(dataFetchingEnvironment.getArgument("isn").toString());

        return deletedBook;
    }
}
