/*
 * DAO hace referencia al patrón de diseño Data Access Object
 * Permite realizar las operaciones de la entidad seleccionada
 */
package datos;

import domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.getConnection;

public class UsuarioDaoJDBC implements UsuarioDAO {

    // Objeto que permite manejar la conexión y las transacciones con la base de datos
    private Connection conexionTransaccional;

    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT u.id_usuario, u.username, u.password, u.tipo_usuario, p.id_persona, p.nombre, p.apellido, p.identificacion, "
                    + "p.telefono, p.email FROM usuario u INNER JOIN persona p ON u.id_persona = p.id_persona";
    private static final String SQL_SELECT_ONE =
            "SELECT u.id_usuario, u.username, u.password, u.tipo_usuario, p.id_persona, p.nombre, p.apellido, p.identificacion, "
                    + "p.telefono, p.email FROM usuario u INNER JOIN persona p ON u.id_persona = p.id_persona "
                    + "WHERE u.id_usuario = ?";
    private static final String SQL_INSERT =
            "INSERT INTO usuario (username, password, tipo_usuario) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE usuario SET username = ?, password = ?, tipo_usuario = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE =
            "DELETE FROM usuario WHERE id_usuario = ?";

    // Constructores para la conexión transaccional
    public UsuarioDaoJDBC() {
    }

    public UsuarioDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    private Persona mapPersona(ResultSet rs, String tipoUsuario) throws SQLException {
        if (tipoUsuario.equals("Empleado")) {
            Empleado empleado = new Empleado();
            empleado.setNombre(rs.getString("nombre"));
            empleado.setApellido(rs.getString("apellido"));
            empleado.setIdentificacion(rs.getString("identificacion"));
            empleado.setTelefono(rs.getString("telefono"));
            empleado.setEmail(rs.getString("email"));
            empleado.setCargo(rs.getString("cargo"));
            return empleado;
        } else if (tipoUsuario.equals("Paciente")) {
            Paciente paciente = new Paciente();
            paciente.setNombre(rs.getString("nombre"));
            paciente.setApellido(rs.getString("apellido"));
            paciente.setIdentificacion(rs.getString("identificacion"));
            paciente.setTelefono(rs.getString("telefono"));
            paciente.setEmail(rs.getString("email"));
            paciente.setDetalleEps(rs.getString("detalle_eps"));
            paciente.setFechaConsulta(rs.getDate("fecha_consulta"));
            return paciente;
        } else {
            throw new IllegalArgumentException("Tipo de persona no soportado");
        }
    }

    // Método que permite seleccionar los objetos de la base de datos (SELECT)
    @Override
    public List<Usuario> seleccionar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));

                String tipoUsuario = rs.getString("tipoUsuario");  // obtener el tipo de Usuario
                usuario.setPersona(mapPersona(rs, tipoUsuario));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }


    // Método para recuperar solo uno de los registros en la base de datos
    @Override
    public Usuario seleccionarPorId(int idUsuario) throws SQLException {
        Usuario usuario = null;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ONE)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setTipoUsuario(rs.getString("tipo_usuario"));

                    usuario.setPersona(mapPersona(rs, usuario.getTipoUsuario()));
                }
            }
        }
        return usuario;
    }


    // Método que permite insertar objetos en la base de datos (INSERT)
    @Override
    public int insertar(Usuario usuario) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection()) {
            conn.setAutoCommit(false);

            System.out.println("Insertando Usuario...");
            try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {
                ps.setString(1, usuario.getUsername());
                ps.setString(2, usuario.getPassword());
                ps.setString(3, usuario.getTipoUsuario());
                ps.setString(4, usuario.getPersona().getNombre());
                ps.setString(5, usuario.getPersona().getApellido());
                ps.setString(6, usuario.getPersona().getIdentificacion());
                ps.setString(7, usuario.getPersona().getTelefono());
                ps.setString(8, usuario.getPersona().getEmail());
                registros = ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idUsuario = rs.getInt(1);
                        usuario.setIdUsuario(idUsuario);
                        PerfilDaoJDBC perfilDao = new PerfilDaoJDBC(conn);
                        Perfil perfil = (Perfil) usuario.getPerfilList();
                        perfil.setUsuario(usuario);
                        perfilDao.insertar(perfil);
                    }
                }
            }

            conn.commit();
            registros = 1;
            System.out.println("Registros insertados = " + registros);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection()) {
                System.out.println("Ejecutando rollback");
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

        return registros;
    }


    // Método que permite actualizar objetos en la base de datos (UPDATE)
    @Override
    public int actualizar(Usuario usuario) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection()) {
            conn.setAutoCommit(false);

            // Actualizar Usuario
            try (PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
                ps.setString(1, usuario.getUsername());
                ps.setString(2, usuario.getPassword());
                ps.setString(3, usuario.getTipoUsuario());
                ps.setInt(4, usuario.getIdUsuario());
                registros = ps.executeUpdate();
            }

            conn.commit();
        } catch (SQLException ex) {
            if (this.conexionTransaccional == null) {
                try (Connection conn = getConnection()) {
                    conn.rollback();
                }
            } else {
                this.conexionTransaccional.rollback();
            }
            throw ex;
        }
        return registros;
    }

    // Método que permite eliminar objetos en la base de datos (DELETE)
    @Override
    public int eliminar(Usuario usuario) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, usuario.getIdUsuario());
            registros = ps.executeUpdate();
        }
        return registros;
    }
}

