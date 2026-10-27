package com.cedaniel200.screenplay.restapi.stepdefinitions.rest;

import com.cedaniel200.screenplay.restapi.questions.LastResponseStatusCode;
import com.cedaniel200.screenplay.restapi.questions.TheUsersOnTheWeb;
import com.cedaniel200.screenplay.restapi.tasks.ConsultTheUsers;
import com.cedaniel200.screenplay.restapi.userinterfaces.ReqresPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.Scroll;

import static com.cedaniel200.screenplay.restapi.userinterfaces.ReqresPage.USERS_IN_JSON_FORMAT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ConsultUsersStepDefinitios {

    private ReqresPage reqresPage;

    @When("^consulting the second group of three users\\.$")
    public void consultingTheSecondGroupOfThreeUsers() throws Exception {
        theActorInTheSpotlight().attemptsTo(
                ConsultTheUsers.ofTheSecondGroup(),
                Open.browserOn(reqresPage),
                Scroll.to(USERS_IN_JSON_FORMAT)
        );

    }

    @Then("^I should see the users$")
    public void iShouldSeeTheUsers() throws Exception {
        theActorInTheSpotlight().should(seeThat("last response status code is 200" , LastResponseStatusCode.is(200)));
        theActorInTheSpotlight().should(seeThat(TheUsersOnTheWeb.correspondToThoseOfTheService()));
    }

}
