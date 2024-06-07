package test;

import datos.Conexion;
import datos.UsuarioDaoJDBC;
import domain.UsuarioDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
            /*UsuarioDTO usuarioNuevo = new UsuarioDTO();
            usuarioNuevo.setUsername("julia.cuartas");
            usuarioNuevo.setPassword("456");
            usuarioDAO.insertar(usuarioNuevo);*/


            // Actualizar un registro existente en la base de datos
            /*UsuarioDTO usuarioModificado = new UsuarioDTO();
            usuarioModificado.setIdUsuario(3);
            usuarioModificado.setUsername("cata.trejos");
            usuarioModificado.setPassword("789");
            usuarioDAO.actualizar(usuarioModificado);*/

            // Eliminar un registro de la base de datos
            /*usuarioDAO.eliminar(new UsuarioDTO(1));*/

            // Commit de la transacción
            conexion.commit();
            System.out.println("Se ha hecho el commit de la transacción");

            // Listar los registros existentes en la base de datos
            List<UsuarioDTO> usuarios = usuarioDAO.seleccionar();
            usuarios.forEach(usuarioList -> {
                System.out.println("Registros: " + usuarioList);
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
