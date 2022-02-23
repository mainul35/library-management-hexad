Feature:
  Scenario: As a User I want to see the books present in the library So that I can chose which book to borrow ,Given, there are no books in the library
    # case 1
    Given there are no books in the library
    When I view the books in the library
    Then I see an empty library

    Given there are books in the library
    When I view the books in the library
    Then I see the list of books in the library

    # case 2
    Given there are books in the library
    When I choose a book to add to my borrowed list
    Then the book is added to my borrowed list
    And the book is removed from the library

    # case 3
    Given there are more than one copy of a book in the library
    When I choose a book to add to my borrowed list
    Then one copy of the book is added to my borrowed list
    And the library has at least one copy of the book left

    Given there is only one copy of a book in the library
    When I choose the book to add to my borrowed list
    Then only copy of the book is added to my borrowed list
    And the only book is removed from the library