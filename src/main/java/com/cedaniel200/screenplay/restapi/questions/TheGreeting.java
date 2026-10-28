package com.cedaniel200.screenplay.restapi.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("The greeting matches the expected message")
public class TheGreeting implements Question<Boolean> {

    private final String expectedMessage;

    public TheGreeting(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return lastResponse().statusCode() == 200
                && lastResponse().body().asString().contains(expectedMessage);
    }

    public static TheGreeting is(String message) {
        return new TheGreeting(message);
    }
}
