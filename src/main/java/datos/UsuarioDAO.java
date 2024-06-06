/*
 * DAO hace referencia al patrón de diseño Data Access Object
 * Permite realizar las operaciones de la entidad seleccionada
 */
package datos;

import domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class UsuarioDAO {
    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT id_usuario, username, password FROM usuario";
    private static final String SQL_INSERT =
            "INSERT INTO usuario (username, password) VALUES (?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE usuario SET username = ?, password = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE =
            "DELETE FROM usuario WHERE id_usuario = ?";

    // Método que permite seleccionar los objetos (SELECT)
    public List<Usuario> seleccionar() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = getConnection();
            System.out.println("Ejecutando query = " + SQL_SELECT);
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            // Iteración de los elementos para obtener todos los registros
            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String username = rs.getString("username");
                String password = rs.getString("password");

                // Creación de un nuevo objeto de la clase
                var usuario = new Usuario();
                usuario.setIdUsuario(idUsuario);
                usuario.setUsername(username);
                usuario.setPassword(password);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        // Se ejecuta el bloque finally para cerrar los objetos creados
        finally {
            close(rs);
            close(ps);
            close(conn);
        }

        return usuarios;
    }

    // Método que permite insertar objetos en la base de datos
    public int insertar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            System.out.println("Ejecutando query = " + SQL_INSERT);
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());

            registros = ps.executeUpdate();
            System.out.println("Registros insertados = " + registros);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            close(ps);
            close(conn);
        }
        return registros;
    }

    // Método que permite actualizar objetos en la base de datos
    public int actualizar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            System.out.println("Ejecutando query = " + SQL_UPDATE);
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getIdUsuario());

            registros = ps.executeUpdate();
            System.out.println("Registros actualizados = " + registros);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            close(ps);
            close(conn);
        }
        return registros;
    }

    // Método que permite eliminar objetos en la base de datos
    public int eliminar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            System.out.println("Ejecutando query = " + SQL_DELETE);
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getIdUsuario());

            registros = ps.executeUpdate();
            System.out.println("Registros eliminados = " + registros);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            close(ps);
            close(conn);
        }
        return registros;
    }
}
