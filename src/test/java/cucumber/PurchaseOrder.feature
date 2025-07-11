

Feature: Purchase the order from Ecommerce Website

  Background:
    Given I landed on Ecommerce Page
  @Regression @test
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> to cart
    Then I checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed for order confirmation
    Examples:
      | name               | password | productName |
      | piyaasok@gmail.com | test123! | zara coat 3 |
