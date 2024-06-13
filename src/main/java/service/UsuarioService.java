package service;

import datos.PerfilDAO;
import datos.UsuarioDAO;
import domain.Usuario;
import domain.Empleado;
import domain.Paciente;
import domain.Perfil;
import domain.Rol;

import java.sql.SQLException;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;
    private final PerfilDAO perfilDAO;

    public UsuarioService(UsuarioDAO usuarioDAO, PerfilDAO perfilDAO) {
        this.usuarioDAO = usuarioDAO;
        this.perfilDAO = perfilDAO;
    }

    public void registrarUsuario(Usuario usuario) throws SQLException {
        // Guardar el usuario en la base de datos
        usuarioDAO.insertar(usuario);

        // Determinar el rol basado en el tipo de persona
        Rol rol;
        if (usuario.getPersona() instanceof Paciente) {
            rol = Rol.USER;
        } else if (usuario.getPersona() instanceof Empleado) {
            rol = Rol.ADMIN;
        } else {
            throw new IllegalArgumentException("Tipo de persona no soportado");
        }

        // Crear el perfil asociado con el rol correspondiente
        Perfil perfil = new Perfil();
        perfil.setUsuario(usuario);
        perfil.setRol(rol);
        perfilDAO.insertar(perfil);
    }
}


