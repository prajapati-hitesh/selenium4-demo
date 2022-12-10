@OrangeHRMTest
Feature: Orange HRM : Update & Validate Logged In User Details

  Scenario: Login to Orange CRM Application

    Given I am on Login Page of Orange HRM application
    When I enter login credentials using following

      | FieldName | FieldType | Value / Action |
      | Username  | Input     | Admin          |
      | Password  | Input     | admin123       |
      | Login     | Button    | Click          |

    Then I should be able to login to system

  Scenario: Update & Validate Logged In User Details

    Given User is on Dashboard page
    When I navigate to My Info
    And For the following field, I update a user details with mentioned values

      | FieldName               | FieldType | Value / Action |
      | First Name              | Input     | Random         |
      | Middle Name             | Input     | Random         |
      | Last Name               | Input     | Random         |
      | Nickname                | Input     | Random         |
      | Driver's License Number | Input     | Random         |
      | Employee Id             | Input     | Random         |
      | Employee Details Save   | Button    | Click          |

    Then User details should be updated successfully