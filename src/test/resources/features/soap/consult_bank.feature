@soap
Feature: Consult bank
  I want to consult the bank to know their information

  Scenario: Consult bank
    When he consulted the bank with code 59491200
    Then you should see the bank's information