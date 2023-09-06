Feature: Delete a job from the app

  Background:
    Given I am on the login page
    When I enter username and password
    Then I should be logged in
    @ui @delete
    Scenario: Delete a job
        Given I am on the jobs page and get the number of the jobs
        When I click on the delete button first for the first job
        And The number of jobs should be decreased by one
