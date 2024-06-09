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
            "INSERT INTO usuario (username, password) VALUES (?, ?)";
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
    public List<Usuario> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_SELECT);
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            // Iteración de los elementos para obtener todos los registros
            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String identificacion = rs.getString("identificacion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                // Creación de un nuevo objeto de la clase o clases
                var usuario = new Usuario();
                usuario.setIdUsuario(idUsuario);
                usuario.setUsername(username);
                usuario.setPassword(password);

                var persona = new Persona();
                persona.setIdPersona(idPersona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setIdentificacion(identificacion);
                persona.setTelefono(telefono);
                persona.setEmail(email);

                usuario.setPersona(persona);
                usuarios.add(usuario);
            }
        }
        // Se ejecuta el bloque finally para cerrar los objetos creados
        finally {
            close(rs);
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }

        return usuarios;
    }

    // Método para recuperar solo uno de los registros en la base de datos
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

            // Si se encontró un registro, crear el objeto Usuario
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                var persona = new Persona();
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setIdentificacion(rs.getString("identificacion"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setEmail(rs.getString("email"));

                usuario.setPersona(persona);
            }
        }
        // Se ejecuta el bloque finally para cerrar los objetos creados
        finally {
            if (rs != null) {
                close(rs);
            }
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }

        return usuario;
    }


    // Método que permite insertar objetos en la base de datos (INSERT)
    public int insertar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_INSERT);
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());

            registros = ps.executeUpdate();
            System.out.println("Registros insertados = " + registros);
        }
        // Se ejecuta el bloque finally para cerrar la conexión
        finally {
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }

    // Método que permite actualizar objetos en la base de datos (UPDATE)
    public int actualizar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_UPDATE);
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getIdUsuario());

            registros = ps.executeUpdate();
            System.out.println("Registros actualizados = " + registros);
        }
        // Se ejecuta el bloque finally para cerrar la conexión
        finally {
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }

    // Método que permite eliminar objetos en la base de datos (DELETE)
    public int eliminar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_DELETE);
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getIdUsuario());

            registros = ps.executeUpdate();
            System.out.println("Registros eliminados = " + registros);
        }
        // Se ejecuta el bloque finally para cerrar la conexión
        finally {
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }
}
