Feature: Jobs API CRUD Operations

  @api
  Scenario Outline: Full CRUD operations for jobs
    Given I am an authenticated user with username "<username>" and password "<password>"
    Given I have an access token
    When I create a job with details "<job_1>"
    Then I should receive a confirmation with job details "<job_1>"
    When I update the job with new details "<job_2>"
    Then I should receive a confirmation with updated job details "<job_2>"
    When I delete the job with details "<job_2>"
    Then the job "<job_2>" should no longer exist

    Examples:
      | username | password | job_1 | job_2 |
      | "user1" | "pass1" | {"title": "test", "company": "test","location": "test","description": "test","requirements": "test" } | {"title": "test2", "company": "test2","location": "test2","description": "test2","requirements": "test2" } |