package com.demo.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class GestionarUsuarioStepDefinitions {

    @Given("que el sistema esta disponible para gestionar usuarios")
    public void queElSistemaEstaDisponibleParaGestionarUsuarios() {
        // Implementar: Verificar que la API esté disponible
    }

    @When("creo un nuevo usuario con id {string}, nombre de usuario {string}, nombre {string}, apellido {string}, correo {string}, contrasena {string}, telefono {string} y estado {string}")
    public void creoUnNuevoUsuario(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {
        // Implementar: Crear usuario mediante POST /user
    }

    @And("consulto la informacion del usuario {string}")
    public void consultoLaInformacionDelUsuario(String username) {
        // Implementar: Consultar usuario mediante GET /user/{username}
    }

    @And("actualizo el usuario {string} con nombre {string}, apellido {string}, correo {string} y contrasena {string}")
    public void actualizoElUsuario(String username, String firstName, String lastName, String email, String password) {
        // Implementar: Actualizar usuario mediante PUT /user/{username}
    }

    @And("consulto nuevamente la informacion del usuario {string}")
    public void consultoNuevamenteLaInformacionDelUsuario(String username) {
        // Implementar: Consultar usuario nuevamente mediante GET /user/{username}
    }

    @And("elimino el usuario {string}")
    public void eliminoElUsuario(String username) {
        // Implementar: Eliminar usuario mediante DELETE /user/{username}
    }

    @Then("el usuario es creado, actualizado y eliminado correctamente del sistema")
    public void elUsuarioEsCreadoActualizadoYEliminadoCorrectamente() {
        // Implementar: Verificar que todas las operaciones fueron exitosas
    }
}
