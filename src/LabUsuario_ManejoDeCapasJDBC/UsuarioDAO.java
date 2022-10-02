
package LabUsuario_ManejoDeCapasJDBC;

import java.sql.*;
import java.util.*;


public interface UsuarioDAO {
    
    public List<UsuarioDTO> seleccionar()throws SQLException ;
    
    public int insertar (UsuarioDTO usuario)throws SQLException ;
    
    public int actualizar(UsuarioDTO usuario)throws SQLException ;
    
    public int eliminar(UsuarioDTO usuario)throws SQLException ;
}
