package datos;

import domain.*;

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
            "SELECT e.id_empleado, e.id_usuario, e.cargo, u.id_usuario, u.username, u.password, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_empleado, u.es_empleado, f.id_funcion, f.id_empleado, f.descripcion, "
                    + "a.id_asignacion, a.id_empleado, a.id_servicio, a.id_empleado "
                    + "FROM empleado e INNER JOIN usuario u ON e.id_usuario = u.id_usuario "
                    + "INNER JOIN funcion f ON f.id_empleado = e.id_empleado "
                    + "INNER JOIN asignacion a ON a.id_empleado = e.id_empleado ";
    private static final String SQL_SELECT_ONE =
            "SELECT e.id_empleado, e.id_usuario, e.cargo, u.id_usuario, u.username, u.password, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_empleado, u.es_empleado, f.id_funcion, f.id_empleado, f.descripcion, "
                    + "a.id_asignacion, a.id_empleado, a.id_servicio, a.id_empleado "
                    + "FROM empleado e INNER JOIN usuario u ON e.id_usuario = u.id_usuario "
                    + "INNER JOIN funcion f ON f.id_empleado = e.id_empleado "
                    + "INNER JOIN asignacion a ON a.id_empleado = e.id_empleado "
                    + "WHERE e.id_empleado = ?";

    private static final String SQL_INSERT_EMPLEADO =
            "INSERT INTO empleado (cargo, id_usuario) VALUES (?, ?)";
    private static final String SQL_INSERT_USUARIO =
            "INSERT INTO empleado(id_usuario, cargo) VALUES(?, ?)";
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

    // Método para recuperar las funciones del empleado
    private List<Funcion> getFuncionesPorEmpleadoId(int idEmpleado) throws SQLException {
        List<Funcion> funciones = new ArrayList<>();
        String SQL_SELECT_FUNCIONES = "SELECT * FROM funcion WHERE id_empleado = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_FUNCIONES)) {

            ps.setInt(1, idEmpleado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Funcion funcion = new Funcion();
                    funcion.setIdFuncion(rs.getInt("id_funcion"));
                    funcion.setDescripcion(rs.getString("descripcion"));
                    funciones.add(funcion);
                }
            }
        }
        return funciones;
    }

    // Método para recuperar las asignaciones del empleado
    private List<Asignacion> getAsignacionesPorEmpleadoId(int idEmpleado) throws SQLException {
        List<Asignacion> asignaciones = new ArrayList<>();
        String SQL_SELECT_ASIGNACIONES = "SELECT * FROM asignacion WHERE id_empleado = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ASIGNACIONES)) {

            ps.setInt(1, idEmpleado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Asignacion asignacion = new Asignacion();
                    asignacion.setIdAsignacion(rs.getInt("id_asignacion"));
                    asignaciones.add(asignacion);
                }
            }
        }
        return asignaciones;
    }


    // Método que se encarga de mapear el ResultSet a un objeto de la clase
    private Empleado mapEmpleado(ResultSet rs, List<Funcion> funciones, List<Asignacion> asignaciones) throws SQLException {
        Empleado empleado = new Empleado();

        // Llenar atributos específicos de Empleado
        empleado.setIdEmpleado(rs.getInt("id_empleado"));
        empleado.setIdUsuario(rs.getInt("id_usuario"));
        empleado.setCargo(rs.getString("cargo"));

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
        usuario.setEsEmpleado(rs.getBoolean("es_empleado"));
        usuario.setEsEmpleado(rs.getBoolean("es_empleado"));

        // Establecer el objeto Usuario en el objeto Empleado
        empleado.setUsuario(usuario);

        // Crear atributos específicos de Función
        Funcion funcion = new Funcion();
        funcion.setIdFuncion(rs.getInt("id_funcion"));
        funcion.setDescripcion(rs.getString("descripcion"));

        // Crear atributos específicos de Asignación
        Asignacion asignacion = new Asignacion();
        asignacion.setIdAsignacion(rs.getInt("id_asignacion"));

        // Establecer la relación entre Empleado, Función y Asignacion
        funcion.setEmpleado(empleado);
        asignacion.setEmpleado(empleado);
        empleado.setFuncionList(funciones);
        empleado.setAsignacionList(asignaciones);

        return empleado;
    }

// Método que permite seleccionar y listar todos los objetos de la base de datos (SELECT)
    @Override
    public List<Empleado> seleccionar() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Ejecutando query...");

            while (rs.next()) {
                int idEmpleado = rs.getInt("id_empleado");
                List<Funcion> funciones = getFuncionesPorEmpleadoId(idEmpleado);
                List<Asignacion> asignaciones = getAsignacionesPorEmpleadoId(idEmpleado);
                empleados.add(mapEmpleado(rs, funciones, asignaciones));
            }
        }

        return empleados;
    }

    // Método para recuperar solo uno de los registros en la base de datos
    @Override
    public Empleado seleccionarPorId(int idEmpleado) throws SQLException {
        Empleado empleado = null;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ONE)) {

            ps.setInt(1, idEmpleado);
            System.out.println("Ejecutando query... ");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    List<Funcion> funciones = getFuncionesPorEmpleadoId(idEmpleado);
                    List<Asignacion> asignaciones = getAsignacionesPorEmpleadoId(idEmpleado);
                    empleado = mapEmpleado(rs, funciones, asignaciones);
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
    // Método para buscar un registro en la base de datos
    public List<Empleado> buscar(String query) throws SQLException {
        String SQL_SELECT_BUSCAR = "SELECT * FROM empleado WHERE cargo LIKE ?";
        List<Empleado> empleados = new ArrayList<>();

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BUSCAR)) {
            stmt.setString(1, "%" + query + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idEmpleado = rs.getInt("idEmpleado");
                    String cargo = rs.getString("cargo");

                    Empleado empleado = new Empleado(idEmpleado, cargo);
                    empleados.add(empleado);
                }
            }
        }
        return empleados;
    }
}
