@mobile
Feature: Mobile

  @1st
  Scenario: 01 - I open the app
    Given I wait for 15 second
    Then I assert false something

  @1st
  Scenario: 02 - I open the app and wait
    Given I wait for 10 second
    Then I assert true something

  @2nd
  Scenario: 03 - I open the app scenario 4
    Given I wait for 17 second
    Then I assert false something

  @2nd
  Scenario: 04 - I open the app again
    Given I wait for 13 second
    Then I assert false something