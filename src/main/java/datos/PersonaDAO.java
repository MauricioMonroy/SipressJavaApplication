/*
 * DAO hace referencia al patrón de diseño Data Access Object
 * Permite realizar las operaciones de la entidad seleccionada
 */
package datos;

import domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.*;

public class PersonaDAO {
    // Creación de las sentencias para recuperar la información de la base de datos
    private static final String SQL_SELECT =
            "SELECT id_persona, nombre, apellido, identificacion, telefono, email FROM persona";
    private static final String SQL_INSERT =
            "INSERT INTO persona (nombre, apellido, identificacion, telefono, email) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE persona SET nombre = ?, apellido = ?, identificacion = ?, telefono = ?, email = ? WHERE id_persona = ?";
    private static final String SQL_DELETE =
            "DELETE FROM persona WHERE id_persona = ?";

    // Método que permite seleccionar los objetos
    public List<Persona> seleccionar() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Persona> personas = new ArrayList<>();

        try {
            conn = getConnection();
            System.out.println("Ejecutando query = " + SQL_SELECT);
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            // Iteración de los elementos para obtener todos los registros de la base de datos
            while (rs.next()) {
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String identificacion = rs.getString("identificacion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                // Creación de un nuevo objeto de la clase
                var persona = new Persona();
                persona.setIdPersona(idPersona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setIdentificacion(identificacion);
                persona.setTelefono(telefono);
                persona.setEmail(email);

                personas.add(persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        // Se ejecuta el bloque finally para cerrar los objetos creados
        finally {
            close(rs);
            close(ps);
            close(conn);
        }

        return personas;
    }

    // Método que permite insertar objetos en la base de datos
    public int insertar(Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        // Especifica el número de registros que se insertan
        int registros = 0;
        try {
            conn = getConnection();
            System.out.println("Ejecutando query = " + SQL_INSERT);
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getIdentificacion());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getEmail());
            registros = ps.executeUpdate();
            System.out.println("Registros insertados = " + registros);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            close(ps);
            close(conn);
        }
        return registros;
    }

    // Método que permite actualizar objetos en la base de datos
    public int actualizar(Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        // Especifica el número de registros que se insertan
        int registros = 0;
        try {
            conn = getConnection();
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
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            close(ps);
            close(conn);
        }
        return registros;
    }

    // Método que permite eliminar objetos en la base de datos
    public int eliminar(Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        // Especifica el número de registros que se insertan
        int registros = 0;
        try {
            conn = getConnection();
            System.out.println("Ejecutando query = " + SQL_DELETE);
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, persona.getIdPersona());
            registros = ps.executeUpdate();
            System.out.println("Registros eliminados = " + registros);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            close(ps);
            close(conn);
        }
        return registros;
    }
}
