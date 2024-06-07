package test;

import datos.Conexion;
import datos.PersonaDAO;
import datos.PersonaDaoJDBC;
import domain.PersonaDTO;

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
            PersonaDAO persona = new PersonaDaoJDBC(conexion);

            // Actualizar un registro en la base de datos
            /*PersonaDTO personaModificada = new PersonaDTO();
            personaModificada.setIdPersona(1);
            personaModificada.setNombre("Carlos Alberto");
            personaModificada.setApellido("Calle Contreras");
            personaModificada.setIdentificacion("3141592654");
            personaModificada.setTelefono("6063218277");
            personaModificada.setEmail("cabeto@gmail.com");
            persona.actualizar(personaModificada);*/

            // Insertar un registro en la base de datos
            /*PersonaDTO nuevaPersona = new PersonaDTO();
            nuevaPersona.setNombre("Julia");
            nuevaPersona.setApellido("Cuartas");
            nuevaPersona.setIdentificacion("3141592654");
            nuevaPersona.setTelefono("6012589874");
            nuevaPersona.setEmail("julia@gmail.com");
            persona.insertar(nuevaPersona);*/

            // Eliminar un registro de la base de datos
            /*persona.eliminar(new PersonaDTO(3));*/

            // Commit de la transacción
            conexion.commit();
            System.out.println("Se ha hecho el commit de la transacción");

            // Listar los registros existentes en la base de datos
            List<PersonaDTO> personas = persona.seleccionar();
            personas.forEach(personasLista -> {
                System.out.println("Registros: " + personasLista);
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
