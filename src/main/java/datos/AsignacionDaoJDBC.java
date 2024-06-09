package datos;

import domain.Asignacion;
import domain.Empleado;
import domain.Paciente;
import domain.Servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class AsignacionDaoJDBC implements AsignacionDAO {

    // Objeto que permite manejar la conexión y las transacciones con la base de datos
    private Connection conexionTransaccional;

    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT a.id_asignacion, a.id_paciente, a.id_servicio, a.id_empleado, px.id_paciente, px.id_paciente, "
                    + " px.detalle_eps, px.fecha_consulta, s.id_servicio, s.nombre, e.id_empleado, e.id_paciente, "
                    + "e.cargo FROM asignacion a INNER JOIN paciente px ON a.id_paciente = px.id_paciente "
                    + "INNER JOIN servicio s ON a.id_servicio = s.id_servicio "
                    + "INNER JOIN empleado e ON a.id_empleado = e.id_empleado ";
    private static final String SQL_SELECT_ONE =
            "SELECT a.id_asignacion, a.id_paciente, a.id_servicio, a.id_empleado, px.id_paciente, px.id_paciente, "
                    + " px.detalle_eps, px.fecha_consulta, s.id_servicio, s.nombre, e.id_empleado, e.id_paciente, "
                    + "e.cargo FROM asignacion a INNER JOIN paciente px ON a.id_paciente = px.id_paciente "
                    + "INNER JOIN servicio s ON a.id_servicio = s.id_servicio "
                    + "INNER JOIN empleado e ON a.id_empleado = e.id_empleado "
                    + "WHERE a.id_asignacion = ?";
    private static final String SQL_INSERT =
            "INSERT INTO asignacion (cargo) VALUES (?)";
    private static final String SQL_UPDATE =
            "UPDATE asignacion SET cargo = ? WHERE id_asignacion = ?";
    private static final String SQL_DELETE =
            "DELETE FROM asignacion WHERE id_asignacion = ?";

    // Constructores para la conexión transaccional
    public AsignacionDaoJDBC() {
    }

    public AsignacionDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    // Método que permite seleccionar y listar todos los objetos de la base de datos (SELECT)
    public List<Asignacion> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Asignacion> asignaciones = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_SELECT);
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            // Iteración de los elementos para obtener todos los registros
            while (rs.next()) {
                int idAsignacion = rs.getInt("id_asignacion");
                int idPaciente = rs.getInt("id_paciente");
                int idServicio = rs.getInt("id_servicio");
                int idEmpleado = rs.getInt("id_empleado");
                String detalleEps = rs.getString("detalle_eps");
                Date fechaConsulta = rs.getDate("fecha_consulta");
                String nombre = rs.getString("nombre");
                String cargo = rs.getString("cargo");

                // Creación de un nuevo objeto de la clase Paciente
                var paciente = new Paciente();
                paciente.setIdPaciente(idPaciente);
                paciente.setDetalleEps(detalleEps);
                paciente.setFechaConsulta(fechaConsulta);

                // Creación de un nuevo objeto de la clase Servicio
                var servicio = new Servicio();
                servicio.setIdServicio(idServicio);
                servicio.setNombre(nombre);

                // Creación de un nuevo objeto de la clase Empleado
                var empleado = new Empleado();
                empleado.setIdEmpleado(idEmpleado);
                empleado.setCargo(cargo);

                // Creación de un nuevo objeto de la clase Asignacion
                var asignacion = new Asignacion();
                asignacion.setIdAsignacion(idAsignacion);
                asignacion.setPaciente(paciente);
                asignacion.setServicio(servicio);
                asignacion.setEmpleado(empleado);

                asignaciones.add(asignacion);
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

        return asignaciones;
    }

    // Método para recuperar solo uno de los registros en la base de datos
    public Asignacion seleccionarPorId(int idAsignacion) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Asignacion asignacion = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_SELECT_ONE);
            ps.setInt(1, idAsignacion);
            rs = ps.executeQuery();

            // Si se encuentra un registro, se crea el objeto
            if (rs.next()) {
                asignacion = new Asignacion();
                asignacion.setIdAsignacion(rs.getInt("id_asignacion"));

                var paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("id_paciente"));
                paciente.setDetalleEps(rs.getString("detalle_eps"));
                paciente.setFechaConsulta(rs.getDate("fecha_consulta"));

                var servicio = new Servicio();
                servicio.setIdServicio(rs.getInt("id_servicio"));
                servicio.setNombre(rs.getString("nombre"));

                var empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setCargo(rs.getString("cargo"));

                asignacion.setPaciente(paciente);
                asignacion.setServicio(servicio);
                asignacion.setEmpleado(empleado);
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

        return asignacion;
    }

    // Método que permite insertar objetos en la base de datos (INSERT)
    public int insertar(Asignacion asignacion) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_INSERT);
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, asignacion.getIdAsignacion());
            ps.setInt(2, asignacion.getPaciente().getIdPaciente());
            ps.setInt(3, asignacion.getServicio().getIdServicio());
            ps.setInt(4, asignacion.getEmpleado().getIdEmpleado());

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
    public int actualizar(Asignacion asignacion) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_UPDATE);
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setInt(1, asignacion.getPaciente().getIdPaciente());
            ps.setInt(2, asignacion.getServicio().getIdServicio());
            ps.setInt(3, asignacion.getEmpleado().getIdEmpleado());
            ps.setInt(4, asignacion.getIdAsignacion());

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
    public int eliminar(Asignacion asignacion) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_DELETE);
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, asignacion.getIdAsignacion());

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
