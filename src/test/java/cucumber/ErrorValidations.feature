Feature: Error validation

  Background:
    Given I landed on Ecommerce Page

  Scenario Outline: Logging in error message verification
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed
    Examples:
      | name               | password |
      | piyaasok@gmail.com | test123  |
