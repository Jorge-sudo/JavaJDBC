
package Persona_TablaTransaccionesJDBC;

import java.sql.*;


public class TestPersonaJDBC {
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConection();
            if (conexion.getAutoCommit()) {
                //Esto hacemos para el AutoCommit no se inicialice solo ya que nosotros lo haremos manualmente 
                conexion.setAutoCommit(false);
            }
            //No queremos que se guarden los cambios hasta que nosotros hagamos commit
            PersonaJDBC personaJdbc = new PersonaJDBC(conexion);
            Persona cambioPersona = new Persona(14, "Karla ivon", "Quispe", "josefaquispe@gmail.com", "123456");
            personaJdbc.actualizar(cambioPersona);
            
            Persona personaInsert= new Persona("Carlos", "Ramirez", null, null);
            //Esto se agrega para probocar un error por que en email solo nos acepta 45 caracteres y aqui lo estamos pasando
            
            //personaInsert.setEmail("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            
            //Para que funciones debemos quitar los catch de la clase PersonaJDBC y 
            // Reportar la Ecxeption "throws SQLException"
            personaJdbc.insertar(personaInsert);
            
            //Si todo el codigo esta bien y no hay error enntonces commit se ejecutara y se ¿guardara los cambios
            conexion.commit();
            System.out.println("Se hizo commit en la transaccion");
        } catch (SQLException ex) {
            System.out.println("Entramos al Rollback");
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
