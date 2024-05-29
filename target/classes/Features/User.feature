Feature: Get all List of users
Scenario: Get all user
Given user is on regres URL
When user hits the Users API
Then all the users is displayed