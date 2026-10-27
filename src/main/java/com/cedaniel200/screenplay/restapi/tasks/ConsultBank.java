package com.cedaniel200.screenplay.restapi.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static com.cedaniel200.screenplay.restapi.util.SOAPService.CONSULT_BANK;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultBank implements Task {

    private int code;

    public ConsultBank(int code) {
        this.code = code;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(CONSULT_BANK.toString())
                        .with(request -> request
                                .header("Content-Type", "application/soap+xml;charset=UTF-8")
                                .header("Accept-Encoding", "gzip,deflate")
                                .body("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:blz=\"http://thomas-bayer.com/blz/\">\n" +
                                        "   <soap:Header/>\n" +
                                        "   <soap:Body>\n" +
                                        "      <blz:getBank>\n" +
                                        "         <blz:blz>" + code + "</blz:blz>\n" +
                                        "      </blz:getBank>\n" +
                                        "   </soap:Body>\n" +
                                        "</soap:Envelope>\n")
                        )
        );
    }

    public static ConsultBank withCode(int code) {
        return instrumented(ConsultBank.class, code);
    }
}
