Feature: Driver on-boarding

  @deleteNewDriver
  Scenario: Create new driver and driver on-boarding
    Given ops manager creates a new driver
    And new driver should be able to sign in driver app
    When "new" driver updates name = "Mai Thu Hai"
    And driver adds new truck that has truck_type_id = 5, truck_model_id = 42
    And "new" driver updates identity
    And "new" driver updates license
    And driver adds preferred route type = "return_trip", pick up area id = 3 and preferred dropoff area id = 5
    And ops manager gets "new" driver document
    And ops manager updates "new" driver identity info
    And ops manager accepts "new" driver identity
    And ops manager updates "new" driver license info
    And ops manager accepts "new" driver license
    And ops manager accepts "new" driver certificate registry
    Then result name from get "new" driver info api must equal "Mai Thu Hai"
    Then result truck from get certificate registries api must contain truck_type_id = 5, truck_model_id = 42
    Then verify driver adds preferred successful from get preferences info api
    Then ops manager verifies that "new" driver is approved from result of get driver info api