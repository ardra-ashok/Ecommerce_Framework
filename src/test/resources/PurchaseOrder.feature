

Feature: Purchase the order from Ecommerce Websitw

  Background: I landed on Ecommerce Page
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> to cart
    Then I checkout <productName> and submit the order
    Then "Thankyou for the order."
    Examples:
      | name               |  | password | productName |
      | piyaasok@gmail.com |  | test123! | zara coat 3 |
#      | ardraasok@yahoo.in |  | Test123! |             |