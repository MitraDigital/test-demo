Feature: Customers Feature

  Background:
    * url url

  Scenario: Get first customer details by id

    Given path 'customer'
    And path '00000178276ea31e-26777ae3a90e0001'
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
