package LabUsuario_TablaTransaccionesJDBC;

import java.sql.*;


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
            UsuarioJDBC personaJdbc = new UsuarioJDBC(conexion);
            Usuario usuarioCambio = new Usuario(4, "jorge", "123456");
            personaJdbc.actualizar(usuarioCambio);
            
            Usuario usuarioInsert= new Usuario("Jose Maria", null);
            
            //Esto se agrega para probocar un error por que en email solo nos acepta 45 caracteres y aqui lo estamos pasando
            //usuarioInsert.setPassword("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            
            //Para que funciones debemos quitar los catch de la clase PersonaJDBC y 
            // Reportar la Ecxeption "throws SQLException"
            personaJdbc.insertar(usuarioInsert);
            
            //Si todo el codigo esta bien y no hay error enntonces commit se ejecutara y se ¿guardara los cambios
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
