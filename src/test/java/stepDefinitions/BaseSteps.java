package stepDefinitions;

import com.microsoft.playwright.Locator;
import driverSession.CucumberHook;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Getter;
import org.testng.Assert;
import pom.HomePagePOM;
import pom.LoginPOM;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import static pom.ElementsMap.elementsMap;

public class BaseSteps extends BaseMethods {

    @Getter
    private static int size;

    @When("User fills {string} in {string}")
    public void userFillsIn(String text, String element) {
        elementsMap.get(element).fill(text);
    }

    @And("User clicks {string}")
    public void userClicks(String element) {
        elementsMap.get(element).click();
    }

    @When("User selects {string} from {string}")
    public void userSelectsFrom(String text, String element) {
        elementsMap.get(element).selectOption(text);
    }

    @When("User clicks multiple {string}")
    public void userClicksMultiple(String elements) throws InterruptedException {
        List<Locator> locators = elementsMap.get(elements).all();
        size = ((int)(Math.random() * locators.size()) + 1);
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            locators.get(i).click();
        }
    }
}
