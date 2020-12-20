Feature: Amazon Checkout
  verify if a new Amazon user is to search and add the cheapest product to the basket

  Background:
    Given The user is on Amazon homepage
    And The user searches for "Snickers"
    And The user sorts the search results by "Price: Low to High"
    And The user adds the cheapest product to the basket
    And The user searches for "Skittles"
    And The user sorts the search results by "Price: Low to High"
    And The user adds the cheapest product to the basket
    And The user navigates to the basket page   
    
  Scenario: Verify if the basket calculates the result correctly
      Then The basket should have the correct total amount

  Scenario: Check if on Checkout, without an account, the user gets redirected to the registration page.
      And The user proceeds to checkout
      Then The user should be redirected to the registration page
  
  