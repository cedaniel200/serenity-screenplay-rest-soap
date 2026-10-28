package com.cedaniel200.screenplay.restapi.utils;

public enum RestService {

    BASE_URL("http://localhost:8089/api"),
    CREATE_USER("/users"),
    CONSULT_USERS("/users?page=%d");

    private String uri;

    RestService(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return uri;
    }
}
