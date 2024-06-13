package datos;

import domain.Funcion;

import java.sql.SQLException;
import java.util.List;

public interface FuncionDAO {
    List<Funcion> seleccionar() throws SQLException;

    Funcion seleccionarPorId(int id) throws SQLException;

    int insertar(Funcion funcion) throws SQLException;

    int actualizar(Funcion funcion) throws SQLException;

    int eliminar(Funcion funcion) throws SQLException;
}
