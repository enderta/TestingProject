Feature: Google search results now include playable podcasts
  @ui
  Scenario: User searches for a podcast
    Given I am on the Google homepage
    When I search for "podcast"
    Then I should see a list of podcasts