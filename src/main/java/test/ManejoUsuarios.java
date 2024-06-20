package test;

import datos.Conexion;
import datos.UsuarioDaoJDBC;
import domain.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ManejoUsuarios {

    public static void main(String[] args) {

        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            UsuarioDaoJDBC usuarioDAO = new UsuarioDaoJDBC(conexion);

            // Inserciones, actualizaciones o eliminaciones

            // Crear un nuevo objeto Usuario
//            Usuario usuarioNuevo = new Usuario(
//                    "pedro.rulfo",
//                    "987",
//                    "Pedro",
//                    "Rulfo",
//                    "47663124",
//                    "3145962544",
//                    "pedrulfo@mail.com",
//                    true, // esPaciente
//                    false  // esEmpleado
//            );
            // Insertar el nuevo usuario
//            usuarioDAO.insertar(usuarioNuevo);

            // Commit de la transacción
            conexion.commit();
            System.out.println("Se ha hecho el commit de la transacción");

            // Listar los registros existentes en la base de datos
//            List<Usuario> usuarios = usuarioDAO.seleccionar();
//            for (int i = 0; i < usuarios.size(); i++) {
//                System.out.println("Registro " + (i + 1) + ": " + usuarios.get(i));
//            }

            // Obtener y mostrar un solo registro
            Usuario usuario = usuarioDAO.seleccionarPorId(4);
            System.out.println("Usuario: " + usuario);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Se llama al método rollback");
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        } finally {
            Conexion.close(conexion);
        }
    }
}

