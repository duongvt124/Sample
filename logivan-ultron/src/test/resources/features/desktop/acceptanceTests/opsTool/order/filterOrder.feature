Feature: Filter order acceptance test on ops tool

  Background: Broker sign in
    Given ops manager should be able to go to ops tool login page by url
    And ops manager should be able to login ops tool with email = "dev@logivan.com" and password = "LogivanRules@2018"
    And ops manager should be able to allow notification on ops tool
    And ops manager should be able to change language to English on ops tool

  @opsTool
  Scenario: ops manager filter cancelled order success
    When ops should be able to filter orders by order status = "cancelled"
    Then ops should be able to verify order status = "cancelled" on order list page

  @opsTool
  Scenario: ops manager filter completed order success
    When ops should be able to filter orders by order status = "completed"
    Then ops should be able to verify order status = "completed" on order list page

  @quitSelenium
  Scenario: ops manager filter order by drop off location
    When ops should be able to filter orders by drop off location = "Hồ Chí Minh"
    Then ops should be able to verify drop off location of order = "Hồ Chí Minh"
