package test;

import datos.PerfilDAO;
import datos.PerfilDaoJDBC;
import datos.UsuarioDAO;
import datos.UsuarioDaoJDBC;
import domain.Paciente;
import domain.Persona;
import domain.Usuario;
import service.UsuarioService;

import java.sql.SQLException;

public class ManejoPerfil {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDaoJDBC();
        PerfilDAO perfilDAO = new PerfilDaoJDBC();
        UsuarioService usuarioService = new UsuarioService(usuarioDAO, perfilDAO);

        // Crear una persona y un usuario
        Persona persona = new Paciente().getPersona(); // O Empleado
        persona.setIdPersona(1); // Establecer el ID de la persona

        Usuario usuario = new Usuario();
        usuario.setPersona(persona);

        try {
            // Registrar el usuario
            usuarioService.registrarUsuario(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
