package stepDefinitions;

import com.microsoft.playwright.*;
import driverSession.CucumberHook;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pom.LoginPOM;

import static driverSession.CucumberHook.page;
import static pom.ElementsMap.elementsMap;


public class LoginSteps {


    LoginPOM loginPOM = new LoginPOM();


    @Then("User should navigate to Home Page")
    public void userShouldNavigateToHomePage() {
        Assert.assertTrue(loginPOM.getAppLogo().isVisible());
    }

    @Then("User should get {string} message")
    public void userShouldGetMessage(String text) {
        Assert.assertEquals(loginPOM.getErrorMsg().textContent(), text);
    }


    @Given("User is in Login Page")
    public void userIsInLoginPage() {


    }
}
