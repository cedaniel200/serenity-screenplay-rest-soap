package com.cedaniel200.screenplay.restapi.stepdefinitions.support;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * Servicios simulados que usan los escenarios. Se levantan una vez por ejecución, en el mismo JVM,
 * para que la suite no dependa de ningún servicio externo.
 */
public class MockServices {

    private static final int PORT = 8089;
    private static WireMockServer server;

    @BeforeAll
    public static void startServices() {
        server = new WireMockServer(options().port(PORT));
        server.start();

        server.stubFor(get(urlPathEqualTo("/api/users"))
                .withQueryParam("page", equalTo("2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(resource("/mocks/users-page-2.json"))));

        server.stubFor(post(urlPathEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody(resource("/mocks/user-created.json"))));

        server.stubFor(post(urlPathEqualTo("/services/hello"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml;charset=UTF-8")
                        .withBody(resource("/mocks/hello-response.xml"))));
    }

    @AfterAll
    public static void stopServices() {
        if (server != null) {
            server.stop();
        }
    }

    private static String resource(String path) {
        try (InputStream in = MockServices.class.getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalStateException("No se encontró el recurso " + path);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer " + path, e);
        }
    }
}
