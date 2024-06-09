package datos;

import domain.Paciente;

import java.sql.SQLException;
import java.util.List;

public interface PacienteDAO {
    List<Paciente> seleccionar() throws SQLException;

    int insertar(Paciente paciente) throws SQLException;

    int actualizar(Paciente paciente) throws SQLException;

    int eliminar(Paciente paciente) throws SQLException;
}
