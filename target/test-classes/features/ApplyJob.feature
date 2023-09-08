Feature: Apply job

  Background:
    Given I am on the login page
    When I enter username and password
    Then I should be logged in
  @ui @apply
    Scenario: Apply for a job
        When I click on apply button
        Then I should see the today's date in the date
