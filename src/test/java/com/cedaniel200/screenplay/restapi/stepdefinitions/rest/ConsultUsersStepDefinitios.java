package com.cedaniel200.screenplay.restapi.stepdefinitions.rest;

import com.cedaniel200.screenplay.restapi.questions.TheUsersContract;
import com.cedaniel200.screenplay.restapi.tasks.ConsultTheUsers;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ConsultUsersStepDefinitios {

    @When("^consulting the page (\\d+) of users$")
    public void consultingThePageOfUsers(int page) {
        theActorInTheSpotlight().attemptsTo(
                ConsultTheUsers.ofPage(page)
        );
    }

    @Then("^I should see (\\d+) users on page (\\d+) with the first id (\\d+)$")
    public void iShouldSeeUsersOnPageWithTheFirstId(int expectedUsers, int expectedPage, int expectedFirstId) {
        theActorInTheSpotlight().should(
                seeThat(TheUsersContract.isRespected(expectedUsers, expectedPage, expectedFirstId))
        );
    }
}
