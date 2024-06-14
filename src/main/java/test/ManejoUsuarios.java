package test;

import datos.Conexion;
import datos.UsuarioDaoJDBC;
import domain.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public class ManejoUsuarios {

    public static void main(String[] args) {

        // Creación de un objeto de la clase Conexion y llamada de los métodos de la clase DAO
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            UsuarioDaoJDBC usuarioDAO = new UsuarioDaoJDBC(conexion);

            // Insertar un nuevo objeto de la clase en la base de datos
            /*Usuario usuarioNuevo = new Usuario();
            usuarioNuevo.setUsername("marko.aguirre");
            usuarioNuevo.setPassword("321");

            Persona personaNueva = new Persona();
            personaNueva.setNombre("Marko");
            personaNueva.setApellido("Aguirre");
            personaNueva.setIdentificacion("741256933");
            personaNueva.setTelefono("3102015253");
            personaNueva.setEmail("markagui@example.com");

            usuarioNuevo.setPersona(personaNueva);

            usuarioDAO.insertar(usuarioNuevo);*/

            // Actualizar un registro existente en la base de datos
//            Usuario usuarioModificado = new Usuario();
//            usuarioModificado.setIdUsuario(3);
//            usuarioModificado.setUsername("cata.trejos");
//            usuarioModificado.setPassword("789");
//
//            Persona personaModificada = new Persona();
//            personaModificada.setIdPersona(3);
//            personaModificada.setNombre("Cata");
//            personaModificada.setApellido("Trejos");
//            personaModificada.setIdentificacion("987654321");
//            personaModificada.setTelefono("0987654321");
//            personaModificada.setEmail("cata.trejos@example.com");
//
//            usuarioModificado.setPersona(personaModificada);
//
//            usuarioDAO.actualizar(usuarioModificado);

            // Eliminar un registro de la base de datos
            //usuarioDAO.eliminar(new Usuario(1));

            // Commit de la transacción
            conexion.commit();
            System.out.println("Se ha hecho el commit de la transacción");

//            // Listar los registros existentes en la base de datos
//            List<Usuario> usuarios = usuarioDAO.seleccionar();
//            for (int i = 0; i < usuarios.size(); i++) {
//                System.out.println("Registro " + (i + 1) + ": " + usuarios.get(i));
//            }

            // Obtener y mostrar un solo registro
            Usuario usuario = usuarioDAO.seleccionarPorId(3);
            System.out.println("Usuario: " + usuario);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Se llama al método rollback");
            try {
                assert conexion != null;
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
