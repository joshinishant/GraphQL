package com.communication.graphql.service.DataFetcher;

import com.communication.graphql.Entity.Book;
import com.communication.graphql.service.BookService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    @Autowired
    private BookService bookService;

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
        return bookService.readBook(dataFetchingEnvironment.getArguments().get("id").toString()     );
    }
}
