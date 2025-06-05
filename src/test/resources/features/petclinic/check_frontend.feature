Feature: Check PetsClinic Frontend

  Scenario: Check search filter on Owners page
    Given I am on Owners page
    When I enter "George" in search filter box
    Then I should see a list with 3 matching results

  Scenario: Check new Owner is registered
    Given I am on Register owner page
    When I fill in data for an owner named "Anton" and Submit
    Then I should see "Anton" in the Owners page