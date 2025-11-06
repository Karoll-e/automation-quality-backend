package com.demo.hooks;

import com.demo.util.Constants;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class ConfigurarApi implements Task {

    public static ConfigurarApi conBaseUrl() {
        return new ConfigurarApi();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        RestAssured.baseURI = Constants.API_URL;
        actor.whoCan(CallAnApi.at(Constants.API_URL));
    }
}
