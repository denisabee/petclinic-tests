package com.backbase.api;

import io.restassured.RestAssured;
import com.backbase.WebServiceEndPoints;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class PetClinicAPI {

    public AppStatus vetsStatus() {
        int statusCode = RestAssured.get(WebServiceEndPoints.VETS.getUrl()).statusCode();
        return (statusCode == 200) ? AppStatus.RUNNING : AppStatus.DOWN;
    }

    public AppStatus getOwner(String ownerId) {
        int statusCode = RestAssured.get(WebServiceEndPoints.OWNER.getUrl() + ownerId).statusCode();
        return (statusCode == 200) ? AppStatus.RUNNING : AppStatus.DOWN;
    }

    public AppStatus addNewPet(String petName, String ownerId) {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.body("{\"id\": 50, \"name\": \"" + petName + "\", \"birthDate\": \"2025-06-01\", \"typeId\": 6}");

        Response response = request.post("/owners/" + ownerId + "/pets");
        return (response.statusCode() == 204) ? AppStatus.RUNNING : AppStatus.DOWN;

    }

    public boolean hasPet(String newPet, String ownerId) {
        String responseBody = RestAssured.get(WebServiceEndPoints.OWNER.getUrl() + ownerId).body().asString();
        return (responseBody.contains(newPet));
    }

    @Step("Get vets list")
    public void getVetsList() {
        SerenityRest.get(WebServiceEndPoints.VETS.getUrl());
    }
}
