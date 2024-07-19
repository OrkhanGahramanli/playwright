Feature: Login

  @LoginSuccess
  Scenario Outline: Successful login in Swag Labs / "<userName>"
    Given User is in Login Page
    When User fills "<userName>" in "userNameInputField"
    And User fills "secret_sauce" in "passwordInputField"
    And User clicks "loginBtn"
    Then User should navigate to Home Page

    Examples:
      | userName                |
      | standard_user           |
      | visual_user             |
      | performance_glitch_user |

  @LoginFail
    Scenario: Unsuccessful login in Swag Labs
      Given User is in Login Page
      When User fills "locked_out_user" in "userNameInputField"
      And User fills "secret_sauce" in "passwordInputField"
      And User clicks "loginBtn"
      Then User should get "Epic sadface: Sorry, this user has been locked out." message