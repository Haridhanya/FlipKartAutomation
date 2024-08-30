Feature: Flip Automation End to End

Scenario: Open FlipKart and search for a product
Given User enter the Url
When User Enter THe Product and search 
Then Get the list of products
And Output the result into Excel File
And Output the result into Word File

