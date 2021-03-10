Feature: Customers Feature

  Background:
    * url url

  Scenario: Get first customer details by id

    Given path 'customer'
    And path '0000017815f6270e-a673d71b48ca0001'
    When method get
    Then status 200
    And match response == { firstName: '#string', lastName: '#string', address: '#string', email: '#string' }
    Then match response.firstName == "Thilina"
    Then match response.lastName == "Jayasinghe"


  Scenario: Validate the error when get the customer details by invalid id
    Given path 'customer'
    And path "100"
    When method get
    Then status 404
