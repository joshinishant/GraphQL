package com.communication.graphql.controller;

import com.communication.graphql.service.BookService;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/books")
public class BookController {

    @Autowired
    private GraphQL graphQL;


    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Object> getAllBooks(@RequestBody String query){
        ExecutionResult executionResult=graphQL.execute(query);
        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }

    @GetMapping(value="/loadData")
    private ResponseEntity<Object> loadData(){
        bookService.loadStaticData();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
