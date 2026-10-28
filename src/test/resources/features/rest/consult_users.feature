@rest
Feature: Consult users
  I want to consult the users to know their information

  Scenario: Consult users
    When consulting the page 2 of users
    Then I should see 6 users on page 2 with the first id 7
