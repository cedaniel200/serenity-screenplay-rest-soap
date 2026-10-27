package com.cedaniel200.screenplay.restapi.stepdefinitions.rest;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static com.cedaniel200.screenplay.restapi.utils.RestService.BASE_URL;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class StageConfiguration {

    private static final String CESAR = "Cesar";

    @Before("@rest")
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
        theActorCalled(CESAR).whoCan(CallAnApi.at(BASE_URL.toString()));
    }
}
