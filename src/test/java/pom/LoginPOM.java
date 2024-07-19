package pom;

import com.microsoft.playwright.*;
import driverSession.CucumberHook;
import io.cucumber.java.Before;
import lombok.Data;
import lombok.extern.java.Log;
import stepDefinitions.LoginSteps;

//import static driverSession.CucumberHook.page;
import static driverSession.CucumberHook.page;
import static pom.ElementsMap.elementsMap;

@Data
public class LoginPOM {

 Page page = CucumberHook.page.get();



    {
          elementsMap.put("userNameInputField", page.locator("#user-name"));
          elementsMap.put("passwordInputField", page.locator("#password"));
          elementsMap.put("loginBtn", page.locator("#login-button"));
    }

        private Locator appLogo = page.locator(".app_logo");
        private Locator errorMsg = page.locator("//*[@data-test='error']");

}
