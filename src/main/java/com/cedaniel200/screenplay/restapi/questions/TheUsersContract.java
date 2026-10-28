package com.cedaniel200.screenplay.restapi.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

/**
 * Comprueba el contrato de la respuesta y dos valores concretos:
 * la forma de cada usuario y la paginación.
 */
@Subject("The users response respects the agreed contract")
public class TheUsersContract implements Question<Boolean> {

    private static final List<String> REQUIRED_FIELDS =
            List.of("id", "email", "first_name", "last_name", "avatar");

    @Override
    public Boolean answeredBy(Actor actor) {
        JsonPath body = lastResponse().body().jsonPath();

        if (lastResponse().statusCode() != 200) {
            return false;
        }
        if (body.getInt("total_pages") != 2) {
            return false;
        }

        List<Map<String, Object>> users = body.getList("data");
        if (users == null || users.size() != 6) {
            return false;
        }
        if (!users.stream().allMatch(TheUsersContract::hasEveryFieldWithTheRightType)) {
            return false;
        }

        return ((Number) users.get(0).get("id")).intValue() == 7;
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

    public static TheUsersContract isRespected() {
        return new TheUsersContract();
    }
}
