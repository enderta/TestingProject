Feature: Login to UI

  @ui
    Scenario: Login to UI
        Given I am on the login page
        When I enter username and password
        Then I should be logged in
