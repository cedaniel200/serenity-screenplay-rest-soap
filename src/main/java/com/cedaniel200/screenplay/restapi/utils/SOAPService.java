package com.cedaniel200.screenplay.restapi.utils;

public enum SOAPService {
    BASE_URL("http://localhost:8089"),
    SAY_HELLO("/services/hello");

    private String uri;

    SOAPService(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return uri;
    }
}
