
package Persona_TablaTransaccionesJDBC;

import java.sql.SQLException;
import java.util.List;

public interface PersonaDAO {
    public List<Persona> seleccionar() throws SQLException;
    public int insertar(Persona persona) throws SQLException;
    public int actualizar(Persona persona) throws SQLException;
    public int eliminar(Persona persona) throws SQLException;
}
