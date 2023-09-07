Feature: Editing first job

  Background: Background:
    Given I am on the login page
    When I enter username and password
    Then I should be logged in
  @ui @edit
  Scenario Outline: Adding new job to ui
    When I click on the edit button
    And I change job details "<jobTitle>", "<jobCompany>", "<jobDescription>", "<jobLocation>", "<jobRequirements>"
    And I click on the submit button
    Then I should see the job edit to the list
    Examples:
      | jobTitle | jobCompany | jobDescription | jobLocation | jobRequirements |
      | Test Job | Test Company | Test Description | Test Location | Test Requirements |