package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ManejoPersonas {
    public static void main(String[] args) {

        // Creación de un objeto de la clase Conexion y llamada de los métodos de la clase DAO
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            PersonaDAO personaDAO = new PersonaDAO(conexion);

            // Actualizar un registro en la base de datos
            Persona personaModificada = new Persona();
            personaModificada.setIdPersona(1);
            personaModificada.setNombre("Carlos Alberto");
            personaModificada.setApellido("Calle Contreras");
            personaModificada.setIdentificacion("3141592654");
            personaModificada.setTelefono("6063218277");
            personaModificada.setEmail("cabeto@gmail.com");
            personaDAO.actualizar(personaModificada);

            // Insertar un registro en la base de datos
            /*Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Julia");
            nuevaPersona.setApellido("Cuartas");
            nuevaPersona.setIdentificacion("3141592654");
            nuevaPersona.setTelefono("6012589874");
            nuevaPersona.setEmail("julia@gmail.com");
            personaDAO.insertar(nuevaPersona);*/

            // Eliminar un registro de la base de datos
            /*personaDAO.eliminar(new Persona(3));*/

            // Commit de la transacción
            conexion.commit();
            System.out.println("Se ha hecho el commit de la transacción");

            // Listar los registros existentes en la base de datos
            List<Persona> personas = personaDAO.seleccionar();
            personas.forEach(personaList -> {
                System.out.println("Registros: " + personaList);
            });

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Se llama al método rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
