Feature: API testing

  @api
  Scenario Outline: Full circle CRUD test
    Given I register a new user with following details:
      | username   | email             | password |
      | <username> | <email>           | <password> |
    Then I should get a successful registration message

    When I login as this user
    Then I should get a successful login message

    When I list all registered users
    Then I should get a user count message

    When I retrieve the details of this user by id
    Then I should get a message with the retrieved user details

    Given I have the following updated user details:
      | username | password | email         |
      | <new_username> | <new_password> | <new_email> |
    When I update this user using updated details
    Then I should get a successful update message

    When I delete this user
    Then I should get a successful user deletion message

    Examples:
      | username      | email                | password | new_username | new_password | new_email        |
      | testusers1w7 | test1wss3317yy2@gmail1.com | 123456   | et2y3       | 123456   | et337@gmail.com |
     