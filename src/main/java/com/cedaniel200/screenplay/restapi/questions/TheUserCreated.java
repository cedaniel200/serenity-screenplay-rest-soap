package com.cedaniel200.screenplay.restapi.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("The user was created with the data sent")
public class TheUserCreated implements Question<Boolean> {

    private final String expectedName;

    public TheUserCreated(String expectedName) {
        this.expectedName = expectedName;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        if (lastResponse().statusCode() != 201) {
            return false;
        }
        JsonPath body = lastResponse().body().jsonPath();
        return expectedName.equals(body.getString("name"))
                && body.getString("id") != null
                && !body.getString("id").isBlank();
    }

    public static TheUserCreated withName(String name) {
        return new TheUserCreated(name);
    }
}
