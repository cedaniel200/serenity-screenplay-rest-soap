package com.cedaniel200.screenplay.restapi.stepdefinitions.soap;

import com.cedaniel200.screenplay.restapi.questions.LastResponseStatusCode;
import com.cedaniel200.screenplay.restapi.tasks.ConsultBank;
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

public class ConsultBankStepDefinitions {

    private static final String CESAR = "Cesar";

    @Before("@soap")
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
        theActorCalled(CESAR).whoCan(CallAnApi.at(BASE_URL.toString()));
    }

    @When("^he consulted the bank with code (\\d+)$")
    public void heConsultedTheBankWithCode(int codeBank) throws Exception {
        theActorInTheSpotlight().attemptsTo(
                ConsultBank.withCode(codeBank)
        );
    }

    @Then("^you should see the bank's information$")
    public void youShouldSeeTheBankSInformation() throws Exception {
        theActorInTheSpotlight().should(seeThat("last response status code is 200", LastResponseStatusCode.is(200)));
    }

}
