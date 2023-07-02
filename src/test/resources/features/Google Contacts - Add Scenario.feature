@GoogleContact
Feature: Add Contact Using Google Contacts App

  Scenario: Launch Google Contacts App & Save a new contact with random data

    Given User is on Google Contacts app
    When User navigates to Add Contact screen
    And For the following fields, user add contact with mentioned values

      | FieldName            | FieldType | Value / Action |
      | First name           | Input     | Random         |
      | Last name            | Input     | Random         |
      | Company              | Input     | Random         |
      | Phone                | Input     | Random         |
      | Contact Details Save | Button    | Click          |

    Then The contact should be saved successfully