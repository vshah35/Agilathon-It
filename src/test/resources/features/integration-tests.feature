Feature: Persist details of policy holder in Database

  Scenario: Receive Details: Valid policy Holder enrolls
    Given A Valid policy Holder enrolls
    When details are received by policy details manger
    Then Success response code is received and details of policy holder are persisted in Database

  Scenario: Receive EQR: Invalid EQR is received
    Given An InValid policy Holder enrolls
    When details are received by policy details manger
    Then Error response code is received and details of policy holder are persisted in Database


