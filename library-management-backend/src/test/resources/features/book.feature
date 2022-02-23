Feature:
  Scenario: As a User I want to see the books present in the library So that I can chose which book to borrow ,Given, there are no books in the library
    Given there are no books in the library
    When I view the books in the library
    Then I see an empty library

    Given there are books in the library
    When I view the books in the library
    Then I see the list of books in the library
