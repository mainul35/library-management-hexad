package com.mainul35.glue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mainul35.BackendApplication;
import com.mainul35.dtos.response.Book;
import com.mainul35.services.LibraryService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CucumberContextConfiguration
@SpringBootTest(classes = {BackendApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookSteps {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private LibraryService libraryService;

    private ObjectMapper objectMapper;

    private List<Book> expectedBooks;
    private List<Book> actualBooks;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
        expectedBooks = new ArrayList<>();
        actualBooks = new ArrayList<>();
    }

    @Given("^there are no books in the library$")
    public void givenThereAreNoBookInTheLibrary() {
        var list = libraryService.getAllBooks();
        expectedBooks.addAll(list);
    }

    @When("^I view the books in the library$")
    public void iViewTheBooksInTheLibrary() throws JsonProcessingException {
        actualBooks.addAll(Arrays.asList(
                objectMapper.readValue(
                        testRestTemplate.getForEntity("/api/books", String.class).getBody(), Book[].class)
        ));
    }

    @Then("^I see an empty library$")
    public void iSeeAnEmptyLibrary() {
        Assert.assertEquals(expectedBooks.size(), actualBooks.size());
        Assert.assertEquals(0, actualBooks.size());
    }

    @Given("there are books in the library")
    public void thereAreBooksInTheLibrary() throws IOException {
        List<Book> books = objectMapper
                .readValue (
                        resourceLoader.getResource ("classpath:books.json").getInputStream (),
                        new TypeReference<ArrayList<Book>>() {
                        }
                );
        libraryService.addBooks(books);
        this.expectedBooks.addAll(libraryService.getAllBooks());
    }

    @Then("I see the list of books in the library")
    public void iSeeTheListOfBooksInTheLibrary() {
        Assert.assertEquals(expectedBooks.size(), actualBooks.size());
    }
}
