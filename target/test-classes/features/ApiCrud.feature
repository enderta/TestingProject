Feature: API testing

  @api
  Scenario: Full circle CRUD test
    When I register a new user with following details:
      | username   | email             | password |
      | testusers1w36sy3 | test1wss331yy@gmail1.com | 123456   |
    Then I get the register message User "successfully" registered successfully
    When I login to the API as new user
    Then I get the login message "User logged in successfully"
    When I get the list of users
    Then I get the message Retrieved "number" users
    When I get the user by id
    Then I get the retrieved message equal Retrieved user with id "id"
    When I update the user with the following details:
      | username | password | email         |
      | et2y3      | 123456   | et337@gmail.com |
    Then I get the update message User "successfully" updated successfully
    When I delete the user
    Then I get delete the message User "successfully" deleted successfully