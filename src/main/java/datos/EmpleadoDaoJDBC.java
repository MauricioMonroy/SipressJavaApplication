package datos;

import domain.*;
import domain.Empleado;
import domain.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class EmpleadoDaoJDBC implements EmpleadoDAO {

    // Objeto que permite manejar la conexión y las transacciones con la base de datos
    private Connection conexionTransaccional;

    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT e.id_empleado, e.id_persona, e.cargo, p.id_persona, p.nombre, p.apellido, p.identificacion, "
                    + "p.telefono, p.email FROM empleado e INNER JOIN persona p ON e.id_persona = p.id_persona";
    private static final String SQL_SELECT_ONE =
            "SELECT e.id_empleado, e.id_persona, e.cargo, p.id_persona, p.nombre, p.apellido, p.identificacion, "
                    + "p.telefono, p.email FROM empleado e INNER JOIN persona p ON e.id_persona = p.id_persona "
                    + "WHERE e.id_empleado = ?";
    private static final String SQL_INSERT =
            "INSERT INTO empleado (cargo) VALUES (?)";
    private static final String SQL_UPDATE =
            "UPDATE empleado SET cargo = ? WHERE id_empleado = ?";
    private static final String SQL_DELETE =
            "DELETE FROM empleado WHERE id_empleado = ?";

    // Constructores para la conexión transaccional
    public EmpleadoDaoJDBC() {
    }

    public EmpleadoDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    // Método que permite seleccionar y listar todos los objetos de la base de datos (SELECT)
    @Override
    public List<Empleado> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Empleado> empleados = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_SELECT);
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            // Iteración de los elementos para obtener todos los registros
            while (rs.next()) {
                int idEmpleado = rs.getInt("id_empleado");
                String cargo = rs.getString("cargo");
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String identificacion = rs.getString("identificacion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                // Creación de un nuevo objeto de la clase o clases
                var empleado = new Empleado();
                empleado.setIdEmpleado(idEmpleado);
                empleado.setCargo(cargo);

                var persona = new Persona();
                persona.setIdPersona(idPersona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setIdentificacion(identificacion);
                persona.setTelefono(telefono);
                persona.setEmail(email);

                empleado.setPersona(persona);
                empleados.add(empleado);
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

        return empleados;
    }

    // Método para recuperar solo uno de los registros en la base de datos
    @Override
    public Empleado seleccionarPorId(int idEmpleado) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Empleado empleado = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_SELECT_ONE);
            ps.setInt(1, idEmpleado);
            rs = ps.executeQuery();

            // Si se encontró un registro, crear el objeto Empleado
            if (rs.next()) {
                empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setCargo(rs.getString("cargo"));
                var persona = new Persona();
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setIdentificacion(rs.getString("identificacion"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setEmail(rs.getString("email"));

                empleado.setPersona(persona);
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

        return empleado;
    }

    // Método que permite insertar objetos en la base de datos (INSERT)
    @Override
    public int insertar(Empleado empleado) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_INSERT);
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, empleado.getCargo());

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
    @Override
    public int actualizar(Empleado empleado) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_UPDATE);
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, empleado.getCargo());
            ps.setInt(2, empleado.getIdEmpleado());

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
    @Override
    public int eliminar(Empleado empleado) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_DELETE);
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, empleado.getIdEmpleado());

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
