/*
 * DAO hace referencia al patrón de diseño Data Access Object
 * Permite realizar las operaciones de la entidad seleccionada
 */
package datos;

import domain.Empleado;
import domain.Paciente;
import domain.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class UsuarioDaoJDBC implements UsuarioDAO {

    // Objeto que permite manejar la conexión y las transacciones con la base de datos
    private Connection conexionTransaccional;

    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT u.id_usuario, u.username, u.password, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_paciente, u.es_empleado, e.id_empleado, e.cargo, "
                    + "pac.id_paciente, pac.detalle_eps, pac.fecha_consulta "
                    + "FROM usuario u "
                    + "LEFT JOIN empleado e ON u.id_usuario = e.id_usuario "
                    + "LEFT JOIN paciente pac ON u.id_usuario = pac.id_usuario";
    private static final String SQL_SELECT_ONE =
            "SELECT u.id_usuario, u.username, u.password, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_paciente, u.es_empleado, e.id_empleado, e.cargo, "
                    + "pac.id_paciente, pac.detalle_eps, pac.fecha_consulta "
                    + "FROM usuario u "
                    + "LEFT JOIN empleado e ON u.id_usuario = e.id_usuario "
                    + "LEFT JOIN paciente pac ON u.id_usuario = pac.id_usuario "
                    + "WHERE u.id_usuario = ?";
    private static final String SQL_INSERT =
            "INSERT INTO usuario(username, password, nombre, apellido, identificacion, telefono, email, "
                    + "es_paciente, es_empleado) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET username = ?, password = ?, nombre = ?, "
            + "apellido = ?, identificacion = ?, telefono = ?, email = ?, es_paciente = ?, es_empleado = ? "
            + "WHERE id_usuario = ?";
    private static final String SQL_DELETE =
            "DELETE FROM usuario WHERE id_usuario = ?";

    // Constructores para la conexión transaccional
    public UsuarioDaoJDBC() {
    }

    public UsuarioDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    private Usuario mapUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setIdentificacion(rs.getString("identificacion"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setEmail(rs.getString("email"));
        usuario.setEsPaciente(rs.getBoolean("es_paciente"));
        usuario.setEsEmpleado(rs.getBoolean("es_empleado"));

        return usuario;
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
            System.out.println("Ejecutando query SELECT");
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = mapUsuario(rs);
                usuarios.add(usuario);
            }
        } finally {
            close(rs);
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
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
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("Ejecutando query SELECT_ONE");
            ps = conn.prepareStatement(SQL_SELECT_ONE);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = mapUsuario(rs);
            }
        } finally {
            close(rs);
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return usuario;
    }

    // Método que permite insertar objetos en la base de datos (INSERT)
    @Override
    public int insertar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("Ejecutando query SELECT_INSERT");
            ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            ps.setString(5, usuario.getIdentificacion());
            ps.setString(6, usuario.getTelefono());
            ps.setString(7, usuario.getEmail());
            ps.setBoolean(8, usuario.getEsPaciente());
            ps.setBoolean(9, usuario.getEsEmpleado());
            registros = ps.executeUpdate();

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
        int registros;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("Ejecutando query SELECT_UPDATE");
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            ps.setString(5, usuario.getIdentificacion());
            ps.setString(6, usuario.getTelefono());
            ps.setString(7, usuario.getEmail());
            ps.setBoolean(8, usuario.getEsPaciente());
            ps.setBoolean(9, usuario.getEsEmpleado());
            ps.setInt(10, usuario.getIdUsuario());
            registros = ps.executeUpdate();

        } finally {
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
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
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("Ejecutando query SELECT_DELETE");
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getIdUsuario());
            registros = ps.executeUpdate();
        } finally {
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }

    // Método para buscar un registro en la base de datos
    public List<Usuario> buscar(String query) throws SQLException {
        String SQL_SELECT_BUSCAR = "SELECT * FROM usuario WHERE username LIKE ? OR nombre LIKE ? OR apellido LIKE ?";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BUSCAR)) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            stmt.setString(3, "%" + query + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idUsuario = rs.getInt("idUsuario");
                    String username = rs.getString("username");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String identificacion = rs.getString("identificacion");
                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");
                    boolean esPaciente = rs.getBoolean("esPaciente");
                    boolean esEmpleado = rs.getBoolean("esEmpleado");

                    Usuario usuario = new Usuario(idUsuario, username, nombre, apellido, identificacion, telefono, email, esPaciente, esEmpleado);
                    usuarios.add(usuario);
                }
            }
        }
        return usuarios;
    }
}

