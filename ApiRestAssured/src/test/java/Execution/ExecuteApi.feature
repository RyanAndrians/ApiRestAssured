Feature: Execute API

  Scenario Outline: As user i can validate response from server
    Given i hit api with "<Data>" number page
    Then i verify "<Data>" of data json returned
    Examples:
    |Data|
    |1|
    |5|
    |20|

  Scenario: As user i can makesure schema is correct
    Given i hit api "https://api.punkapi.com/v2/beers"
    Then i validate schema is correct