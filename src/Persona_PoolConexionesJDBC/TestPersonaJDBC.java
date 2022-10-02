
package Persona_PoolConexionesJDBC;


import java.sql.*;
import java.util.*;


public class TestPersonaJDBC {
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConection();
            if (conexion.getAutoCommit()) {
                //Esto hacemos para el AutoCommit no se inicialice solo ya que nosotros lo haremos manualmente 
                conexion.setAutoCommit(false);
            }
            
            //Aqui le asignamos la variable a una clase tipo interface ya que necesitamos lo mas generico posible
            PersonaDAO personaDao = new PersonaDAO_JDBC(conexion);
            List<PersonaDTO> personas = personaDao.seleccionar();
            for (PersonaDTO persona:personas) {
                System.out.println("persona = " + persona);
            }
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
