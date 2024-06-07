package datos;

import domain.PersonaDTO;

import java.sql.SQLException;
import java.util.List;

public interface PersonaDAO {
    List<PersonaDTO> seleccionar() throws SQLException;
    int insertar(PersonaDTO personaDTO) throws SQLException;
    int actualizar(PersonaDTO personaDTO) throws SQLException;
    int eliminar(PersonaDTO personaDTO) throws SQLException;
}
