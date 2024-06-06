package test;

import datos.PersonaDAO;
import domain.Persona;

import java.util.List;

public class TestManejoPersonas {
    public static void main(String[] args) {

        // Creación del objeto que permite la ejecución de métodos de la clase DAO
        PersonaDAO personaDAO = new PersonaDAO();

       // Insertar un nuevo objeto de la clase en la base de datos
        Persona personaNueva = new Persona(
                "Catalina",
                "Trejos",
                "1054789369",
                "6044413032",
                "catalina@gmail.com");
        personaDAO.insertar(personaNueva);

        // Modificar un registro existente en la base de datos
        // Se cambia el campo de nombre del registro número 2
        /*Persona personaModificar = new Persona(
                2,
                "María Patricia",
                "Galeano",
                "254598763",
                "3184176592",
                "maria@mail.com");
        personaDAO.actualizar(personaModificar);*/


        // Eliminar un registro de la base de datos
        // Se elimina el registro número 3
        /*personaDAO.eliminar(new Persona(3));*/

        // Listar los registros existentes en la base de datos
        List<Persona> personas = personaDAO.seleccionar();
        personas.forEach(personaList -> {
            System.out.println("Registro: " + personaList);
        });
    }
}
