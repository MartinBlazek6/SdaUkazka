package starter.wikipedia.tests;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.apache.http.util.Asserts;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import starter.wikipedia.enums.Items;
import starter.wikipedia.enums.Users;
import starter.wikipedia.services.NavigateActions;
import starter.wikipedia.services.UtilityActions;
import starter.wikipedia.services.WebElements;

@ExtendWith(SerenityJUnit5Extension.class)
public class SendOrderTest {

    @Managed(driver = "chrome")
    WebDriver driver;

    NavigateActions navigate;
    UtilityActions actions;

    WebElements webElements;
    private final String expectedUrlAfterLogin = "https://www.saucedemo.com/inventory.html";
    private final String expectedUrlAfterOrder = "https://www.saucedemo.com/checkout-complete.html";


    @BeforeEach
    void beforeEach(){
        navigate.toTheHomePage();
        actions.logIn(Users.STANDARD_USER.name().toLowerCase(),"secret_sauce");
        Assertions.assertEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
    }

    @Test
    @Order(1)
    void cancelOrder() {
        actions.addToCartByName(Items.BACKPACK.getValue());
        actions.addToCartByName(Items.BIKE_LIGHT.getValue());
        webElements.clickCartIcon();
        actions.clickCheckOutButton();
        actions.fillCheckoutInformationForm("Martin","Blazek","912301823");
        actions.clickContinueButton();
        actions.clickCancelButton();
        Assertions.assertEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
        Assertions.assertEquals(String.valueOf(2),webElements.cartItems());
    }

    @Test
    @Order(2)
    void checkShippingProvider() {
        actions.addToCartByName(Items.BACKPACK.getValue());
        actions.addToCartByName(Items.BIKE_LIGHT.getValue());
        webElements.clickCartIcon();
        actions.clickCheckOutButton();
        actions.fillCheckoutInformationForm("Martin","Blazek","912301823");
        actions.clickContinueButton();
        Assertions.assertEquals("Free Pony Express Delivery!", webElements.getShippingProvider());
    }
    @Test
    @Order(3)
    void tryOrderItems() {
        actions.addToCartByName(Items.BACKPACK.getValue());
        actions.addToCartByName(Items.BIKE_LIGHT.getValue());
        webElements.clickCartIcon();
        actions.clickCheckOutButton();
        actions.fillCheckoutInformationForm("Martin","Blazek","912301823");
        actions.clickContinueButton();
        actions.clickFinishButton();
        Assertions.assertEquals(expectedUrlAfterOrder,driver.getCurrentUrl());
    }

    @Test
    @Order(4)
    void tryGetBeckToHomeAfterOrder() {
        actions.addToCartByName(Items.BACKPACK.getValue());
        actions.addToCartByName(Items.BIKE_LIGHT.getValue());
        webElements.clickCartIcon();
        actions.clickCheckOutButton();
        actions.fillCheckoutInformationForm("Martin","Blazek","912301823");
        actions.clickContinueButton();
        actions.clickFinishButton();
        actions.clickBackHomeButton();
        Assertions.assertEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
    }
    @Test
    @Order(5)
    void tryToOrderNothing() {
        webElements.clickEmptyCartIcon();
        Assertions.assertFalse(webElements.checkoutButtonIsClickable());
        actions.clickCheckOutButton();
        actions.fillCheckoutInformationForm("Martin","Blazek","912301823");
        actions.clickContinueButton();
        actions.clickFinishButton();
        actions.clickBackHomeButton();
        Assertions.assertEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
        Assertions.fail("It should be not possible to order nothing");
    }

    @AfterEach
    void afterEach(){
        webElements.clearCart();
    }
}
