@users
Feature: Adding the users to the List.
@add
Scenario Outline: Add user
Given User is on  regres URL
When User enters the "<name>" and "<job>"
And users hit the users API
Then users are added to list

Examples:
|name|job|
|ajay|software|
|ramu|analyst|
@update
Scenario: Update the user
Given User is on regres URL
When User enters name & job
|ajay|software|
|ramu|analyst|
And user hits the API
Then User data is updated