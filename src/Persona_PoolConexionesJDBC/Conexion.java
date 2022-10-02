package Persona_PoolConexionesJDBC;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class Conexion {

            //CON ESTA CLASE PODREMOS REUTILIZAR EL METODO DE CONECCTAR
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "danay hija";
    private static BasicDataSource dataSource;

    //Creamos este metodo para inicializar el "POOL DE CONEXIONES" y utilizaremos este mismo metodo para solicitar 
    //La conexion a la base de datos
    public static DataSource getDataSource(){
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            //Definimos el tamaño inicial del pool de conexiones
            dataSource.setInitialSize(5);//Debemos tener cuidado aqui pero comenzaremos con 5
        }
        return dataSource;
    }
    public static Connection getConection() throws SQLException {
        //En este caso ya no encerramos en un try y catch sinos reportamos la Exception "throws SQLException" 
        return getDataSource().getConnection();
    }

    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close(Statement smtm)  {
        try {
            smtm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Utilizamos preparedStatement por que es mejor que el Statement asi que lo utilizaremos 
    public static void close(PreparedStatement smtm){
        try {
            smtm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
