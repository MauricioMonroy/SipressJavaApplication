package datos;

import domain.Historial;
import domain.Paciente;
import domain.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.getConnection;

public class PacienteDaoJDBC implements PacienteDAO {
    // Objeto que permite manejar la conexión y las transacciones con la base de datos
    private Connection conexionTransaccional;

    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT pac.id_paciente, pac.id_usuario, pac.detalle_eps, pac.fecha_consulta, u.id_usuario, u.username, u.password, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_paciente, u.es_empleado, h.id_historial, h.id_paciente, h.motivo_consulta, h.fecha_nacimiento, h.sexo, h.direccion, "
                    + "h.ocupacion, h.contacto_emergencia, h.nombre_contacto_emergencia, h.alergias, h.condiciones_preexistentes, "
                    + "h.medicamentos_actuales, h.historial_vacunas, h.grupo_sanguineo, h.notas_adicionales, h.ultima_actualizacion "
                    + " FROM paciente pac INNER JOIN usuario u ON pac.id_usuario = u.id_usuario "
                    + "INNER JOIN historial h ON h.id_paciente = pac.id_paciente ";
    private static final String SQL_SELECT_ONE =
            "SELECT pac.id_paciente, pac.id_usuario, pac.detalle_eps, pac.fecha_consulta, u.id_usuario, u.username, u.password, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_paciente, u.es_empleado, h.id_historial, h.id_paciente, h.motivo_consulta, h.fecha_nacimiento, h.sexo, h.direccion, "
                    + "h.ocupacion, h.contacto_emergencia, h.nombre_contacto_emergencia, h.alergias, h.condiciones_preexistentes, "
                    + "h.medicamentos_actuales, h.historial_vacunas, h.grupo_sanguineo, h.notas_adicionales, h.ultima_actualizacion "
                    + " FROM paciente pac INNER JOIN usuario u ON pac.id_usuario = u.id_usuario "
                    + "INNER JOIN historial h ON h.id_paciente = pac.id_paciente "
                    + "WHERE pac.id_paciente = ?";

    private static final String SQL_INSERT_USUARIO =
            "INSERT INTO usuario (username, password, nombre, apellido, identificacion, telefono, email, es_paciente, es_empleado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_PACIENTE =
            "INSERT INTO paciente (detalle_eps, fecha_consulta, id_usuario) VALUES (?, ?, ?)";

    private static final String SQL_UPDATE_USUARIO =
            "UPDATE usuario SET nombre = ?, apellido = ?, identificacion = ?, telefono = ?, email = ? WHERE id_usuario = ?";
    private static final String SQL_UPDATE_PACIENTE =
            "UPDATE paciente SET detalle_eps = ?, fecha_consulta = ? WHERE id_paciente = ?";

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

        // Llenar atributos específicos de Paciente
        paciente.setIdPaciente(rs.getInt("id_paciente"));
        paciente.setDetalleEps(rs.getString("detalle_eps"));
        paciente.setFechaConsulta(rs.getDate("fecha_consulta"));

        // Crear un nuevo objeto Usuario y llenar sus atributos
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

        // Establecer el objeto Usuario en el objeto Paciente
        paciente.setUsuario(usuario);

        // Crear atributos específicos del historial médico
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

        // Establecer la relación entre Paciente e Historial
        historial.setPaciente(paciente);
        paciente.setHistorial(historial);

        return paciente;
    }


    @Override
    public List<Paciente> seleccionar() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Ejecutando query... ");

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
            conn.setAutoCommit(false); // Iniciar la transacción

            // Insertar Usuario
            System.out.println("Ejecutando query = " + SQL_INSERT_USUARIO);
            try (PreparedStatement psUsuario = conn.prepareStatement(SQL_INSERT_USUARIO, Statement.RETURN_GENERATED_KEYS)) {
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

            // Insertar Paciente
            System.out.println("Ejecutando query = " + SQL_INSERT_PACIENTE);
            try (PreparedStatement psPaciente = conn.prepareStatement(SQL_INSERT_PACIENTE, Statement.RETURN_GENERATED_KEYS)) {
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

            // Insertar Historial
            HistorialDaoJDBC historialDao = new HistorialDaoJDBC(conn);
            Historial historial = paciente.getHistorial();
            historial.setPaciente(paciente);
            historialDao.insertar(historial);

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

            // Actualizar tabla usuario
            System.out.println("Ejecutando query = " + SQL_UPDATE_USUARIO);
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

            // Actualizar Paciente
            System.out.println("Ejecutando query = " + SQL_UPDATE_PACIENTE);
            try (PreparedStatement psPaciente = conn.prepareStatement(SQL_UPDATE_PACIENTE)) {
                psPaciente.setString(1, paciente.getDetalleEps());
                psPaciente.setDate(2, new java.sql.Date(paciente.getFechaConsulta().getTime()));
                psPaciente.setInt(3, paciente.getIdPaciente());
                registros += psPaciente.executeUpdate();
            }

            // Actualizar Historial
            System.out.println("Ejecutando query de actualización del historial");
            Historial historial = paciente.getHistorial();
            HistorialDaoJDBC historialDao = new HistorialDaoJDBC(conn);
            historial.setPaciente(paciente);
            registros += historialDao.actualizar(historial);

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
