package datos;

import domain.Perfil;
import domain.Rol;
import domain.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class PerfilDaoJDBC implements PerfilDAO {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT id_perfil, id_usuario, rol FROM perfil";
    private static final String SQL_SELECT_BY_ID = "SELECT id_perfil, id_usuario, rol FROM perfil WHERE id_perfil = ?";
    private static final String SQL_INSERT = "INSERT INTO perfil (id_usuario, rol) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE perfil SET id_usuario = ?, rol = ? WHERE id_perfil = ?";
    private static final String SQL_DELETE = "DELETE FROM perfil WHERE id_perfil = ?";

    public PerfilDaoJDBC() {
    }

    public PerfilDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<Perfil> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Perfil> perfiles = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idPerfil = rs.getInt("id_perfil");
                int idUsuario = rs.getInt("id_usuario");
                Rol rol = Rol.valueOf(rs.getString("rol"));

                Usuario usuario = new Usuario(); // Crear o buscar el Usuario correspondiente
                usuario.setIdUsuario(idUsuario);

                Perfil perfil = new Perfil(idPerfil, rol, usuario);
                perfiles.add(perfil);
            }
        } finally {
            close(rs);
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }

        return perfiles;
    }

    @Override
    public Perfil seleccionarPorId(int idPerfil) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Perfil perfil = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_SELECT_BY_ID);
            ps.setInt(1, idPerfil);
            rs = ps.executeQuery();

            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                Rol rol = Rol.valueOf(rs.getString("rol"));

                Usuario usuario = new Usuario(); // Crear o buscar el Usuario correspondiente
                usuario.setIdUsuario(idUsuario);

                perfil = new Perfil(idPerfil, rol, usuario);
            }
        } finally {
            close(rs);
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }

        return perfil;
    }

    @Override
    public int insertar(Perfil perfil) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, perfil.getUsuario().getIdUsuario());
            ps.setString(2, perfil.getRol().name());

            registros = ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    perfil.setIdPerfil(generatedKeys.getInt(1));
                }
            }
        } finally {
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }

        return registros;
    }

    @Override
    public int actualizar(Perfil perfil) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setInt(1, perfil.getUsuario().getIdUsuario());
            ps.setString(2, perfil.getRol().name());
            ps.setInt(3, perfil.getIdPerfil());

            registros = ps.executeUpdate();
        } finally {
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }

        return registros;
    }

    @Override
    public int eliminar(Perfil perfil) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, perfil.getIdPerfil());

            registros = ps.executeUpdate();
        } finally {
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }

        return registros;
    }
}

