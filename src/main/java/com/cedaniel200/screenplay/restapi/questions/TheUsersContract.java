package com.cedaniel200.screenplay.restapi.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

/**
 * Comprueba el contrato de la respuesta (campos y tipos) y los valores que espera el escenario.
 * Los datos esperados llegan desde la feature, no se fijan aquí.
 */
@Subject("The users response respects the agreed contract")
public class TheUsersContract implements Question<Boolean> {

    private static final List<String> REQUIRED_FIELDS =
            List.of("id", "email", "first_name", "last_name", "avatar");

    private final int expectedUsers;
    private final int expectedPage;
    private final int expectedFirstId;

    public TheUsersContract(int expectedUsers, int expectedPage, int expectedFirstId) {
        this.expectedUsers = expectedUsers;
        this.expectedPage = expectedPage;
        this.expectedFirstId = expectedFirstId;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        if (lastResponse().statusCode() != 200) {
            return false;
        }

        JsonPath body = lastResponse().body().jsonPath();
        if (body.getInt("page") != expectedPage) {
            return false;
        }

        List<Map<String, Object>> users = body.getList("data");
        if (users == null || users.size() != expectedUsers) {
            return false;
        }
        if (!users.stream().allMatch(TheUsersContract::hasEveryFieldWithTheRightType)) {
            return false;
        }

        Object firstId = users.get(0).get("id");
        return firstId instanceof Number && ((Number) firstId).intValue() == expectedFirstId;
    }

    private static boolean hasEveryFieldWithTheRightType(Map<String, Object> user) {
        if (!user.keySet().containsAll(REQUIRED_FIELDS)) {
            return false;
        }
        if (!(user.get("id") instanceof Number)) {
            return false;
        }
        return REQUIRED_FIELDS.stream()
                .filter(field -> !field.equals("id"))
                .allMatch(field -> user.get(field) instanceof String);
    }

    public static TheUsersContract isRespected(int expectedUsers, int expectedPage, int expectedFirstId) {
        return new TheUsersContract(expectedUsers, expectedPage, expectedFirstId);
    }
}
