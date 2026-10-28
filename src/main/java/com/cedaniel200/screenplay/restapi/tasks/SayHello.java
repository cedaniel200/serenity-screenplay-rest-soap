package com.cedaniel200.screenplay.restapi.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static com.cedaniel200.screenplay.restapi.utils.SOAPService.SAY_HELLO;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SayHello implements Task {

    private final String name;

    public SayHello(String name) {
        this.name = name;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(

        );
    }

    public static SayHello to(String name) {
        return instrumented(SayHello.class, name);
    }
}
