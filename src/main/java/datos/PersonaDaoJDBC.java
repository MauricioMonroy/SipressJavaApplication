/*
 * DAO hace referencia al patrón de diseño Data Access Object
 * Permite realizar las operaciones de la entidad seleccionada
 */
package datos;

import domain.Persona;
import domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<Persona> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Persona> personas = new ArrayList<>();
        Persona personaActual = null;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_SELECT);
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            // Iteración de los elementos para obtener todos los registros de la base de datos
            while (rs.next()) {
                int idPersona = rs.getInt("id_persona");

                if (personaActual == null || personaActual.getIdPersona() != idPersona) {

                    // Creación de un nuevo objeto de la clase
                    personaActual = new Persona();
                    personaActual.setIdPersona(idPersona);
                    personaActual.setNombre(rs.getString("nombre"));
                    personaActual.setApellido(rs.getString("apellido"));
                    personaActual.setIdentificacion(rs.getString("identificacion"));
                    personaActual.setTelefono(rs.getString("telefono"));
                    personaActual.setEmail(rs.getString("email"));

                    personas.add(personaActual);
                }

            }

        }
        // Se ejecuta el bloque finally para cerrar la conexión
        finally {
            if (rs != null) {
                close(rs);
            }
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }

        return personas;
    }

    // Método que permite insertar objetos en la base de datos (INSERT)
    public int insertar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_INSERT);
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getIdentificacion());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getEmail());
            registros = ps.executeUpdate();
            System.out.println("Registros insertados = " + registros);
        }
        // Se ejecuta el bloque finally para cerrar la conexión
        finally {
            assert ps != null;
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }

    // Método que permite actualizar objetos en la base de datos (UPDATE)
    public int actualizar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_UPDATE);
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getIdentificacion());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getEmail());
            ps.setInt(6, persona.getIdPersona());
            registros = ps.executeUpdate();
            System.out.println("Registros actualizados = " + registros);
        }
        // Se ejecuta el bloque finally para cerrar la conexión
        finally {
            assert ps != null;
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }

    // Método que permite eliminar objetos en la base de datos (DELETE)
    public int eliminar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query = " + SQL_DELETE);
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, persona.getIdPersona());
            registros = ps.executeUpdate();
            System.out.println("Registros eliminados = " + registros);
        }
        // Se ejecuta el bloque finally para cerrar la conexión
        finally {
            assert ps != null;
            close(ps);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }
}
