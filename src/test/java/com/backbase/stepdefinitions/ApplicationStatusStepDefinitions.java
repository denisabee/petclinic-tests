package com.backbase.stepdefinitions;

import com.backbase.frontend.OwnerRegistrationPage;
import com.backbase.frontend.OwnersPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.backbase.api.AppStatus;
import com.backbase.api.PetClinicAPI;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ApplicationStatusStepDefinitions {

    @Steps
    PetClinicAPI theApplication;
    OwnersPage ownersPage;
    OwnerRegistrationPage ownerRegistrationPage;

    @Managed
    WebDriver driver;

    @Given("the status of the API is 200")
    public void status_of_API_is_200() {
        assertThat(theApplication.vetsStatus()).isEqualTo(AppStatus.RUNNING);
    }

    @When("I make a request to the vets end-point")
    public void i_make_request_to_vets_endpoint() {
        theApplication.getVetsList();
    }

    @Then("the API response should contain {string}")
    public void the_API_response_should_contain(String expectedVet) {
        restAssuredThat(lastResponse -> lastResponse.body(containsString(expectedVet)));
    }

    @Given("that owner with id {string} is present")
    public void thatOwnerWithIdIsPresent(String ownerId) {
        assertThat(theApplication.getOwner(ownerId)).isEqualTo(AppStatus.RUNNING);
    }

    @When("I add a new pet with name {string} for owner with id {string}")
    public void iAddANewPetWithNameForOwnerWithId(String petName, String ownerId) {
        assertThat(theApplication.addNewPet(petName, ownerId)).isEqualTo(AppStatus.RUNNING);
    }

    @Then("pet {string} will show in the list of pets for owner with id {string}")
    public void petWillShowInTheListOfPetsForOwnerWithId(String newPet, String ownerId) {
        assertThat(theApplication.hasPet(newPet, ownerId)).isTrue();
    }

    @Given("I am on Owners page")
    public void iAmOnOwnersPage() {
        driver.get("http://localhost:8080/#!/owners");
    }

    @When("I enter {string} in search filter box")
    public void iEnterInSearchFilterBox(String inputSearch) {
        ownersPage.enterSearchTerm(inputSearch);
    }

    @Then("I should see a list with {int} matching results")
    public void iShouldSeeAListWithMatchingResults(int expectedCount) {
        ownersPage.verifyResultsCount(expectedCount);
    }

    @Given("I am on Register owner page")
    public void iAmOnRegisterOwnerPage() {
        driver.get("http://localhost:8080/#!/owners/new");
    }

    @When("I fill in data for an owner named {string} and Submit")
    public void iFillInDataForAnOwnerNamedAndSubmit(String ownerName) {
        ownerRegistrationPage.registerOwner(ownerName);
    }

    @Then("I should see {string} in the Owners page")
    public void iShouldSeeInTheOwnersPage(String newOwner) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Search Filter']")));
        ownersPage.verifyNewOwner(newOwner);
    }
}