package com.demo.tasks;

import com.demo.models.Usuario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

public class ActualizarUsuarioApi implements Task {

    private final Usuario usuario;

    public ActualizarUsuarioApi(Usuario usuario) {
        this.usuario = usuario;
    }

    public static ActualizarUsuarioApi conDatos(Usuario usuario) {
        return new ActualizarUsuarioApi(usuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Put.to("/user/" + usuario.getUsername())
                .with(request -> request
                    .header("Content-Type", "application/json")
                    .body(usuario)
                )
        );
    }
}
