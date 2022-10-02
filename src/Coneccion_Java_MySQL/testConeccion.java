package Coneccion_Java_MySQL;

import java.sql.*;

public class testConeccion {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        try {
            //La linea de abajo no es requerida para aplicaciones normales pero para
            //aplicaciones web si es requerida pero ahora no la necesitamos
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "danay hija");
            //Esto es una instruccion, creamos un obejto de tipo Statement que es de tipo interface 
            //Este mismo metodo sirve para otra base de datos como oracle y mas
            Statement instruccion = conexion.createStatement();
            //Este String es lenguaje SQL
            String sql = "SELECT id_Persona, nombre, epellido, email, telefono FROM test.persona";
            ResultSet resultado = instruccion.executeQuery(sql);
            //"Resultado.next()" Si tenemos elementos a iterar regresa "true" y si no "false"
            while (resultado.next()) {
                //Aqui indicamos ya no por numero de columna sinos nombre de la columna 
                System.out.println("Id Persona: " + resultado.getInt("id_Persona") + " - "
                        + resultado.getString("nombre") + " - " + resultado.getString("epellido")
                        + " - " + resultado.getString("email") + " - " + resultado.getInt("telefono"));
            }
            //Aqui cerramos todos los obejtos que abrimos, pero los cerramos inversamente osea primero resultado despues ect.
            resultado.close();
            instruccion.close();
            conexion.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(System.out);
        }
    }
}

// Al crear tabla en la base de datos idPersona = "PK = Llave primaria  NN = No nulo  IA = Auto incrementable" 