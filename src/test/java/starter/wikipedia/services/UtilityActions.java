package starter.wikipedia.services;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

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
    public void sendKeysFirstName(String firstName) {
        getDriver().findElement(By.id("first-name")).sendKeys(firstName);
    }

    @Step
    public void sendKeysLastName(String lastName) {
        getDriver().findElement(By.id("last-name")).sendKeys(lastName);
    }

    @Step
    public void sendKeysPostalCode(String postalCode) {
        getDriver().findElement(By.id("postal-code")).sendKeys(postalCode);
    }

    @Step
    public void addToCartByName(String item) {
        getDriver().findElement(By.id("add-to-cart-" + item)).click();
    }

    @Step
    public void removeFromCartByName(String item) {
        getDriver().findElement(By.id("remove-" + item)).click();
    }

    @Step
    public WebElement findElementByTextContains(String text) {
        return getDriver().findElement(By.xpath("//*[text() = '" + text + "']"));
    }
    @Step
    public void clickCheckOutButton() {
        getDriver().findElement(By.id("checkout")).click();
    }
    @Step
    public void clickContinueButton() {
        getDriver().findElement(By.id("continue")).click();
    }
    @Step
    public void clickFinishButton() {
        getDriver().findElement(By.id("finish")).click();
    }
    @Step
    public void clickBackHomeButton() {
        getDriver().findElement(By.id("back-to-products")).click();
    }
    @Step
    public void clickCancelButton() {
        getDriver().findElement(By.id("cancel")).click();
    }


    @Step
    public void logIn(String username, String password) {
        sendKeysUsername(username);
        sendKeysPassword(password);
        clickLoginButton();
    }

    @Step
    public void fillCheckoutInformationForm(String firstName, String lastName, String postalCode) {
        sendKeysFirstName(firstName);
        sendKeysLastName(lastName);
        sendKeysPostalCode(postalCode);
    }

}
