Feature: Persist details of policy holder in Database

  Scenario: Receive Details: Valid policy Holder enrolls
    Given A Valid policy Holder enrolls 6
    When details are received by policy details manager
    Then Success response code is received and details of policy holder are persisted in Database

  Scenario: Delete Details: existing policy holder
    Given A existing policy Holder in db
    When details are deleted by policy details manager
    Then details are deleted from db


