package datos;

import domain.Empleado;
import domain.Funcion;
import domain.Funcion;
import domain.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class EmpleadoDaoJDBC implements EmpleadoDAO {

    // Objeto que permite manejar la conexión y las transacciones con la base de datos
    private Connection conexionTransaccional;

    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT e.id_empleado, e.id_usuario, e.cargo, u.id_usuario, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_paciente, u.es_empleado, f.id_funcion, f.id_empleado, f.descripcion "
                    + "FROM empleado e INNER JOIN usuario u ON e.id_usuario = u.id_usuario "
                    + "INNER JOIN funcion f ON f.id_empleado = e.id_empleado ";
    private static final String SQL_SELECT_ONE =
            "SELECT e.id_empleado, e.id_usuario, e.cargo, u.id_usuario, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_paciente, u.es_empleado, f.id_funcion, f.id_empleado, f.descripcion "
                    + "FROM empleado e INNER JOIN usuario u ON e.id_usuario = u.id_usuario "
                    + "INNER JOIN funcion f ON f.id_empleado = e.id_empleado "
                    + "WHERE e.id_empleado = ?";
    private static final String SQL_INSERT_USUARIO =
            "INSERT INTO empleado (id_usuario, nombre, apellido, identificacion, telefono, email, cargo, es_paciente, es_empleado) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_EMPLEADO =
            "INSERT INTO paciente (cargo, id_usuario) VALUES (?, ?)";
    private static final String SQL_INSERT_FUNCION =
            "INSERT INTO funcion (id_empleado, descripcion) VALUES (?, ?)";
    private static final String SQL_UPDATE_EMPLEADO =
            "UPDATE empleado SET cargo = ? WHERE id_empleado = ?";
    private static final String SQL_UPDATE_USUARIO =
            "UPDATE usuario SET nombre = ?, apellido = ?, identificacion = ?, telefono = ?, email = ? WHERE id_usuario = ?";
    private static final String SQL_UPDATE_FUNCION =
            "UPDATE funcion SET descripcion = ? WHERE id_funcion = ?";
    private static final String SQL_DELETE =
            "DELETE FROM empleado WHERE id_empleado = ?";

    // Constructores para la conexión transaccional
    public EmpleadoDaoJDBC() {
    }

    public EmpleadoDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    // Método que se encarga de mapear el ResultSet a un objeto de la clase
    private Empleado mapEmpleado(ResultSet rs) throws SQLException {
        Empleado empleado = new Empleado();

        // Llenar atributos específicos de Empleado
        empleado.setIdEmpleado(rs.getInt("id_empleado"));
        empleado.setCargo(rs.getString("cargo"));

        // Llenar atributos heredados de Usuario
        empleado.setIdUsuario(rs.getInt("id_usuario"));
        empleado.setNombre(rs.getString("nombre"));
        empleado.setApellido(rs.getString("apellido"));
        empleado.setIdentificacion(rs.getString("identificacion"));
        empleado.setTelefono(rs.getString("telefono"));
        empleado.setEmail(rs.getString("email"));

        // Crear una lista para almacenar las funciones del empleado
        List<Funcion> funciones = new ArrayList<>();

        do {
            // Llenar atributos específicos de la funcion
            Funcion funcion = new Funcion();
            funcion.setIdFuncion(rs.getInt("id_funcion"));
            funcion.setDescripcion(rs.getString("descripcion"));

            // Establecer la relación entre Empleado y Funcion
            funcion.setEmpleado(empleado);

            // Añadir la función a la lista de funciones del empleado
            funciones.add(funcion);
        } while (rs.next());  // Continuar mientras haya más funciones para este empleado en el ResultSet

        // Establecer la lista de funciones en el empleado
        empleado.setFuncionList(funciones);

        return empleado;
    }


    // Método que permite seleccionar y listar todos los objetos de la base de datos (SELECT)
    @Override
    public List<Empleado> seleccionar() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = SQL_SELECT;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Ejecutando query = " + sql);

            while (rs.next()) {
                empleados.add(mapEmpleado(rs));
            }
        }

        return empleados;
    }


    // Método para recuperar solo uno de los registros en la base de datos
    @Override
    public Empleado seleccionarPorId(int idEmpleado) throws SQLException {
        Empleado empleado = null;
        String sql = SQL_SELECT_ONE;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);
            System.out.println("Ejecutando query = " + sql);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    empleado = mapEmpleado(rs);
                }
            }
        }

        return empleado;
    }


    // Método que permite insertar objetos en la base de datos (INSERT)
    @Override
    public int insertar(Empleado empleado) throws SQLException {
        Connection conn = null;
        PreparedStatement psEmpleado = null;
        PreparedStatement psFuncion = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            conn.setAutoCommit(false);

            // Insertar Empleado
            System.out.println("Ejecutando query = " + SQL_INSERT_EMPLEADO);
            psEmpleado = conn.prepareStatement(SQL_INSERT_EMPLEADO, Statement.RETURN_GENERATED_KEYS);
            psEmpleado.setInt(1, empleado.getIdUsuario());
            psEmpleado.setString(2, empleado.getNombre());
            psEmpleado.setString(3, empleado.getApellido());
            psEmpleado.setString(4, empleado.getIdentificacion());
            psEmpleado.setString(5, empleado.getTelefono());
            psEmpleado.setString(6, empleado.getEmail());
            psEmpleado.setString(7, empleado.getCargo());
            psEmpleado.executeUpdate();

            ResultSet rsEmpleado = psEmpleado.getGeneratedKeys();
            if (rsEmpleado.next()) {
                empleado.setIdEmpleado(rsEmpleado.getInt(1));
            }

            // Insertar Funciones
            System.out.println("Ejecutando query = " + SQL_INSERT_FUNCION);
            psFuncion = conn.prepareStatement(SQL_INSERT_FUNCION);
            for (Funcion funcion : empleado.getFuncionList()) {
                psFuncion.setInt(1, empleado.getIdEmpleado());
                psFuncion.setString(2, funcion.getDescripcion());
                psFuncion.addBatch();
            }
            psFuncion.executeBatch();

            conn.commit();
            registros = 1; // Se considera como un registro exitoso

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            close(psFuncion);
            close(psEmpleado);
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
        PreparedStatement psEmpleado = null;
        PreparedStatement psFuncion = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            conn.setAutoCommit(false);

            // Actualizar Empleado
            System.out.println("Ejecutando query = " + SQL_UPDATE_EMPLEADO);
            psEmpleado = conn.prepareStatement(SQL_UPDATE_EMPLEADO);
            psEmpleado.setInt(1, empleado.getIdUsuario());
            psEmpleado.setString(2, empleado.getNombre());
            psEmpleado.setString(3, empleado.getApellido());
            psEmpleado.setString(4, empleado.getIdentificacion());
            psEmpleado.setString(5, empleado.getTelefono());
            psEmpleado.setString(6, empleado.getEmail());
            psEmpleado.setString(7, empleado.getCargo());
            psEmpleado.setInt(8, empleado.getIdEmpleado());
            psEmpleado.executeUpdate();

            // Actualizar Funciones (puedes eliminarlas todas y volver a insertarlas o actualizar individualmente)
            System.out.println("Ejecutando query = " + SQL_UPDATE_FUNCION);
            psFuncion = conn.prepareStatement(SQL_UPDATE_FUNCION);
            for (Funcion funcion : empleado.getFuncionList()) {
                psFuncion.setString(1, funcion.getDescripcion());
                psFuncion.setInt(2, funcion.getIdFuncion());
                psFuncion.addBatch();
            }
            psFuncion.executeBatch();

            conn.commit();
            registros = 1; // Se considera como un registro exitoso

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            close(psFuncion);
            close(psEmpleado);
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
