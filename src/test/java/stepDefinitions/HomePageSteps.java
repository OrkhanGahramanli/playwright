package stepDefinitions;

import com.microsoft.playwright.Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pom.HomePagePOM;

import java.util.List;

import static pom.ElementsMap.elementsMap;

public class HomePageSteps {

    HomePagePOM homePagePOM = new HomePagePOM();

    @Then("Products should be ordered by {string}")
    public void products_should_be_ordered_by(String sortType) {
        List<Locator> productNames = homePagePOM.getInventoryNames().all();
        List<Locator> productPrices = homePagePOM.getInventoryPrices().all();
        switch (sortType) {
            case "az":
                for (int i = 0; i < productNames.size(); i++) {
                    if (i==productNames.size()-1) break;
//                    System.out.println(productNames.get(i).textContent()+ "-" + productNames.get(i+1).textContent());
                    Assert.assertTrue(productNames.get(i).textContent().compareTo(productNames.get(i+1).textContent()) <= 0,
                            productNames.get(i).textContent()+ "-" + productNames.get(i+1).textContent());
                }
                break;

            case "za":
                for (int i = 0; i < productNames.size(); i++) {
                    if (i==productNames.size()-1) break;
                    Assert.assertTrue(productNames.get(i).textContent().compareTo(productNames.get(i+1).textContent()) >= 0);
                }
                break;

            case "lohi":
                for (int i = 0; i < productPrices.size(); i++) {
                    if (i==productPrices.size()-1) break;
                    Assert.assertTrue(Double.parseDouble(productPrices.get(i).textContent().replace("$", ""))<=
                            Double.parseDouble(productPrices.get(i+1).textContent().replace("$", "")));
                }
                break;

            case "hilo":
                for (int i = 0; i < productPrices.size(); i++) {
                    if (i==productPrices.size()-1) break;
                    Assert.assertTrue(Double.parseDouble(productPrices.get(i).textContent().replace("$", ""))>=
                            Double.parseDouble(productPrices.get(i+1).textContent().replace("$", "")));
                }
                break;

        }
    }

    @Then("Products count in basket should be visible on {string}")
    public void productsCountInBasketShouldBeVisibleOn(String basketIcon) {
        Assert.assertEquals(Integer.parseInt(elementsMap.get(basketIcon).textContent()), BaseSteps.getSize());
    }

    @Then("Products should be displayed in basket")
    public void productsShouldBeDisplayedInBasket() {
        Assert.assertEquals(homePagePOM.getInventoryNames().all().size(), BaseSteps.getSize());
    }

    @Then("Items should be removed from basket")
    public void itemsShouldBeRemovedFromBasket() {
        Assert.assertEquals(homePagePOM.getRemovedCartItems().all().size(), BaseSteps.getSize());
    }

    @And("User removes all items from basket")
    public void userRemovesAllItemsFromBasket() throws InterruptedException {
        Locator removeItemBtns = homePagePOM.getRemoveProductBtns();
        int count = removeItemBtns.count();
//        for (Locator removeItemBtn : removeItemBtns){
//            System.out.println(removeItemBtn.getAttribute("name"));
//        }
        for (int i = 0; i < count;) {
//            System.out.println("index: " + i + "--" + removeItemBtns.get(i).getAttribute("name") + "size: " + removeItemBtns.size());
            removeItemBtns.nth(i).click();
            count = removeItemBtns.count();
        }

//        while (homePagePOM.getRemoveProductBtns().isVisible()){
//            homePagePOM.getRemoveProductBtns().click();
//        }
        Thread.sleep(3000);
    }
}
