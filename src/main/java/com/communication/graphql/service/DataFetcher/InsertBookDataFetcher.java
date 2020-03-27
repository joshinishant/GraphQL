package com.communication.graphql.service.DataFetcher;

import com.communication.graphql.Entity.Book;
import com.communication.graphql.service.impl.BookServiceImplementation;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class InsertBookDataFetcher  implements DataFetcher<Book> {


    @Autowired
    private BookServiceImplementation bookService;


    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {

        Book book= new Book();
        HashMap<String,Object> bookDataInMap=dataFetchingEnvironment.getArgument("book");

        List<String>  authorsAsList=((List<String>) bookDataInMap.get("authors"));
        String[] authorsAsArray=new String[authorsAsList.size()];
        authorsAsList.toArray(authorsAsArray);

        book.setIsn(bookDataInMap.get("isn").toString());
        book.setTitle(bookDataInMap.get("title").toString());
        book.setAuthors(authorsAsArray);
        book.setPublisher(bookDataInMap.get("publisher")==null?null:bookDataInMap.get("publisher").toString());
        book.setPublishedDate(bookDataInMap.get("publishedDate")==null?null:bookDataInMap.get("publishedDate").toString());

        Book savedBook=bookService.saveBook(book);

        return savedBook;
    }
}
