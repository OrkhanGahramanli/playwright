Feature: HomePage

  Background:
    Given User is in Login Page
    When User fills "standard_user" in "userNameInputField"
    And User fills "secret_sauce" in "passwordInputField"
    And User clicks "loginBtn"

    @SortProducts
      Scenario Outline: Sort products on Home Page by "<sortType>"
      When User selects "<sortType>" from "sortFilter"
      Then Products should be ordered by "<sortType>"


      Examples:
        | sortType |
        | az       |
        | za       |
        | lohi     |
        | hilo     |

      @addProductToBasket
      Scenario: Add product to basket
        When User clicks multiple "addToCardBtns"
        Then Products count in basket should be visible on "basketIcon"
        And User clicks "basketBtn"
        Then Products should be displayed in basket

        @RemoveProductsFromBasket
        Scenario: Remove products from basket
          When User clicks multiple "addToCardBtns"
          And User clicks "basketBtn"
          And User removes all items from basket
          Then Items should be removed from basket

