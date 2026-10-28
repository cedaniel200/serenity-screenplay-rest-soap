@soap
Feature: Say hello
  I want to be greeted by the service

  Scenario: Greet a person
    When he says hello to Cesar
    Then he should see the greeting Hello Cesar!
