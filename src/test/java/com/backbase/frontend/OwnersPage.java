package com.backbase.frontend;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


public class OwnersPage extends PageObject {
    @FindBy(css = "input[type='text']")
    WebElement searchInput;

    @FindBy(css = "table.table-striped")
    WebElement resultTable;

    public void enterSearchTerm(String term) {
        searchInput.clear();
        searchInput.sendKeys(term);
    }

    public void verifyResultsCount(int expectedCount) {
        List<WebElement> rows = resultTable.findElements(By.cssSelector("tbody tr"));
        assertThat(rows).hasSize(expectedCount);
    }

    public void verifyNewOwner(String ownerName) {
        assertThat(resultTable.getText()).contains(ownerName);
    }
}
