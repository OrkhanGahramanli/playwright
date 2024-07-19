package stepDefinitions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import driverSession.CucumberHook;

public class BaseMethods {

    Page page = CucumberHook.page.get();


}
