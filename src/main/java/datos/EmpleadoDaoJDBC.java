package datos;

import domain.Empleado;
import domain.Usuario;

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
            "SELECT e.id_empleado, e.cargo, u.id_usuario, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_empleado, u.es_empleado "
                    + "FROM empleado e INNER JOIN usuario u ON e.id_usuario = u.id_usuario ";
    private static final String SQL_SELECT_ONE =
            "SELECT e.id_empleado, e.cargo, u.id_usuario, u.nombre, u.apellido, u.identificacion, "
                    + "u.telefono, u.email, u.es_empleado, u.es_empleado "
                    + "FROM empleado e INNER JOIN usuario u ON e.id_usuario = u.id_usuario "
                    + "WHERE e.id_empleado = ?";
    private static final String SQL_INSERT_EMPLEADO =
            "INSERT INTO empleado (cargo, id_usuario) VALUES (?, ?)";
    private static final String SQL_INSERT_USUARIO =
            "INSERT INTO usuario (username, password, nombre, apellido, identificacion, telefono, " +
                    "email, es_paciente, es_empleado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_EMPLEADO =
            "UPDATE empleado SET cargo = ? WHERE id_empleado = ?";
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

        empleado.setIdEmpleado(rs.getInt("id_empleado"));
        empleado.setCargo(rs.getString("cargo"));

        // Crear un nuevo objeto Usuario y llenar sus atributos
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setIdentificacion(rs.getString("identificacion"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setEmail(rs.getString("email"));
        usuario.setEsEmpleado(rs.getBoolean("es_empleado"));
        usuario.setEsEmpleado(rs.getBoolean("es_empleado"));

        empleado.setUsuario(usuario);

        return empleado;
    }

    // Método que permite seleccionar y listar todos los objetos de la base de datos (SELECT)
    @Override
    public List<Empleado> seleccionar() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Ejecutando query SELECT");

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

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ONE)) {

            ps.setInt(1, idEmpleado);
            System.out.println("Ejecutando query SELECT_ONE ");

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
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psUsuario = conn.prepareStatement(
                    SQL_INSERT_USUARIO, Statement.RETURN_GENERATED_KEYS)) {
                Usuario usuario = empleado.getUsuario();
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
                        empleado.setIdUsuario(idUsuario);
                    }
                }
            }

            try (PreparedStatement psEmpleado = conn.prepareStatement(
                    SQL_INSERT_EMPLEADO, Statement.RETURN_GENERATED_KEYS)) {
                psEmpleado.setString(1, empleado.getCargo());
                psEmpleado.setInt(2, empleado.getIdUsuario());

                psEmpleado.executeUpdate();

                try (ResultSet rsPaciente = psEmpleado.getGeneratedKeys()) {
                    if (rsPaciente.next()) {
                        int idEmpleado = rsPaciente.getInt(1);
                        empleado.setIdEmpleado(idEmpleado);
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


    // Método que permite actualizar objetos en la base de datos (UPDATE)
    @Override
    public int actualizar(Empleado empleado) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query SQL_UPDATE");
            ps = conn.prepareStatement(SQL_UPDATE_EMPLEADO);
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
