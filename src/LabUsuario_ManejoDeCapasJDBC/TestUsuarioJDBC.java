package LabUsuario_ManejoDeCapasJDBC;

import LabUsuario_TablaTransaccionesJDBC.*;
import java.sql.*;
import java.util.List;


public class TestUsuarioJDBC {
    public static void main(String[] args) {
       Connection conexion = null;
        try {
            conexion = Conexion.getConection();
            if (conexion.getAutoCommit()) {
                //Esto hacemos para el AutoCommit no se inicialice solo ya que nosotros lo haremos manualmente 
                conexion.setAutoCommit(false);
            }
            //No queremos que se guarden los cambios hasta que nosotros hagamos commit
            UsuarioDAO personaJdbc = new UsuarioDAO_JDBC(conexion);
            List<UsuarioDTO> personas = personaJdbc.seleccionar();
            for (UsuarioDTO usuario:personas) {
                System.out.println("usuario = " + usuario);
            }
            
            conexion.commit();
            System.out.println("Se hizo commit: No ocurrio ningun error por lo cual si se realizo todos los cambios");
        } catch (SQLException ex) {
            System.out.println("Entramos al Rollback: Ocurrio algun error por lo cual no se guardo ningun cambios");
            ex.printStackTrace(System.out);
            try {
                //Perso si sale un error entonces rollback se ejecutara y no se realizara ningun cambio 
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
        
    }
    
}
