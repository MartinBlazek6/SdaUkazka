package starter.wikipedia.services;

import net.serenitybdd.core.pages.PageComponent;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

public class WebElements extends PageComponent {

    @FindBy(id="firstHeading")
    private WebElementFacade firstHeading;

    @FindBy(xpath = "//*[@id='shopping_cart_container']/a/span")
    private WebElementFacade cart;
    @FindBy(className = "shopping_cart_link")
    private WebElementFacade emptyCart;

    @FindBy(xpath = "//div[@id='checkout_summary_container']/div/div[2]/div[4]")
    private WebElementFacade provider;

    @FindBy(id = "checkout")
    private WebElementFacade checkoutButton;

    public String getFirstHeading() {
        return firstHeading.getText();
    }

    public String cartItems() {
        return cart.getText();
    }
    public void clickCartIcon(){
        cart.click();
    }
    public void clickEmptyCartIcon(){
        emptyCart.click();
    }

    public String getShippingProvider(){
        return provider.getText();
    }

    public boolean checkoutButtonIsClickable(){
       return checkoutButton.isClickable();
    }
    public void clearCart(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.localStorage.removeItem('cart-contents');");
        jsExecutor.executeScript("window.localStorage.removeItem('cart-contents');");
        getDriver().navigate().refresh();
    }

}
