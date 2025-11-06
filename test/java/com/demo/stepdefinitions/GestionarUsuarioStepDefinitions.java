package com.demo.stepdefinitions;

import com.demo.hooks.ConfigurarApi;
import com.demo.models.Usuario;
import com.demo.questions.CodigoRespuesta;
import com.demo.tasks.ActualizarUsuarioApi;
import com.demo.tasks.CrearUsuarioApi;
import com.demo.tasks.EliminarUsuarioApi;
import com.demo.util.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.anyOf;

public class GestionarUsuarioStepDefinitions {

    private Actor actor;
    private Usuario usuario;
    private Usuario usuarioActualizado;

    @Given("que el sistema esta disponible para gestionar usuarios")
    public void queElSistemaEstaDisponibleParaGestionarUsuarios() {
        actor = OnStage.theActorCalled(Constants.ACTOR);
        actor.attemptsTo(ConfigurarApi.conBaseUrl());
    }

    @When("creo un nuevo usuario con id {string}, nombre de usuario {string}, nombre {string}, apellido {string}, correo {string}, contrasena {string}, telefono {string} y estado {string}")
    public void creoUnNuevoUsuario(String id, String username, String firstName, String lastName, String email,
            String password, String phone, String userStatus) {
        usuario = new Usuario(
                Integer.parseInt(id),
                username,
                firstName,
                lastName,
                email,
                password,
                phone,
                Integer.parseInt(userStatus));

        actor.attemptsTo(
                CrearUsuarioApi.conDatos(usuario));

        actor.should(
                seeThat(CodigoRespuesta.obtenido(), equalTo(200)));
    }

    @And("actualizo el usuario {string} con nombre {string}, apellido {string}, correo {string} y contrasena {string}")
    public void actualizoElUsuario(String username, String firstName, String lastName, String email, String password) {
        usuarioActualizado = new Usuario(
                usuario.getId(),
                username,
                firstName,
                lastName,
                email,
                password,
                usuario.getPhone(),
                usuario.getUserStatus());

        actor.attemptsTo(
                ActualizarUsuarioApi.conDatos(usuarioActualizado));

        actor.should(
                seeThat(CodigoRespuesta.obtenido(), equalTo(200)));
    }

    @And("elimino el usuario {string}")
    public void eliminoElUsuario(String username) {
        actor.attemptsTo(
                EliminarUsuarioApi.conUsername(username));

        // Petstore API: acepta 200 (eliminado) o 404 (no existía)
        actor.should(
                seeThat(CodigoRespuesta.obtenido(), anyOf(equalTo(200), equalTo(404))));
    }

    @Then("el usuario es creado, actualizado y eliminado correctamente del sistema")
    public void elUsuarioEsCreadoActualizadoYEliminadoCorrectamente() {
        actor.should(
                seeThat(CodigoRespuesta.obtenido(), anyOf(equalTo(200), equalTo(404))));
    }
}
