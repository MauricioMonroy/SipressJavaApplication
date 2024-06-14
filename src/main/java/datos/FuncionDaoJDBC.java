package datos;

import domain.Empleado;
import domain.Funcion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.getConnection;

public class FuncionDaoJDBC implements FuncionDAO {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT f.id_funcion, f.id_empleado, f.descripcion, e.id_usuario, e.cargo " +
            "FROM funcion f LEFT JOIN empleado e ON f.id_empleado = e.id_empleado";
    private static final String SQL_SELECT_BY_ID = "SELECT f.id_funcion, f.id_empleado, f.descripcion, e.id_usuario, e.cargo " +
            "FROM funcion f LEFT JOIN empleado e ON f.id_empleado = e.id_empleado WHERE f.id_funcion = ?";
    private static final String SQL_INSERT = "INSERT INTO funcion (id_empleado, descripcion) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE funcion SET id_empleado = ?, descripcion = ? WHERE id_funcion = ?";
    private static final String SQL_DELETE = "DELETE FROM funcion WHERE id_funcion = ?";

    public FuncionDaoJDBC() {
    }

    public FuncionDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    private Empleado mapEmpleado(ResultSet rs) throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(rs.getInt("id_empleado"));
        empleado.setIdUsuario(rs.getInt("id_usuario"));
        empleado.setCargo(rs.getString("cargo"));
        return empleado;
    }

    private Funcion mapFuncion(ResultSet rs) throws SQLException {
        int idFuncion = rs.getInt("id_funcion");
        String descripcion = rs.getString("descripcion");

        Empleado empleado = mapEmpleado(rs);
        Funcion funcion = new Funcion();
        funcion.setIdFuncion(idFuncion);
        funcion.setEmpleado(empleado);
        funcion.setDescripcion(descripcion);

        return funcion;
    }

    @Override
    public List<Funcion> seleccionar() throws SQLException {
        List<Funcion> funciones = new ArrayList<>();

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Ejecutando query = " + SQL_SELECT);

            while (rs.next()) {
                funciones.add(mapFuncion(rs));
            }
        }

        return funciones;
    }

    @Override
    public Funcion seleccionarPorId(int idFuncion) throws SQLException {
        Funcion funcion = null;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID)) {

            ps.setInt(1, idFuncion);
            System.out.println("Ejecutando query = " + SQL_SELECT_BY_ID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    funcion = mapFuncion(rs);
                }
            }
        }

        return funcion;
    }

    @Override
    public int insertar(Funcion funcion) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, funcion.getEmpleado().getIdEmpleado());
            ps.setString(2, funcion.getDescripcion());

            registros = ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    funcion.setIdFuncion(generatedKeys.getInt(1));
                }
            }
        }

        return registros;
    }

    @Override
    public int actualizar(Funcion funcion) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, funcion.getEmpleado().getIdEmpleado());
            ps.setString(2, funcion.getDescripcion());
            ps.setInt(3, funcion.getIdFuncion());

            registros = ps.executeUpdate();
        }

        return registros;
    }

    @Override
    public int eliminar(Funcion funcion) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, funcion.getIdFuncion());

            registros = ps.executeUpdate();
        }

        return registros;
    }
}



