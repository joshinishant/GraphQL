package com.communication.graphql.service.DataFetcher;

import com.communication.graphql.Entity.Book;
import com.communication.graphql.service.impl.BookServiceImplementation;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateTitleDataFetcher  implements DataFetcher<Book> {

    @Autowired
    private BookServiceImplementation bookService;


    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {

        Book book=bookService.readBook(dataFetchingEnvironment.getArgument("isn").toString());
        book.setTitle(dataFetchingEnvironment.getArgument("title").toString());
        Book updatedBook=bookService.updateBook(book);


        return updatedBook;
    }
}
