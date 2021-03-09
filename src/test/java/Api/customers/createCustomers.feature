Feature: Create Customer Feature
  for help, see: https://github.com/intuit/karate/wiki/IDE-Support

  Background:
    * url url
    * def now = function(){ return java.lang.System.currentTimeMillis() }
    * def email = 'test' + now() + 'gmail.com'


  Scenario: Create a user using valid details
    * def user =
      """
        {
        "firstName": "Thilina",
        "lastName": "Jayasinghe",
        "address": "Piliyandala",
        "email": "#(email)"
        }
      """

    Given path 'customer'
    And request user
    When method post
    Then status 201
    * def customer_id = response.id
    Then print 'id is ', customer_id


  Scenario: Validate the error when a user try to register using already added details
    * def user =
      """
        {
        "firstName": "Thilina",
        "lastName": "Jayasinghe",
        "address": "Piliyandala",
        "email": "tjay@gmail.com"
        }
      """

    Given path 'customer'
    And request user
    When method post
    Then status 500
    Then match response.violations == "customer duplicated :tjay@gmail.com"


  Scenario: Validate the first name validation if the first name is empty
    * def user =
      """
        {
        "firstName": "",
        "lastName": "Jayasinghe",
        "address": "Piliyandala",
        "email": "tjay@gmail.com"
        }
      """

    Given path 'customer'
    And request user
    When method post
    Then status 400
    Then match response.violations == "firstName size must be between 1 and 30"

  Scenario: Validate the email validation if the email is empty
    * def user =
      """
        {
        "firstName": "Thilina",
        "lastName": "Jayasinghe",
        "address": "Piliyandala",
        "email": ""
        }
      """

    Given path 'customer'
    And request user
    When method post
    Then status 400
    Then match response.violations == "email size must be between 1 and 30"