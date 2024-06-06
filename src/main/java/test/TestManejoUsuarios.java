package test;

import datos.UsuarioDAO;
import domain.Usuario;

import java.util.List;

public class TestManejoUsuarios {

    public static void main(String[] args) {
        // Creación del objeto que permite la ejecución de métodos de la clase DAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Insertar un nuevo objeto de la clase en la base de datos
        Usuario usuarioNuevo = new Usuario("Eliana", "123");
        usuarioDAO.insertar(usuarioNuevo);


        // Modificar un registro existente en la base de datos
        Usuario usuarioModificar = new Usuario(2, "maria.galeano", "456");
        usuarioDAO.actualizar(usuarioModificar);

        // Eliminar un registro de la base de datos
        // Se elimina el registro número 1
        /*usuarioDAO.eliminar(new Usuario(1);*/

        // Listar los registros existentes en la base de datos
        List<Usuario> usuarios = usuarioDAO.seleccionar();
        usuarios.forEach(usuarioList -> {
            System.out.println("Registro: " + usuarioList);
        });
    }
}
