/*
 * DAO hace referencia al patrón de diseño Data Access Object
 * Permite realizar las operaciones de la entidad seleccionada
 */
package datos;

import domain.Persona;
import domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class UsuarioDaoJDBC implements UsuarioDAO {

    // Objeto que permite manejar la conexión y las transacciones con la base de datos
    private Connection conexionTransaccional;

    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT u.id_usuario, u.username, u.password, p.id_persona, p.nombre, p.apellido, p.identificacion, "
                    + "p.telefono, p.email FROM usuario u INNER JOIN persona p ON u.id_persona = p.id_persona";
    private static final String SQL_SELECT_ONE =
            "SELECT u.id_usuario, u.username, u.password, p.id_persona, p.nombre, p.apellido, p.identificacion, "
                    + "p.telefono, p.email FROM usuario u INNER JOIN persona p ON u.id_persona = p.id_persona "
                    + "WHERE u.id_usuario = ?";
    private static final String SQL_INSERT =
            "INSERT INTO usuario (username, password, id_persona) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE usuario SET username = ?, password = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE =
            "DELETE FROM usuario WHERE id_usuario = ?";

    // Constructores para la conexión transaccional
    public UsuarioDaoJDBC() {
    }

    public UsuarioDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    // Método que permite seleccionar los objetos de la base de datos (SELECT)
    @Override
    public List<Usuario> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));

                Persona persona = new Persona();
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setIdentificacion(rs.getString("identificacion"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setEmail(rs.getString("email"));

                usuario.setPersona(persona);
                usuarios.add(usuario);
            }
        } finally {
            if (rs != null) close(rs);
            if (ps != null) close(ps);
            if (this.conexionTransaccional == null) close(conn);
        }
        return usuarios;
    }

    // Método para recuperar solo uno de los registros en la base de datos
    @Override
    public Usuario seleccionarPorId(int idUsuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_SELECT_ONE);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));

                Persona persona = new Persona();
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setIdentificacion(rs.getString("identificacion"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setEmail(rs.getString("email"));

                usuario.setPersona(persona);
            }
        } finally {
            if (rs != null) close(rs);
            if (ps != null) close(ps);
            if (this.conexionTransaccional == null) close(conn);
        }
        return usuario;
    }


    // Método que permite insertar objetos en la base de datos (INSERT)
    @Override
    public int insertar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Insertando Persona...");

            PersonaDaoJDBC personaDAO = new PersonaDaoJDBC(conn);
            int idPersona = personaDAO.insertar(usuario.getPersona());
            usuario.getPersona().setIdPersona(idPersona);

            System.out.println("Insertando Usuario...");
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, idPersona);

            registros = ps.executeUpdate();
            System.out.println("Registros insertados = " + registros);
        } finally {
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }


    // Método que permite actualizar objetos en la base de datos (UPDATE)
    @Override
    public int actualizar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            conn.setAutoCommit(false);

            // Actualizar Persona
            PersonaDaoJDBC personaDao = new PersonaDaoJDBC(conn);
            personaDao.actualizar(usuario.getPersona());

            // Actualizar Usuario
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getIdUsuario());
            registros = ps.executeUpdate();

            conn.commit();
        } catch (SQLException ex) {
            if (conn != null) {
                conn.rollback();
            }
            throw ex;
        } finally {
            if (ps != null) close(ps);
            if (this.conexionTransaccional == null && conn != null) close(conn);
        }
        return registros;
    }

    // Método que permite eliminar objetos en la base de datos (DELETE)
    @Override
    public int eliminar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getIdUsuario());
            registros = ps.executeUpdate();
        } finally {
            if (ps != null) close(ps);
            if (this.conexionTransaccional == null) close(conn);
        }
        return registros;
    }
}
