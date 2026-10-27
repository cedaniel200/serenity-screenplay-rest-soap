package com.cedaniel200.screenplay.restapi.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.annotations.DefaultUrl;

@DefaultUrl("https://reqres.in/")
public class ReqresPage extends PageObject{

    public static final Target USERS_IN_JSON_FORMAT = Target.the("users in json format")
            .locatedBy("//*[@id=\"console\"]/div[2]/div[2]/pre");

}
