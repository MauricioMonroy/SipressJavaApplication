package datos;

import domain.Asignacion;
import domain.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static datos.Conexion.getConnection;

public class ServicioDaoJDBC implements ServicioDAO {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT s.id_servicio, s.nombre, a.id_asignacion "
            + "FROM servicio s LEFT JOIN asignacion a ON s.id_servicio = a.id_servicio";
    private static final String SQL_SELECT_BY_ID = "SELECT s.id_servicio, s.nombre, a.id_asignacion "
            + "FROM servicio s LEFT JOIN asignacion a ON s.id_servicio = a.id_servicio WHERE s.id_servicio = ?";
    private static final String SQL_INSERT = "INSERT INTO servicio (nombre) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE servicio SET nombre = ? WHERE id_servicio = ?";
    private static final String SQL_DELETE = "DELETE FROM servicio WHERE id_servicio = ?";

    public ServicioDaoJDBC() {
    }

    public ServicioDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    private Servicio mapServicio(ResultSet rs) throws SQLException {
        int idServicio = rs.getInt("id_servicio");
        String nombre = rs.getString("nombre");

        Servicio servicio = new Servicio();
        servicio.setIdServicio(idServicio);
        servicio.setNombre(nombre);

        // Comprobar si hay una Asignacion para este Servicio
        int idAsignacion = rs.getInt("id_asignacion");
        if (!rs.wasNull()) {
            Asignacion asignacion = new Asignacion();
            asignacion.setIdAsignacion(idAsignacion);
            servicio.setAsignacionList(Collections.singletonList(asignacion));
        }

        return servicio;
    }


    @Override
    public List<Servicio> seleccionar() throws SQLException {
        List<Servicio> servicios = new ArrayList<>();

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Ejecutando query = " + SQL_SELECT);

            while (rs.next()) {
                servicios.add(mapServicio(rs));
            }
        }

        return servicios;
    }

    @Override
    public Servicio seleccionarPorId(int idServicio) throws SQLException {
        Servicio servicio = null;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID)) {

            ps.setInt(1, idServicio);
            System.out.println("Ejecutando query = " + SQL_SELECT_BY_ID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    servicio = mapServicio(rs);
                }
            }
        }

        return servicio;
    }

    @Override
    public int insertar(Servicio servicio) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, servicio.getNombre());

            registros = ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    servicio.setIdServicio(generatedKeys.getInt(1));
                }
            }
        }

        return registros;
    }


    @Override
    public int actualizar(Servicio servicio) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, servicio.getNombre());
            ps.setInt(2, servicio.getIdServicio());

            registros = ps.executeUpdate();
        }

        return registros;
    }

    @Override
    public int eliminar(Servicio servicio) throws SQLException {
        int registros = 0;

        try (Connection conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, servicio.getIdServicio());

            registros = ps.executeUpdate();
        }

        return registros;
    }
}
