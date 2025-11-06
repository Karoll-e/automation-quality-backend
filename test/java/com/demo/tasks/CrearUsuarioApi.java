package com.demo.tasks;

import com.demo.models.Usuario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class CrearUsuarioApi implements Task {

    private final Usuario usuario;

    public CrearUsuarioApi(Usuario usuario) {
        this.usuario = usuario;
    }

    public static CrearUsuarioApi conDatos(Usuario usuario) {
        return new CrearUsuarioApi(usuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Post.to("/user")
                .with(request -> request
                    .header("Content-Type", "application/json")
                    .body(usuario)
                )
        );
    }
}
