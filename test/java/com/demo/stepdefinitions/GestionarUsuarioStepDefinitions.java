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
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class GestionarUsuarioStepDefinitions {

    private static final String USUARIO_KEY = "usuario";
    private static final String CODIGO_CREACION_KEY = "codigoCreacion";
    private static final String CODIGO_ACTUALIZACION_KEY = "codigoActualizacion";
    private static final String CODIGO_ELIMINACION_KEY = "codigoEliminacion";

    @Given("que el sistema esta disponible para gestionar usuarios")
    public void queElSistemaEstaDisponibleParaGestionarUsuarios() {
        OnStage.theActorCalled(Constants.ACTOR).attemptsTo(ConfigurarApi.conBaseUrl());
    }

    @When("creo un nuevo usuario con id {string}, nombre de usuario {string}, nombre {string}, apellido {string}, correo {string}, contrasena {string}, telefono {string} y estado {string}")
    public void creoUnNuevoUsuario(String id, String username, String firstName, String lastName, 
                                   String email, String password, String phone, String userStatus) {
        Usuario usuario = new Usuario(
                Integer.parseInt(id),
                username,
                firstName,
                lastName,
                email,
                password,
                phone,
                Integer.parseInt(userStatus)
        );

        OnStage.theActorInTheSpotlight().remember(USUARIO_KEY, usuario);
        OnStage.theActorInTheSpotlight().attemptsTo(CrearUsuarioApi.conDatos(usuario));
        
        // Almacenar el código de respuesta de la creación
        int codigoCreacion = CodigoRespuesta.obtenido().answeredBy(OnStage.theActorInTheSpotlight());
        OnStage.theActorInTheSpotlight().remember(CODIGO_CREACION_KEY, codigoCreacion);
    }

    @And("actualizo el usuario {string} con nombre {string}, apellido {string}, correo {string} y contrasena {string}")
    public void actualizoElUsuario(String username, String firstName, String lastName, String email, String password) {
        Usuario usuarioOriginal = OnStage.theActorInTheSpotlight().recall(USUARIO_KEY);
        
        Usuario usuarioActualizado = new Usuario(
                usuarioOriginal.getId(),
                username,
                firstName,
                lastName,
                email,
                password,
                usuarioOriginal.getPhone(),
                usuarioOriginal.getUserStatus()
        );

        OnStage.theActorInTheSpotlight().attemptsTo(ActualizarUsuarioApi.conDatos(usuarioActualizado));
        
        // Almacenar el código de respuesta de la actualización
        int codigoActualizacion = CodigoRespuesta.obtenido().answeredBy(OnStage.theActorInTheSpotlight());
        OnStage.theActorInTheSpotlight().remember(CODIGO_ACTUALIZACION_KEY, codigoActualizacion);
    }

    @And("elimino el usuario {string}")
    public void eliminoElUsuario(String username) {
        OnStage.theActorInTheSpotlight().attemptsTo(EliminarUsuarioApi.conUsername(username));
        
        // Almacenar el código de respuesta de la eliminación
        int codigoEliminacion = CodigoRespuesta.obtenido().answeredBy(OnStage.theActorInTheSpotlight());
        OnStage.theActorInTheSpotlight().remember(CODIGO_ELIMINACION_KEY, codigoEliminacion);
    }

    @Then("el usuario es creado, actualizado y eliminado correctamente del sistema")
    public void elUsuarioEsCreadoActualizadoYEliminadoCorrectamente() {
        // Validar que la creación fue exitosa
        Integer codigoCreacion = OnStage.theActorInTheSpotlight().recall(CODIGO_CREACION_KEY);
        OnStage.theActorInTheSpotlight().should(
                seeThat("El codigo de creacion", actor -> codigoCreacion, equalTo(200))
        );

        // Validar que la actualización fue exitosa
        Integer codigoActualizacion = OnStage.theActorInTheSpotlight().recall(CODIGO_ACTUALIZACION_KEY);
        OnStage.theActorInTheSpotlight().should(
                seeThat("El codigo de actualizacion", actor -> codigoActualizacion, equalTo(200))
        );

        // Validar que la eliminación fue exitosa
        Integer codigoEliminacion = OnStage.theActorInTheSpotlight().recall(CODIGO_ELIMINACION_KEY);
        OnStage.theActorInTheSpotlight().should(
                seeThat("El codigo de eliminacion", actor -> codigoEliminacion, equalTo(200))
        );
    }
}