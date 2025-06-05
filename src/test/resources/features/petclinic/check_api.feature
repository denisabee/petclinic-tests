Feature: Check Pets Clinic APIs

  Scenario: Check List of Vets contains Vet
    Given the status of the API is 200
    When I make a request to the vets end-point
    Then the API response should contain "Leary"

  Scenario: Check new pet can be added to existing owner
    Given that owner with id "1" is present
    When I add a new pet with name "Aladin" for owner with id "1"
    Then pet "Aladin" will show in the list of pets for owner with id "1"



