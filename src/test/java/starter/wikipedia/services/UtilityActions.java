package starter.wikipedia.services;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class UtilityActions extends UIInteractions {

    @Step("Search by keyword '{0}'")
    public void searchBy(String keyword) {
        $("#searchInput").sendKeys(keyword, Keys.ENTER);
    }

    @Step
    public void sendKeysUsername(String username) {
        getDriver().findElement(By.id("user-name")).sendKeys(username);
    }

    @Step
    public void sendKeysPassword(String password) {
        getDriver().findElement(By.id("password")).sendKeys(password);
    }

    @Step
    public void clickLoginButton() {
        getDriver().findElement(By.id("login-button")).click();
    }

    @Step
    public void addToCartByName(String item) {
        getDriver().findElement(By.id("add-to-cart-"+item)).click();
    }
    @Step
    public void removeFromCartByName(String item) {
        getDriver().findElement(By.id("remove-"+item)).click();
    }

    @Step
    public void logIn(String username, String password) {
        sendKeysUsername(username);
        sendKeysPassword(password);
        clickLoginButton();
    }

}
