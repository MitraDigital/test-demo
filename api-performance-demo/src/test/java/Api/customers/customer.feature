Feature: Customers Feature

  Background:
    * url url

  Scenario: Get first customer details by id

    Given path 'customer'
    And path '0000017833c87284-1a7ea6f868100001'
    When method get
    Then status 200
    And match response == { firstName: '#string', lastName: '#string', address: '#string', accounts: '#object', email: '#string', status: '#string' }
    Then match response.firstName == "Thilina"
    Then match response.lastName == "Jayasinghe"


  Scenario: Validate the error when get the customer details by invalid id
    Given path 'customer'
    And path "100"
    When method get
    Then status 404
