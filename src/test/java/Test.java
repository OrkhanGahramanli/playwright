import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.function.BooleanSupplier;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class Test {

   // Shared between all tests in this class.
   Playwright playwright;
   Browser browser;

   // New instance for each test method.
   BrowserContext context;
   Page page;

   @BeforeClass
   void launchBrowser() {
      playwright = Playwright.create();
      browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
              .setHeadless(false).setChannel("chrome"));
   }

   @AfterClass
   void closeBrowser() {
      playwright.close();
   }

   @BeforeMethod
   void createContextAndPage() {
      context = browser.newContext();
      page = context.newPage();
   }

   @AfterMethod
   void closeContext() {
      context.close();
   }

   @org.testng.annotations.Test
   void shouldClickButton() {
      page.navigate("data:text/html,<script>var result;</script><button onclick='result=\"Clicked\"'>Go</button>");
      page.locator("button").click();
      assertEquals("Clicked", page.evaluate("result"));
   }

   @org.testng.annotations.Test
   void shouldCheckTheBox() {
      page.setContent("<input id='checkbox' type='checkbox'></input>");
      page.locator("input").check();
      assertTrue((Boolean) page.evaluate("() => window['checkbox'].checked"));
   }

   @org.testng.annotations.Test
   void shouldSearchWiki() {
      page.navigate("https://www.wikipedia.org/");
      page.locator("input[name=\"search\"]").click();
      page.locator("input[name=\"search\"]").fill("playwright");
      page.locator("input[name=\"search\"]").press("Enter");
      assertEquals("https://en.wikipedia.org/wiki/Playwright", page.url());
   }

}
