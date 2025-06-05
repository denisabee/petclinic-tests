package com.backbase;

public enum WebServiceEndPoints {
    VETS("http://localhost:8080/vets"),
    OWNER("http://localhost:8080/owners/");

    private final String url;

    WebServiceEndPoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
