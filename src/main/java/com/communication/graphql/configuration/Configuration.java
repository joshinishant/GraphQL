package com.communication.graphql.configuration;

import com.communication.graphql.service.DataFetcher.*;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    private BookDataFetcher bookDataFetcher;

    @Autowired
    private AllBookdataFetcher bookDataFetchersAll;

    @Autowired
    private InsertBookDataFetcher insertBookDataFetcher;

    @Autowired
    private UpdateTitleDataFetcher updateTitleDataFetcher;

    @Autowired
    private DeleteBookDataFetcher deleteBookDataFetcher;

    @Value("classpath:books.graphql")
    private Resource resource;

    @Bean("graphQL")
    public GraphQL loadGraphqlSchema() throws IOException {
        File schemaFile=resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry= new SchemaParser().parse(schemaFile);

        RuntimeWiring runtimeWiring= buildRuntimeWiring();
        GraphQLSchema graphQLSchema= new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);

        GraphQL graphQL= GraphQL.newGraphQL(graphQLSchema).build();
        return graphQL;
    }

    private RuntimeWiring buildRuntimeWiring() {

        return RuntimeWiring.newRuntimeWiring().type("Query",typeWiring ->
                typeWiring.dataFetcher("allBooks", bookDataFetchersAll).dataFetcher("book", bookDataFetcher))
                .type("Mutation", mutation -> mutation.dataFetcher("updateTitle",updateTitleDataFetcher)
                .dataFetcher("insertBook",insertBookDataFetcher)
                .dataFetcher("deleteBook",deleteBookDataFetcher)).build();
    }
}