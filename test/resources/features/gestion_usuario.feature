Feature: Gestion completa del ciclo de vida de usuarios en la plataforma
  Como administrador del sistema
  Quiero gestionar usuarios desde su creacion hasta su eliminacion
  Para mantener actualizada la informacion de los usuarios en la plataforma

  Scenario Outline: Crear, consultar, actualizar y eliminar un usuario exitosamente
    Given que el sistema esta disponible para gestionar usuarios
    When creo un nuevo usuario con id "<id>", nombre de usuario "<username>", nombre "<firstName>", apellido "<lastName>", correo "<email>", contrasena "<password>", telefono "<phone>" y estado "<userStatus>"
    And actualizo el usuario "<username>" con nombre "<firstName_actualizado>", apellido "<lastName_actualizado>", correo "<email_actualizado>" y contrasena "<password_actualizado>"
    And elimino el usuario "<username>"
    Then el usuario es creado, actualizado y eliminado correctamente del sistema

    Examples:
      | id | username | firstName | lastName | email                    | password    | phone      | userStatus | firstName_actualizado | lastName_actualizado | email_actualizado            | password_actualizado |
      | 0  | usuario1 | Juan      | Perez    | juan.perez@email.com     | password123 | 1234567890 | 1          | Juan Carlos           | Perez Lopez          | juancarlos.perez@email.com   | newpassword123       |
      | 0  | usuario2 | Maria     | Garcia   | maria.garcia@email.com   | password456 | 0987654321 | 1          | Maria Elena           | Garcia Rodriguez     | mariaelena.garcia@email.com  | newpassword456       |
