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
                Post.to(SAY_HELLO.toString())
                        .with(request -> request
                                .header("Content-Type", "text/xml;charset=UTF-8")
                                .header("SOAPAction", "\"\"")
                                .body("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\""
                                        + " xmlns:hel=\"http://learnwebservices.com/services/hello\">"
                                        + "<soapenv:Body><hel:HelloRequest><hel:Name>" + name + "</hel:Name>"
                                        + "</hel:HelloRequest></soapenv:Body></soapenv:Envelope>")
                        )
        );
    }

    public static SayHello to(String name) {
        return instrumented(SayHello.class, name);
    }
}
