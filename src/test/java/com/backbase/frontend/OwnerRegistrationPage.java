package com.backbase.frontend;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;

public class OwnerRegistrationPage extends PageObject {
    @FindBy(name = "firstName")
    WebElement inputFirstName;

    @FindBy(name = "lastName")
    WebElement inputLastName;

    @FindBy(name = "address")
    WebElement inputAddress;

    @FindBy(name = "city")
    WebElement inputCity;

    @FindBy(name = "telephone")
    WebElement inputTelephone;

    @FindBy(css = "button[type='submit']")
    WebElementFacade submitButton;

    public void registerOwner(String ownerName) {
        inputFirstName.sendKeys(ownerName);
        inputLastName.sendKeys("Smith");
        inputAddress.sendKeys("125 Main Road");
        inputCity.sendKeys("New York");
        inputTelephone.sendKeys("123456");

        submitButton.click();
    }
}
