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
            "INSERT INTO paciente (detalleEps) VALUES (?)";
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

    // Método que permite seleccionar y recuperar los objetos de la base de datos (SELECT)
    public List<Paciente> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_SELECT);
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            // Iteración de los elementos para obtener todos los registros
            while (rs.next()) {
                int idPaciente = rs.getInt("id_paciente");
                String detalleEps = rs.getString("detalle_eps");
                Date fechaConsulta = rs.getDate("fecha_consulta");
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String identificacion = rs.getString("identificacion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                int idHistorial = rs.getInt("id_historial");
                String motivoConsulta = rs.getString("motivo_consulta");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                String sexo = rs.getString("sexo");
                String direccion = rs.getString("direccion");
                String ocupacion = rs.getString("ocupacion");
                String contactoEmergencia = rs.getString("contacto_emergencia");
                String nombreContactoEmergencia = rs.getString("nombre_contacto_emergencia");
                String alergias = rs.getString("alergias");
                String condicionesPreexistentes = rs.getString("condiciones_preexistentes");
                String medicamentosActuales = rs.getString("medicamentos_actuales");
                String historialVacunas = rs.getString("historial_vacunas");
                String grupoSanguineo = rs.getString("grupo_sanguineo");
                String notasAdicionales = rs.getString("notas_adicionales");
                Timestamp ultimaActualizacion = rs.getTimestamp("ultima_actualizacion");

                // Creación de un nuevo objeto de la clase o clases
                var paciente = new Paciente();
                paciente.setIdPaciente(idPaciente);
                paciente.setDetalleEps(detalleEps);
                paciente.setFechaConsulta(fechaConsulta);

                var persona = new Persona();
                persona.setIdPersona(idPersona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setIdentificacion(identificacion);
                persona.setTelefono(telefono);
                persona.setEmail(email);

                var historial = new Historial();
                historial.setIdHistorial(idHistorial);
                historial.setMotivoConsulta(motivoConsulta);
                historial.setFechaNacimiento(fechaNacimiento);
                historial.setSexo(sexo);
                historial.setDireccion(direccion);
                historial.setOcupacion(ocupacion);
                historial.setContactoEmergencia(contactoEmergencia);
                historial.setNombreContactoEmergencia(nombreContactoEmergencia);
                historial.setAlergias(alergias);
                historial.setCondicionesPreexistentes(condicionesPreexistentes);
                historial.setMedicamentosActuales(medicamentosActuales);
                historial.setHistorialVacunas(historialVacunas);
                historial.setGrupoSanguineo(grupoSanguineo);
                historial.setNotasAdicionales(notasAdicionales);
                historial.setUltimaActualizacion(ultimaActualizacion);

                paciente.setPersona(persona);
                paciente.setHistorial(historial);
                pacientes.add(paciente);
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

        return pacientes;
    }

    // Método para recuperar solo uno de los registros en la base de datos
    public Paciente seleccionarPorId(int idPaciente) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Paciente paciente = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_SELECT_ONE);
            ps.setInt(1, idPaciente);
            rs = ps.executeQuery();

            // Si se encontró un registro, crear el objeto Paciente
            if (rs.next()) {
                paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("id_paciente"));
                paciente.setDetalleEps(rs.getString("detalle_eps"));
                paciente.setFechaConsulta(rs.getDate("fecha_consulta"));
                var persona = new Persona();
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setIdentificacion(rs.getString("identificacion"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setEmail(rs.getString("email"));
                var historial = new Historial();
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

                paciente.setPersona(persona);
                paciente.setHistorial(historial);
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

        return paciente;
    }

    // Método que permite insertar objetos en la base de datos (INSERT)
    public int insertar(Paciente paciente) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_INSERT);
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, paciente.getDetalleEps());

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
    public int actualizar(Paciente paciente) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_UPDATE);
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, paciente.getDetalleEps());
            ps.setInt(2, paciente.getIdPaciente());

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
    public int eliminar(Paciente paciente) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_DELETE);
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, paciente.getIdPaciente());

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
