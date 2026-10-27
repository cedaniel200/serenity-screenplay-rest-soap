package com.cedaniel200.screenplay.restapi.util;

public enum RestService {

    BASE_URL("https://reqres.in/api"),
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
