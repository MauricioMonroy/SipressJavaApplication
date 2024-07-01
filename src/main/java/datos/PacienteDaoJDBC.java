package datos;

import domain.Paciente;
import domain.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.getConnection;

public class PacienteDaoJDBC implements PacienteDAO {
    private Connection conexionTransaccional;

    private static final String SQL_SELECT =
            "SELECT pac.id_paciente, pac.detalle_eps, pac.fecha_consulta, "
                    + "u.id_usuario, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_paciente, u.es_empleado "
                    + "FROM paciente pac " + "INNER JOIN usuario u ON pac.id_usuario = u.id_usuario";
    private static final String SQL_SELECT_ONE =
            "SELECT pac.id_paciente, pac.detalle_eps, pac.fecha_consulta, "
                    + "u.id_usuario, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_paciente, u.es_empleado "
                    + "FROM paciente pac " + "INNER JOIN usuario u ON pac.id_usuario = u.id_usuario "
                    + "WHERE pac.id_paciente = ?";
    private static final String SQL_INSERT_USUARIO =
            "INSERT INTO usuario (username, password, nombre, apellido, identificacion, telefono, " +
                    "email, es_paciente, es_empleado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_PACIENTE =
            "INSERT INTO paciente (detalle_eps, fecha_consulta, id_usuario) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_USUARIO =
            "UPDATE usuario SET nombre = ?, apellido = ?, identificacion = ?, telefono = ?, " +
                    "email = ? WHERE id_usuario = ?";
    private static final String SQL_UPDATE_PACIENTE =
            "UPDATE paciente SET detalle_eps = ?, fecha_consulta = ? WHERE id_paciente = ?";
    private static final String SQL_DELETE =
            "DELETE FROM paciente WHERE id_paciente = ?";

    public PacienteDaoJDBC() {
    }

    public PacienteDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    private Paciente mapPaciente(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(rs.getInt("id_paciente"));
        paciente.setDetalleEps(rs.getString("detalle_eps"));
        paciente.setFechaConsulta(rs.getDate("fecha_consulta"));

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setIdentificacion(rs.getString("identificacion"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setEmail(rs.getString("email"));
        usuario.setEsPaciente(rs.getBoolean("es_paciente"));
        usuario.setEsEmpleado(rs.getBoolean("es_empleado"));

        paciente.setUsuario(usuario);

        return paciente;
    }

    @Override
    public List<Paciente> seleccionar() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional :
                getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Ejecutando query SELECT ");

            while (rs.next()) {
                pacientes.add(mapPaciente(rs));
            }
        }

        return pacientes;
    }

    @Override
    public Paciente seleccionarPorId(int idPaciente) throws SQLException {
        Paciente paciente = null;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional :
                getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ONE)) {

            ps.setInt(1, idPaciente);
            System.out.println("Ejecutando query... ");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    paciente = mapPaciente(rs);
                }
            }
        }

        return paciente;
    }

    @Override
    public int insertar(Paciente paciente) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psUsuario = conn.prepareStatement(
                    SQL_INSERT_USUARIO, Statement.RETURN_GENERATED_KEYS)) {
                Usuario usuario = paciente.getUsuario();
                psUsuario.setString(1, usuario.getUsername());
                psUsuario.setString(2, usuario.getPassword());
                psUsuario.setString(3, usuario.getNombre());
                psUsuario.setString(4, usuario.getApellido());
                psUsuario.setString(5, usuario.getIdentificacion());
                psUsuario.setString(6, usuario.getTelefono());
                psUsuario.setString(7, usuario.getEmail());
                psUsuario.setBoolean(8, usuario.getEsPaciente());
                psUsuario.setBoolean(9, usuario.getEsEmpleado());

                psUsuario.executeUpdate();

                try (ResultSet rsUsuario = psUsuario.getGeneratedKeys()) {
                    if (rsUsuario.next()) {
                        int idUsuario = rsUsuario.getInt(1);
                        usuario.setIdUsuario(idUsuario);
                        paciente.setIdUsuario(idUsuario);
                    }
                }
            }

            try (PreparedStatement psPaciente = conn.prepareStatement(
                    SQL_INSERT_PACIENTE, Statement.RETURN_GENERATED_KEYS)) {
                psPaciente.setString(1, paciente.getDetalleEps());
                psPaciente.setDate(2, new java.sql.Date(paciente.getFechaConsulta().getTime()));
                psPaciente.setInt(3, paciente.getIdUsuario());

                psPaciente.executeUpdate();

                try (ResultSet rsPaciente = psPaciente.getGeneratedKeys()) {
                    if (rsPaciente.next()) {
                        int idPaciente = rsPaciente.getInt(1);
                        paciente.setIdPaciente(idPaciente);
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

    @Override
    public int actualizar(Paciente paciente) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psUsuario = conn.prepareStatement(SQL_UPDATE_USUARIO)) {
                Usuario usuario = paciente.getUsuario();
                psUsuario.setString(1, usuario.getNombre());
                psUsuario.setString(2, usuario.getApellido());
                psUsuario.setString(3, usuario.getIdentificacion());
                psUsuario.setString(4, usuario.getTelefono());
                psUsuario.setString(5, usuario.getEmail());
                psUsuario.setInt(6, usuario.getIdUsuario());
                registros += psUsuario.executeUpdate();
            }

            try (PreparedStatement psPaciente = conn.prepareStatement(SQL_UPDATE_PACIENTE)) {
                psPaciente.setString(1, paciente.getDetalleEps());
                psPaciente.setDate(2, new java.sql.Date(paciente.getFechaConsulta().getTime()));
                psPaciente.setInt(3, paciente.getIdPaciente());
                registros += psPaciente.executeUpdate();
            }

            conn.commit();
            System.out.println("Registros actualizados = " + registros);
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

    @Override
    public int eliminar(Paciente paciente) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            System.out.println("Ejecutando query = " + SQL_DELETE);
            ps.setInt(1, paciente.getIdPaciente());

            registros = ps.executeUpdate();
            System.out.println("Registros eliminados = " + registros);
        }

        return registros;
    }

}

