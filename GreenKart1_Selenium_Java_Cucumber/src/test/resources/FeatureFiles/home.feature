Feature: Home Page Functionality
  As a user
  I want to access the home page
  So that I can view available products

 Background:
    Given user is on GreenKart home page

  @smoke
  Scenario Outline: Item count updates when adding different products
    When user searches and adds "<productName>" to cart
    Then cart item count should be 1

    Examples:
      | productName |
      | Orange      |
      | Apple       |

  Scenario: Item count increases when adding multiple products
    When user adds "Orange" and "Apple" to cart
    Then cart item count should be 2

  @smoke
  Scenario: Item count does not increase when adding same product twice
    When user adds "Orange" twice to cart
    Then cart item count should be 1

  @smoke
  Scenario: Cart price updates correctly after adding item
    When user adds "Apple" to cart and opens cart
    Then cart price should be 72

  Scenario: Default quantity should not decrease below one
    When user tries to decrease quantity for "Orange"
    Then item quantity should remain 1

  @smoke
  Scenario: User is able to search product
    When user searches for "Orange"
    Then product name should be "Orange"