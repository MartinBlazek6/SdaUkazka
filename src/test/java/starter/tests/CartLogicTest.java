package starter.tests;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import starter.enums.Items;
import starter.enums.Users;
import starter.services.NavigateActions;
import starter.services.UtilityActions;
import starter.services.WebElements;

@ExtendWith(SerenityJUnit5Extension.class)
public class CartLogicTest {
    @Managed(driver = "chrome")
    WebDriver driver;

    NavigateActions navigate;
    UtilityActions actions;

    WebElements webElements;

    private final String expectedUrlAfterLogin = "https://www.saucedemo.com/inventory.html";

    @BeforeEach
    void beforeEach(){
        navigate.toTheHomePage();
        actions.logIn(Users.STANDARD_USER.name().toLowerCase(),"secret_sauce");
//       actions.logIn(Users.STANDARD_USER.name().toLowerCase(),System.getenv("password"));
        Assertions.assertEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
    }

    @Test
    void addAndRemoveFromCart() {
        actions.addToCartByName(Items.BACKPACK.getValue());
        actions.addToCartByName(Items.BIKE_LIGHT.getValue());
        Assertions.assertEquals(String.valueOf(2),webElements.cartItems());
        actions.removeFromCartByName(Items.BACKPACK.getValue());
        Assertions.assertEquals(String.valueOf(1),webElements.cartItems());
    }

    @Test
    @Order(1)
    void deleteCartItemsFromCartPage() {
        actions.addToCartByName(Items.BACKPACK.getValue());
        actions.addToCartByName(Items.BIKE_LIGHT.getValue());
        Assertions.assertEquals(String.valueOf(2),webElements.cartItems());
        webElements.clickCartIcon();
        actions.removeFromCartByName(Items.BACKPACK.getValue());
        Assertions.assertEquals(String.valueOf(1),webElements.cartItems());
    }

    @Test
    @Order(2)
    void checkCartItems() {
        actions.addToCartByName(Items.BACKPACK.getValue());
        actions.addToCartByName(Items.BIKE_LIGHT.getValue());
        webElements.clickCartIcon();
        WebElement BACKPACK = actions.findElementByTextContains("Sauce Labs Backpack");
        WebElement BIKE_LIGHT = actions.findElementByTextContains("Sauce Labs Bike Light");
        Assertions.assertEquals("Sauce Labs Backpack",BACKPACK.getText());
        Assertions.assertEquals("Sauce Labs Bike Light",BIKE_LIGHT.getText());
    }

    @AfterEach
    void afterEach(){
        webElements.clearCart();
    }
}
