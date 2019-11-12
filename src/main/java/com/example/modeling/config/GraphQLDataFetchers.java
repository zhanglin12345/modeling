package com.example.modeling.config;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {
    
    private static final String AUTHOR_ID = "authorId";
    private static final String PAGE_COUNT = "pageCount";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    
    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    PAGE_COUNT, "223",
                    AUTHOR_ID, "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    PAGE_COUNT, "635",
                    AUTHOR_ID, "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    PAGE_COUNT, "371",
                    AUTHOR_ID, "author-3")
    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    FIRST_NAME, "Joanne",
                    LAST_NAME, "Rowling"),
            ImmutableMap.of("id", "author-2",
                    FIRST_NAME, "Herman",
                    LAST_NAME, "Melville"),
            ImmutableMap.of("id", "author-3",
                    FIRST_NAME, "Anne",
                    LAST_NAME, "Rice")
    );

    DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String,String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get(AUTHOR_ID);
            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }
}
