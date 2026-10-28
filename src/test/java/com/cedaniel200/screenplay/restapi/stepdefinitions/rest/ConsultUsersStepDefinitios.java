package com.cedaniel200.screenplay.restapi.stepdefinitions.rest;

import com.cedaniel200.screenplay.restapi.questions.TheUsersContract;
import com.cedaniel200.screenplay.restapi.tasks.ConsultTheUsers;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ConsultUsersStepDefinitios {

    @When("^consulting the second group of three users\\.$")
    public void consultingTheSecondGroupOfThreeUsers() {
        theActorInTheSpotlight().attemptsTo(
                ConsultTheUsers.ofTheSecondGroup()
        );
    }

    @Then("^I should see the users$")
    public void iShouldSeeTheUsers() {
        theActorInTheSpotlight().should(
                seeThat(TheUsersContract.isRespected())
        );
    }
}
