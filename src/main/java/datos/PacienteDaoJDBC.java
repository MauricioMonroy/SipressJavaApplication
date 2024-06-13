package datos;

import domain.Historial;
import domain.Paciente;
import domain.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class PacienteDaoJDBC implements PacienteDAO {
    // Objeto que permite manejar la conexión y las transacciones con la base de datos
    private Connection conexionTransaccional;

    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT px.id_paciente, px.id_persona, px.detalle_eps, px.fecha_consulta, p.id_persona, p.nombre, p.apellido, p.identificacion, "
                    + "p.telefono, p.email, h.id_historial, h.id_paciente, h.motivo_consulta, h.fecha_nacimiento, h.sexo, h.direccion, "
                    + "h.ocupacion, h.contacto_emergencia, h.nombre_contacto_emergencia, h.alergias, h.condiciones_preexistentes, "
                    + "h.medicamentos_actuales, h.historial_vacunas, h.grupo_sanguineo, h.notas_adicionales, h.ultima_actualizacion "
                    + " FROM paciente px INNER JOIN persona p ON px.id_persona = p.id_persona "
                    + "INNER JOIN historial h ON h.id_paciente = px.id_paciente ";
    private static final String SQL_SELECT_ONE =
            "SELECT px.id_paciente, px.id_persona, px.detalle_eps, px.fecha_consulta, p.id_persona, p.nombre, p.apellido, p.identificacion, "
                    + "p.telefono, p.email, h.id_historial, h.id_paciente, h.motivo_consulta, h.fecha_nacimiento, h.sexo, h.direccion, "
                    + "h.ocupacion, h.contacto_emergencia, h.nombre_contacto_emergencia, h.alergias, h.condiciones_preexistentes, "
                    + "h.medicamentos_actuales, h.historial_vacunas, h.grupo_sanguineo, h.notas_adicionales, h.ultima_actualizacion "
                    + "FROM paciente px INNER JOIN persona p ON px.id_persona = p.id_persona "
                    + "INNER JOIN historial h ON h.id_paciente = px.id_paciente "
                    + "WHERE px.id_paciente = ?";

    private static final String SQL_INSERT =
            "INSERT INTO historial (id_paciente, motivo_consulta, fecha_nacimiento, sexo, direccion, ocupacion, contacto_emergencia, "
                    + "nombre_contacto_emergencia, alergias, condiciones_preexistentes, medicamentos_actuales, historial_vacunas, "
                    + "grupo_sanguineo, notas_adicionales, ultima_actualizacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE paciente SET detalleEps = ? WHERE id_paciente = ?";
    private static final String SQL_DELETE =
            "DELETE FROM paciente WHERE id_paciente = ?";

    // Constructores para la conexión transaccional
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

        // Establecer los atributos de Persona en el objeto Paciente
        paciente.setIdPersona(rs.getInt("id_persona"));
        paciente.setNombre(rs.getString("nombre"));
        paciente.setApellido(rs.getString("apellido"));
        paciente.setIdentificacion(rs.getString("identificacion"));
        paciente.setTelefono(rs.getString("telefono"));
        paciente.setEmail(rs.getString("email"));

        return paciente;
    }

    @Override
    public List<Paciente> seleccionar() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Ejecutando query = " + SQL_SELECT);

            while (rs.next()) {
                pacientes.add(mapPaciente(rs));
            }
        }

        return pacientes;
    }

    @Override
    public Paciente seleccionarPorId(int idPaciente) throws SQLException {
        Paciente paciente = null;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ONE)) {

            ps.setInt(1, idPaciente);
            System.out.println("Ejecutando query = " + SQL_SELECT_ONE);

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
            conn.setAutoCommit(false); // Iniciar la transacción

            // Insertar Paciente
            System.out.println("Ejecutando query = " + SQL_INSERT);
            try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, paciente.getDetalleEps());
                ps.setDate(2, new java.sql.Date(paciente.getFechaConsulta().getTime()));
                ps.setInt(3, paciente.getIdPersona());

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idPaciente = rs.getInt(1);
                        paciente.setIdPaciente(idPaciente);

                        // Insertar Historial
                        HistorialDaoJDBC historialDao = new HistorialDaoJDBC(conn);
                        Historial historial = paciente.getHistorial();
                        historial.setPaciente(paciente);
                        historialDao.insertar(historial);
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
            conn.setAutoCommit(false); // Iniciar la transacción

            // Actualizar Paciente
            System.out.println("Ejecutando query = " + SQL_UPDATE);
            try (PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
                ps.setString(1, paciente.getDetalleEps());
                ps.setDate(2, new java.sql.Date(paciente.getFechaConsulta().getTime()));
                ps.setInt(3, paciente.getIdPaciente());
                registros = ps.executeUpdate();
            }

            // Actualizar Historial
            HistorialDaoJDBC historialDao = new HistorialDaoJDBC(conn);
            Historial historial = paciente.getHistorial();
            historial.setPaciente(paciente);
            historialDao.actualizar(historial);

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
