package com.cedaniel200.screenplay.restapi.stepdefinitions.soap;

import com.cedaniel200.screenplay.restapi.questions.TheGreeting;
import com.cedaniel200.screenplay.restapi.tasks.SayHello;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static com.cedaniel200.screenplay.restapi.utils.SOAPService.BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class SayHelloStepDefinitions {

    private static final String CESAR = "Cesar";

    @Before("@soap")
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
        theActorCalled(CESAR).whoCan(CallAnApi.at(BASE_URL.toString()));
    }

    @When("^he says hello to (.*)$")
    public void heSaysHelloTo(String name) {
        theActorInTheSpotlight().attemptsTo(SayHello.to(name));
    }

    @Then("^he should see the greeting (.*)$")
    public void heShouldSeeTheGreeting(String message) {
        theActorInTheSpotlight().should(seeThat(TheGreeting.is(message)));
    }
}
