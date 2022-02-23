package com.mainul35.glue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mainul35.BackendApplication;
import com.mainul35.dtos.request.BorrowingBooks;
import com.mainul35.dtos.response.Book;
import com.mainul35.dtos.response.LibraryStatus;
import com.mainul35.services.LibraryService;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = {BackendApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class LibrarySteps {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    private ObjectMapper objectMapper;

    @Autowired
    private LibraryService libraryService;

    private List<Book> expectedBooks;
    private List<Book> actualBooks;
    private LibraryStatus libraryStatus;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
        expectedBooks = new ArrayList<>();
        actualBooks = new ArrayList<>();
        this.libraryStatus = new LibraryStatus();
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
        Assertions.assertEquals(expectedBooks.size(), actualBooks.size());
        Assertions.assertEquals(0, actualBooks.size());
    }

    @Given("there are books in the library")
    public void thereAreBooksInTheLibrary() throws IOException {
        List<Book> books = objectMapper
                .readValue (
                        resourceLoader.getResource ("classpath:books.json").getInputStream (),
                        new TypeReference<ArrayList<Book>>() {
                        }
                );
        books.forEach(book -> {
            libraryService.addBook(book);
        });
        this.expectedBooks.addAll(libraryService.getAllBooks());
    }

    @Then("I see the list of books in the library")
    public void iSeeTheListOfBooksInTheLibrary() {
        Assertions.assertEquals(expectedBooks.size(), actualBooks.size());
    }

    @When("I choose a book to add to my borrowed list")
    public void iChooseABookToAddToMyBorrowedList() {
        BorrowingBooks borrowingBooks = new BorrowingBooks();
        List<String> bookIds = new ArrayList<>();
        bookIds.add("f76b7137-2a23-4654-9395-9916c7c1d460");
        bookIds.add("5876caca-290d-406a-9869-646f58c0d668");
        borrowingBooks.setBookIds(bookIds);
        libraryStatus = testRestTemplate.postForObject("/api/books/borrow", borrowingBooks, LibraryStatus.class);
    }

    @Then("the book is added to my borrowed list")
    public void theBookIsAddedToMyBorrowedList() {
        Assertions.assertFalse(libraryStatus.getBorrowedBooks().isEmpty());
    }

    @And("the book is removed from the library")
    public void theBookIsRemovedFromTheLibrary() {
        Assertions.assertEquals(2, libraryStatus.getBorrowedBooks().size());
        Assertions.assertEquals(
                (libraryService.getAllBooks().size() - libraryStatus.getBorrowedBooks().size()),
                libraryStatus.getRemainingBooks().size()
        );
    }

    @Given("there are more than one copy of a book in the library")
    public void thereAreMoreThanOneCopyOfABookInTheLibrary() {
        var booksInLibrary = libraryService.getAllBooks();
        booksInLibrary.stream()
                .filter(book -> doesListContain(book, booksInLibrary))
                .findAny().ifPresent(Assertions::assertNotNull);
    }

    private boolean doesListContain(Book book, List<Book> list) {
        for (Book b : list) if (b.getIsbn().equals(book.getIsbn())) return true;
        return false;
    }

    @Then("one copy of the book is added to my borrowed list")
    public void oneCopyOfTheBookIsAddedToMyBorrowedList() {
        Assertions.assertTrue(this.libraryStatus.getBorrowedBooks().stream()
                .anyMatch(book -> otherCopyExistsInLibrary(book, libraryStatus.getRemainingBooks())));
    }

    private boolean otherCopyExistsInLibrary(Book book, List<Book> remainingBooks) {
        return remainingBooks.stream().anyMatch(book1 -> book.getIsbn().equals(book1.getIsbn()));
    }

    @And("the library has at least one copy of the book left")
    public void theLibraryHasAtLeastOneCopyOfTheBookLeft() {
        Assertions.assertTrue(this.libraryStatus.getRemainingBooks().stream()
                .anyMatch(book -> otherCopyExistsInLibrary(book, libraryStatus.getBorrowedBooks())));
    }

    @Given("there is only one copy of a book in the library")
    public void thereIsOnlyOneCopyOfABookInTheLibrary() {
        var onlyBook = libraryStatus.getRemainingBooks()
                .stream()
                .filter(book -> book.getBookName().equals("Thread Programming"))
                .findAny().get();
        Assertions.assertNotNull(onlyBook);
    }

    @When("I choose the book to add to my borrowed list")
    public void iChooseTheBookToAddToMyBorrowedList() {
        BorrowingBooks borrowingBooks = new BorrowingBooks();
        List<String> bookIds = new ArrayList<>();
        bookIds.add("adea8964-053c-4027-b2a0-77de7f6ea799");
        borrowingBooks.setBookIds(bookIds);
        libraryStatus = testRestTemplate.postForObject("/api/books/borrow", borrowingBooks, LibraryStatus.class);
    }


    @Then("only copy of the book is added to my borrowed list")
    public void onlyCopyOfTheBookIsAddedToMyBorrowedList() {
        var borrowedBook = libraryStatus.getBorrowedBooks().stream()
                .filter(book -> book.getBookName().equals("Thread Programming"))
                .findAny().get();
        Assertions.assertNotNull(borrowedBook);
    }


    @And("the only book is removed from the library")
    public void theOnlyBookIsRemovedFromTheLibrary() {
        Assertions.assertTrue(
                libraryStatus.getRemainingBooks().stream()
                        .noneMatch(book -> book.getBookName().equals("Thread Programming"))
        );
    }
}
