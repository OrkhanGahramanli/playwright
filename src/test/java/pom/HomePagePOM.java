package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import driverSession.CucumberHook;
import lombok.Data;

import static pom.ElementsMap.elementsMap;
@Data
public class HomePagePOM {

    Page page = CucumberHook.page.get();

    {
        elementsMap.put("sortFilter", page.locator(".product_sort_container"));
        elementsMap.put("addToCardBtns", page.locator(".btn_inventory"));
        elementsMap.put("basketIcon", page.locator(".shopping_cart_badge"));
        elementsMap.put("basketBtn", page.locator(".shopping_cart_link"));
    }

    private final Locator inventoryNames = page.locator(".inventory_item_name");
    private final Locator inventoryPrices = page.locator("//*[@data-test='inventory-item-price']");
    private final Locator removedCartItems = page.locator(".removed_cart_item");
    private final Locator removeProductBtns = page.locator(".btn.btn_secondary.btn_small.cart_button");
}
