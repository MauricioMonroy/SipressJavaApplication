package datos;

import domain.Empleado;

import java.sql.SQLException;
import java.util.List;

public interface EmpleadoDAO {
    List<Empleado> seleccionar() throws SQLException;

    Empleado seleccionarPorId(int id) throws SQLException;

    int insertar(Empleado empleado) throws SQLException;

    int actualizar(Empleado empleado) throws SQLException;

    int eliminar(Empleado empleado) throws SQLException;
}
