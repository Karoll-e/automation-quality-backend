package com.demo.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class EliminarUsuarioApi implements Task {

    private final String username;

    public EliminarUsuarioApi(String username) {
        this.username = username;
    }

    public static EliminarUsuarioApi conUsername(String username) {
        return new EliminarUsuarioApi(username);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Delete.from("/user/" + username)
        );
    }
}
