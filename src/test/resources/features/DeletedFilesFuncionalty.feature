@wip
Feature: Deleted Files functionality
  User story: As a user, I should be able to see all deleted files and delete/restore them permanently under the Deleted Files tab.


  Background: For the scenarios in the feature file, user is expected to be logged in and at "Deleted files" tab
    Given User is logged in and on Deleted Files tab


  Scenario: User can see the most recent deleted file in the first line of the deleted file list when deleted files are ordered by newest to oldest
    When deleted files are ordered by newest to oldest
    Then user should see recent deleted file


  Scenario: User can order the all deleted files by newest to oldest or visa versa
    When user orders the deleted files by newest to oldest
    Then the most recent ones will be listed on the top of the list
    When user orders the deleted files by oldest to newest
    Then the oldest deleted ones will be listed on the top of the list


  Scenario: User can order alphabetically all the deleted files based on their names
    When user clicks on alphabetically sort button
    Then deleted files are ordered alphabetically by their names


  Scenario: User can delete any deleted file permanently by using the three dots icon in the file’s line
    When user three dots icon and sees the Delete permanently button
    Then user is able to delete file permanently


  Scenario: User can restore any deleted file and see it again under the All Files tab
    When user clicks on restore button and file disappears from deleted files list
    Then user can see the restored file in all files list
