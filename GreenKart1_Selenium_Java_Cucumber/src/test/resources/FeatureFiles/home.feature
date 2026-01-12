Feature: Cart functionality on Home Page

  Background:
    Given user is on GreenKart home page
   

  @smoke
  Scenario Outline: Verify item number is updating while adding different products to cart
    When user searches and adds "<productName>" to cart
    Then cart item number should be 1

    Examples:
      | productName |
      | Orange      |
      | Apple       |

  Scenario: Verify item number is increasing while adding multiple items
    When user searches and adds "Orange" to cart
    And user searches and adds "Apple" to cart
    Then cart item number should be 2

  @smoke
  Scenario: Verify item number is not increasing while adding same item twice
    When user searches "Orange"
    And user adds "Orange" to cart
    And user adds "Orange" to cart again
    Then cart item number should be 1

  @smoke
  Scenario: Verify user is able to increase cart price
    When user searches and adds "Apple" to cart
    And user opens cart bag
    Then cart price should be 72

  Scenario: Verify user is not able to decrease default quantity in quantity box
    When user searches "Orange"
    And user clicks minus button in quantity box
    Then default quantity should remain 1

  @smoke
  Scenario: Verify user is able to search item
    When user searches "Orange"
    Then product name should be "Orange"
