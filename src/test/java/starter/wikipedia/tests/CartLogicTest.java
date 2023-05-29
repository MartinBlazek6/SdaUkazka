package starter.wikipedia.tests;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import starter.wikipedia.enums.Items;
import starter.wikipedia.enums.Users;
import starter.wikipedia.services.NavigateActions;
import starter.wikipedia.services.UtilityActions;
import starter.wikipedia.services.WebElements;

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
        actions.logIn(Users.STANDARD_USER.name().toLowerCase(),System.getenv("password"));
        Assertions.assertEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
    }

    @Test
    void addAndRemoveFromCart() {
        actions.addToCartByName(Items.BACKPACK.getValue());
        actions.addToCartByName(Items.BIKE_LIGHT.getValue());
        Assertions.assertEquals(String.valueOf(2),webElements.cartItems());

        actions.removeFromCartByName(Items.BACKPACK.getValue());
        Assertions.assertEquals(String.valueOf(3),webElements.cartItems());
    }

    @AfterEach
    void afterEach(){

    }
}
