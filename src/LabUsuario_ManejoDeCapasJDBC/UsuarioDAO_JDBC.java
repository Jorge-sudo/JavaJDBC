package LabUsuario_ManejoDeCapasJDBC;



import static LabUsuario_TablaDeSQL.Conexion.*;
import java.sql.*;
import java.util.*;

public class UsuarioDAO_JDBC implements UsuarioDAO {

    private Connection conexionTransaccional;
    
    private static final String SQL_SELECT = "SELECT idusuario, usuario, password FROM test.usuario";
    private static final String SQL_INSERT = "INSERT INTO test.usuario(usuario,password) VALUES(?,?);";
    private static final String SQL_UPDATE = "UPDATE test.usuario SET usuario = ?,password = ? WHERE idusuario = ?;";
    private static final String SQL_DELETE = "DELETE FROM test.usuario WHERE idusuario = ?;";

    public UsuarioDAO_JDBC() {
    }
    
    //Este constructor es para manejar la transaccion desde una clase externa cuando se hara comit o rolbak
    public UsuarioDAO_JDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    @Override
    public List<UsuarioDTO> seleccionar() throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioDTO usuario = null;
        List<UsuarioDTO> personas = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            //En este caso ya no utilizamos " instruccion.executeQuery(sql);"
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("idusuario");
                String Usuario = rs.getString("usuario");
                String password = rs.getString("password");
                //Aqui es clave por que estam,os convirtiendo informacion de bases de datos a objetos de java
                usuario = new UsuarioDTO(idUsuario, Usuario, password);
                //Aqui se agrega a la lista "List" el obejto 
                personas.add(usuario);
            }
        } finally {
            try {
                //Debemos cerrar la sesion de la manera inversa a la cual se abrio
                //Como ya importamos los metodos staticos cerramos sin la necesidad 
                //de poner la palabra Conexion.close(rs);
                close(rs);
                close(stmt);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }

        return personas;
    }

    //Este metodo es INSERT=Nos permite agregar informacion a la tabla en SQL y en aqui sera insertar
    //recibiremos un objeto de tipo persona y es ese objeto que se guardara en la base de datos
    //Int para ver cuantos registros hay 
    @Override
    public int insertar(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_INSERT);
            //Aqui recibimos el obejto persona y lo añadimos a la tabla persona de SQL con el metodo get
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            //Esto es para que actualice el estado de la base de datos 
            System.out.println("ejecutando query "+ SQL_INSERT);
            registros = stmt.executeUpdate();
            System.out.println("registros afectados = " + registros);
        } finally {
            try {
                //Debemos cerrar la sesion de la manera inversa a la cual se abrio
                //Como ya importamos los metodos staticos cerramos sin la necesidad 
                //de poner la palabra Conexion.close(conn);
                close(stmt);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }

    //Este metodo es UPDATE=Nos permitira actualizar informacion de nuestra tabla
    //Regresara un numero entero para verificar cuantos registros se an actualizado
    @Override
    public int actualizar(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            System.out.println("ejecutando query "+ SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            //Sustituimos los parametros de la misma forma que insertar
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getId_Usuario());
            //Esto es para que actualice el estado de la base de datos 
            registros = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + registros);
        } finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }

    //Este metodo es para DELETE=Eliminar informacion de nuestra tabla
    //Regresara un numero entero para ver cuantos elementos fueron eliminados
    @Override
    public int eliminar(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            System.out.println("ejecutando query "+ SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            //Sustituimos los parametros de la misma forma que insertar
            stmt.setInt(1, usuario.getId_Usuario());
            //Esto es para que actualice el estado de la base de datos 
            registros = stmt.executeUpdate();
            System.out.println("Registros Eliminados: " + registros);
        } finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }
}
