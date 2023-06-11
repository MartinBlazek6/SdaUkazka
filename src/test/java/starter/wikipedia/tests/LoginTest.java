package starter.wikipedia.tests;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import starter.wikipedia.enums.Users;
import starter.wikipedia.services.NavigateActions;
import starter.wikipedia.services.UtilityActions;
import starter.wikipedia.services.WebElements;

@ExtendWith(SerenityJUnit5Extension.class)
class LoginTest {
    @Managed(driver = "chrome")
    WebDriver driver;

    NavigateActions navigate;
    UtilityActions actions;

    private final String expectedUrlAfterLogin = "https://www.saucedemo.com/inventory.html";

    @Test
    @Order(1)
    void loginPageValid() {
        navigate.toTheHomePage();
        actions.logIn(Users.STANDARD_USER.name().toLowerCase(),"secret_sauce");
        Assertions.assertEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
    }

    @Test
    @Order(2)
    void loginPageInvalidPassword() {
        navigate.toTheHomePage();
        actions.logIn(Users.STANDARD_USER.name().toLowerCase(),"invalidPassword");
        Assertions.assertNotEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
    }
    @Test
    @Order(3)
    void loginPageInvalidPasswordAndUsername() {
        navigate.toTheHomePage();
        actions.logIn("InvalidUsername","invalidPassword");
        Assertions.assertNotEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
    }
    @Test
    @Order(4)
    void loginPageInvalidUsername() {
        navigate.toTheHomePage();
        actions.logIn("InvalidUsername",System.getenv("password"));
        Assertions.assertNotEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
    }

    @Test
    @Order(5)
    void loginPageSwitchedCredentials() {
        navigate.toTheHomePage();
        actions.logIn("secret_sauce",Users.STANDARD_USER.name().toLowerCase());
        Assertions.assertNotEquals(expectedUrlAfterLogin,driver.getCurrentUrl());
    }
}
