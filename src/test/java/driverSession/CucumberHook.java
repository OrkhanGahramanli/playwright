package driverSession;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotType;
import io.cucumber.java.*;
import org.apache.commons.io.FilenameUtils;
import org.testng.annotations.*;
import org.testng.internal.invokers.ITestInvoker;

import java.util.List;

public class CucumberHook {
//public static Playwright playwright;
//public static Browser browser;
//public static BrowserContext browserContext;
//public static Page page;

    public static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    public static ThreadLocal<Browser> browser = new ThreadLocal<>();
    public static ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    public static ThreadLocal<Page> page = new ThreadLocal<>();

    public static ThreadLocal<String>  pomName = new ThreadLocal<>();



    @Before
    public static void beforeScenario(Scenario scenario) {
        playwright.set(Playwright.create());
        browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setChannel("chrome").setArgs(List.of("--start-maximized"))));

        browserContext.set(browser.get().newContext(new Browser.NewContextOptions().setViewportSize(null)));
        page.set(browserContext.get().newPage());
        page.get().navigate("https://www.saucedemo.com/");
        page.get().setDefaultTimeout(10000);

        pomName.set(FilenameUtils.getBaseName(scenario.getUri().toString()));
        try {
            Class<?> clazz = Class.forName("pom." + pomName.get() + "POM");
            Object o = clazz.getDeclaredConstructor().newInstance();
        }catch (Exception ignored){

        }
    }

    @After
    public static void afterScenario(Scenario scenario) throws InterruptedException {
        if(scenario.isFailed()) {
            Thread.sleep(300);
            final byte[] screenshot = page.get().screenshot();
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        page.get().close();
    }


}
