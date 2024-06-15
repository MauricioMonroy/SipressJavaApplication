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

        if (usuario.getEsEmpleado()) {
            Empleado empleado = new Empleado();
            empleado.setIdEmpleado(rs.getInt("id_empleado"));
            empleado.setCargo(rs.getString("cargo"));
            empleado.setUsuario(usuario);
            usuario.setEmpleado(empleado);
        }

        if (usuario.getEsPaciente()) {
            Paciente paciente = new Paciente();
            paciente.setIdPaciente(rs.getInt("id_paciente"));
            paciente.setDetalleEps(rs.getString("detalle_eps"));
            paciente.setFechaConsulta(rs.getDate("fecha_consulta"));
            paciente.setUsuario(usuario);
            usuario.setPaciente(paciente);
        }

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
            Conexion.close(rs);
            Conexion.close(ps);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return usuario;
    }

    // Método que permite insertar objetos en la base de datos (INSERT)
    @Override
    public int insertar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
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
            close(rs);
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
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
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

            if (usuario.getEmpleado() != null) {
                EmpleadoDaoJDBC empleadoDao = new EmpleadoDaoJDBC(conn);
                usuario.getEmpleado().setUsuario(usuario);
                empleadoDao.actualizar(usuario.getEmpleado());
            }

            if (usuario.getPaciente() != null) {
                PacienteDaoJDBC pacienteDao = new PacienteDaoJDBC(conn);
                usuario.getPaciente().setUsuario(usuario);
                pacienteDao.actualizar(usuario.getPaciente());
            }

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
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getIdUsuario());
            registros = ps.executeUpdate();

            if (usuario.getEmpleado() != null) {
                EmpleadoDaoJDBC empleadoDao = new EmpleadoDaoJDBC(conn);
                empleadoDao.eliminar(usuario.getEmpleado());
            }

            if (usuario.getPaciente() != null) {
                PacienteDaoJDBC pacienteDao = new PacienteDaoJDBC(conn);
                pacienteDao.eliminar(usuario.getPaciente());
            }

        } finally {
            Conexion.close(ps);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return registros;
    }
}

