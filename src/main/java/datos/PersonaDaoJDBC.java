/*
 * DAO hace referencia al patrón de diseño Data Access Object
 * Permite realizar las operaciones de la entidad seleccionada
 */
package datos;

import domain.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class PersonaDaoJDBC implements PersonaDAO {

    // Objeto que permite manejar la conexión y las transacciones con la base de datos
    private Connection conexionTransaccional;

    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT id_persona, nombre, apellido, identificacion, telefono, email FROM persona";
    private static final String SQL_SELECT_ONE =
            "SELECT id_persona, nombre, apellido, identificacion, telefono, email FROM persona "
                    + "WHERE id_persona = ?";
    private static final String SQL_INSERT =
            "INSERT INTO persona (nombre, apellido, identificacion, telefono, email) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE persona SET id_paciente = ?, nombre = ?, apellido = ?, identificacion = ?, telefono = ?, email = ? WHERE id_persona = ?";
    private static final String SQL_DELETE =
            "DELETE FROM persona WHERE id_persona = ?";

    // Constructores para la conexión transaccional
    public PersonaDaoJDBC() {
    }

    public PersonaDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    // Método que permite seleccionar los objetos de la base de datos (SELECT)

    @Override
    public List<Persona> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Persona> personas = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                Persona persona = new Persona();
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setIdentificacion(rs.getString("identificacion"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setEmail(rs.getString("email"));
                personas.add(persona);
            }
        } finally {
            if (rs != null) close(rs);
            if (ps != null) close(ps);
            if (this.conexionTransaccional == null) close(conn);
        }
        return personas;
    }

    // Método para recuperar solo uno de los registros en la base de datos
    @Override
    public Persona seleccionarPorId(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Persona persona = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_SELECT_ONE);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                persona = new Persona();
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setIdentificacion(rs.getString("identificacion"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setEmail(rs.getString("email"));
            }
        } finally {
            if (rs != null) close(rs);
            if (ps != null) close(ps);
            if (this.conexionTransaccional == null) close(conn);
        }
        return persona;
    }

    // Método que permite insertar objetos en la base de datos (INSERT)
    @Override
    public int insertar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idPersonaGenerada = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_INSERT);
            ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getIdentificacion());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getEmail());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idPersonaGenerada = rs.getInt(1);
            }
            System.out.println("ID Persona generada = " + idPersonaGenerada);
        } finally {
            close(rs);
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return idPersonaGenerada;
    }

    // Método que permite actualizar objetos en la base de datos (UPDATE)
    @Override
    public int actualizar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getIdentificacion());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getEmail());
            ps.setInt(6, persona.getIdPersona());
            registros = ps.executeUpdate();
        } finally {
            if (ps != null) close(ps);
            if (this.conexionTransaccional == null) close(conn);
        }
        return registros;
    }

    // Método que permite eliminar objetos en la base de datos (DELETE)
    @Override
    public int eliminar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, persona.getIdPersona());
            registros = ps.executeUpdate();
        } finally {
            if (ps != null) close(ps);
            if (this.conexionTransaccional == null) close(conn);
        }
        return registros;
    }
}
