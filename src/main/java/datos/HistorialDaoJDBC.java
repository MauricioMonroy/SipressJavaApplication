package datos;

import domain.Historial;
import domain.Paciente;
import domain.Paciente;
import domain.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.getConnection;

public class HistorialDaoJDBC implements HistorialDAO {
    // Objeto que permite manejar la conexión y las transacciones con la base de datos
    private Connection conexionTransaccional;

    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT h.id_historial, h.id_paciente, h.motivo_consulta, h.fecha_nacimiento, h.sexo, h.direccion, "
                    + "h.ocupacion, h.contacto_emergencia, h.nombre_contacto_emergencia, h.alergias, h.condiciones_preexistentes, "
                    + "h.medicamentos_actuales, h.historial_vacunas, h.grupo_sanguineo, h.notas_adicionales, h.ultima_actualizacion, "
                    + "px.id_paciente, px.id_persona, px.detalle_eps, px.fecha_consulta, p.id_persona, p.nombre, p.apellido, "
                    + "p.identificacion, p.telefono, p.email "
                    + "FROM historial h "
                    + "INNER JOIN paciente px ON h.id_paciente = px.id_paciente "
                    + "INNER JOIN persona p ON px.id_persona = p.id_persona";

    private static final String SQL_SELECT_ONE =
            "SELECT h.id_historial, h.id_paciente, h.motivo_consulta, h.fecha_nacimiento, h.sexo, h.direccion, "
                    + "h.ocupacion, h.contacto_emergencia, h.nombre_contacto_emergencia, h.alergias, h.condiciones_preexistentes, "
                    + "h.medicamentos_actuales, h.historial_vacunas, h.grupo_sanguineo, h.notas_adicionales, h.ultima_actualizacion, "
                    + "px.id_paciente, px.id_persona, px.detalle_eps, px.fecha_consulta, p.id_persona, p.nombre, p.apellido, "
                    + "p.identificacion, p.telefono, p.email "
                    + "FROM historial h "
                    + "INNER JOIN paciente px ON h.id_paciente = px.id_paciente "
                    + "INNER JOIN persona p ON px.id_persona = p.id_persona "
                    + "WHERE h.id_historial = ?";

    private static final String SQL_INSERT =
            "INSERT INTO historial (id_paciente, motivo_consulta, fecha_nacimiento, sexo, direccion, ocupacion, contacto_emergencia, "
                    + "nombre_contacto_emergencia, alergias, condiciones_preexistentes, medicamentos_actuales, historial_vacunas, grupo_sanguineo, "
                    + "notas_adicionales, ultima_actualizacion) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_PERSONA =
            "UPDATE persona SET nombre = ?, apellido = ?, identificacion = ?, telefono = ?, email = ? WHERE id_persona = ?";
    private static final String SQL_UPDATE_PACIENTE =
            "UPDATE paciente SET detalle_eps = ?, fecha_consulta = ? WHERE id_paciente = ?";

    private static final String SQL_UPDATE_HISTORIAL =
            "UPDATE historial SET motivo_consulta = ?, fecha_nacimiento = ?, sexo = ?, direccion = ?, ocupacion = ?, "
                    + "contacto_emergencia = ?, nombre_contacto_emergencia = ?, alergias = ?, condiciones_preexistentes = ?, "
                    + "medicamentos_actuales = ?, historial_vacunas = ?, grupo_sanguineo = ?, notas_adicionales = ?, ultima_actualizacion = ? "
                    + "WHERE id_historial = ?";

    private static final String SQL_DELETE =
            "DELETE FROM historial WHERE id_historial = ?";

    // Constructores para la conexión transaccional
    public HistorialDaoJDBC() {
    }

    public HistorialDaoJDBC(Connection conexionTransaccional) {
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

    private Historial mapHistorial(ResultSet rs) throws SQLException {
        Historial historial = new Historial();
        historial.setIdHistorial(rs.getInt("id_historial"));
        historial.setMotivoConsulta(rs.getString("motivo_consulta"));
        historial.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        historial.setSexo(rs.getString("sexo"));
        historial.setDireccion(rs.getString("direccion"));
        historial.setOcupacion(rs.getString("ocupacion"));
        historial.setContactoEmergencia(rs.getString("contacto_emergencia"));
        historial.setNombreContactoEmergencia(rs.getString("nombre_contacto_emergencia"));
        historial.setAlergias(rs.getString("alergias"));
        historial.setCondicionesPreexistentes(rs.getString("condiciones_preexistentes"));
        historial.setMedicamentosActuales(rs.getString("medicamentos_actuales"));
        historial.setHistorialVacunas(rs.getString("historial_vacunas"));
        historial.setGrupoSanguineo(rs.getString("grupo_sanguineo"));
        historial.setNotasAdicionales(rs.getString("notas_adicionales"));
        historial.setUltimaActualizacion(rs.getTimestamp("ultima_actualizacion"));
        historial.setPaciente(mapPaciente(rs));
        return historial;
    }

    @Override
    public List<Historial> seleccionar() throws SQLException {
        List<Historial> historiales = new ArrayList<>();

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Ejecutando query = " + SQL_SELECT);

            while (rs.next()) {
                historiales.add(mapHistorial(rs));
            }
        }

        return historiales;
    }

    @Override
    public Historial seleccionarPorId(int idHistorial) throws SQLException {
        Historial historial = null;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ONE)) {

            ps.setInt(1, idHistorial);
            System.out.println("Ejecutando query = " + SQL_SELECT_ONE);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    historial = mapHistorial(rs);
                }
            }
        }

        return historial;
    }

    @Override
    public int insertar(Historial historial) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, historial.getPaciente().getIdPaciente());
            ps.setString(2, historial.getMotivoConsulta());
            ps.setDate(3, new java.sql.Date(historial.getFechaNacimiento().getTime()));
            ps.setString(4, historial.getSexo());
            ps.setString(5, historial.getDireccion());
            ps.setString(6, historial.getOcupacion());
            ps.setString(7, historial.getContactoEmergencia());
            ps.setString(8, historial.getNombreContactoEmergencia());
            ps.setString(9, historial.getAlergias());
            ps.setString(10, historial.getCondicionesPreexistentes());
            ps.setString(11, historial.getMedicamentosActuales());
            ps.setString(12, historial.getHistorialVacunas());
            ps.setString(13, historial.getGrupoSanguineo());
            ps.setString(14, historial.getNotasAdicionales());
            ps.setTimestamp(15, new java.sql.Timestamp(historial.getUltimaActualizacion().getTime()));

            registros = ps.executeUpdate();
            System.out.println("Registros insertados = " + registros);

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    historial.setIdHistorial(generatedKeys.getInt(1));
                    PacienteDaoJDBC pacienteDao = new PacienteDaoJDBC(conn);
                    Paciente paciente = historial.getPaciente();
                    pacienteDao.insertar(paciente);
                }
            }
        }

        return registros;
    }

    @Override
    public int actualizar(Historial historial) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection()) {
            conn.setAutoCommit(false);  // Empezar transacción

            // Actualizar tabla paciente
            System.out.println("Ejecutando query = " + SQL_UPDATE_PACIENTE);
            try (PreparedStatement psPaciente = conn.prepareStatement(SQL_UPDATE_PACIENTE)) {
                Paciente paciente = historial.getPaciente();
                psPaciente.setString(1, paciente.getDetalleEps());
                psPaciente.setDate(2, new java.sql.Date(paciente.getFechaConsulta().getTime()));
                psPaciente.setInt(3, paciente.getIdPaciente());
                psPaciente.setInt(4, paciente.getIdPersona());
                psPaciente.setString(5, paciente.getNombre());
                psPaciente.setString(6, paciente.getApellido());
                psPaciente.setString(7, paciente.getIdentificacion());
                psPaciente.setString(8, paciente.getEmail());
                psPaciente.setString(9, paciente.getTelefono());
                registros += psPaciente.executeUpdate();
            }

            // Actualizar tabla historial
            System.out.println("Ejecutando query = " + SQL_UPDATE_HISTORIAL);
            try (PreparedStatement psHistorial = conn.prepareStatement(SQL_UPDATE_HISTORIAL)) {
                psHistorial.setString(1, historial.getMotivoConsulta());
                psHistorial.setDate(2, new java.sql.Date(historial.getFechaNacimiento().getTime()));
                psHistorial.setString(3, historial.getSexo());
                psHistorial.setString(4, historial.getDireccion());
                psHistorial.setString(5, historial.getOcupacion());
                psHistorial.setString(6, historial.getContactoEmergencia());
                psHistorial.setString(7, historial.getNombreContactoEmergencia());
                psHistorial.setString(8, historial.getAlergias());
                psHistorial.setString(9, historial.getCondicionesPreexistentes());
                psHistorial.setString(10, historial.getMedicamentosActuales());
                psHistorial.setString(11, historial.getHistorialVacunas());
                psHistorial.setString(12, historial.getGrupoSanguineo());
                psHistorial.setString(13, historial.getNotasAdicionales());
                psHistorial.setTimestamp(14, new java.sql.Timestamp(historial.getUltimaActualizacion().getTime()));
                psHistorial.setInt(15, historial.getIdHistorial());
                registros += psHistorial.executeUpdate();
            }

            conn.commit();  // Confirmar transacción
        } catch (SQLException e) {
            if (conexionTransaccional == null) {
                try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection()) {
                    if (conn != null) {
                        conn.rollback();  // Revertir transacción en caso de error
                    }
                }
            }
            throw e;
        }

        return registros;
    }

    @Override
    public int eliminar(Historial historial) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, historial.getIdHistorial());
            registros = ps.executeUpdate();
        }

        return registros;
    }
}
