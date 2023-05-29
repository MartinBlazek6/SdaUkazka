package starter.wikipedia.services;

import net.serenitybdd.core.pages.PageComponent;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class WebElements extends PageComponent {

    @FindBy(id="firstHeading")
    private WebElementFacade firstHeading;

    @FindBy(xpath = "//*[@id='shopping_cart_container']/a/span")
    private WebElementFacade cart;

    public String getFirstHeading() {
        return firstHeading.getText();
    }

    public String cartItems() {
        return cart.getText();
    }

}
