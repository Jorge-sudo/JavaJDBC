package Persona_TablaTransaccionesJDBC;

//Importamos la clase static para cerrar la sesion con SQL 
import static Persona_TablaDeSQL.Conexion.*;
import java.sql.*;
import java.util.*;

//ESTO SE LLAMA DAO-DATA ACCES OBJECT
//Esta clase es para actualizar insertar eliminar registros de la tabla persona
//Como ejemplo "SELECT - INSERT - DELETE " sobre la tabla persona 
public class PersonaJDBC implements PersonaDAO{

    //Este atributo es para manejar la transaccion desde una clase externa 
    private Connection conexionTransaccional;

    //Tambien se puede "SQL_SELECT = "SELECT * FROM test.persona";" 
    //Este atributo SQL_SELECT es el select de la base de datos
    private static final String SQL_SELECT = "SELECT id_persona, nombre, epellido, email, telefono FROM test.persona";
    //Agregamos este atributo para el metodo imprimir que es INSERT=Agregamos informacion a la tabla
    //En este caso insertaremos un obejto con datos asi que para eso ponemos asi "VALUES(?,?,?,?)" 
    private static final String SQL_INSERT = "INSERT INTO test.persona(nombre,epellido,email,telefono) VALUES(?,?,?,?);";
    //Agregamos este atributo para el metodo actualizar que es UPDATE=Nos permitira actualizar informacion de nuestra tabla
    private static final String SQL_UPDATE = "UPDATE test.persona SET nombre = ?,epellido = ?,email = ?,telefono = ? WHERE id_persona = ?;";
    //Agregamos este atributo para el metodo eliminar que es DELETE=Eliminar informacion de nuestra tabla
    private static final String SQL_DELETE = "DELETE FROM test.persona WHERE id_persona = ?;";

    public PersonaJDBC() {
    }

    //Este constructor es para manejar la transaccion desde una clase externa cuando se hara comit o rolbak
    public PersonaJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    //SELECT=Nos permite recuperar registros de la tabla en este caso sera el metodo seleccionar
    
                        //Con throws Exception propagamos la Exception para la siguiente clase que utilice este metodo
    @Override
    public List<Persona> seleccionar() throws SQLException {
        //Aqui solo definimos las variables que vamos a necesitar 
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            //Si conexion transaccional es diferente a null entonces conn = this.conexionTransaccional
            //Si conexion transaccional es igual a null entonces conn = getConection();
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            //En este caso ya no utilizamos " instruccion.executeQuery(sql);"
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("epellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                //Aqui es clave por que estam,os convirtiendo informacion de bases de datos a objetos de java
                persona = new Persona(idPersona, nombre, apellido, email, telefono);
                //Aqui se agrega a la lista "List" el obejto 
                personas.add(persona);
            }
        }  finally {
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
                         //Con throws Exception propagamos la Exception para la siguiente clase que utilice este metodo
    @Override
    public int insertar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_INSERT);
            //Aqui recibimos el obejto persona y lo añadimos a la tabla persona de SQL con el metodo get
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            //Esto es para que actualice el estado de la base de datos 
            registros = stmt.executeUpdate();
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
    public int actualizar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            //Sustituimos los parametros de la misma forma que insertar
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());

            //Esto es para que actualice el estado de la base de datos 
            registros = stmt.executeUpdate();
        }  finally {
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
    
                    //Con throws Exception propagamos la Exception para la siguiente clase que utilice este metodo
    @Override
    public int eliminar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_DELETE);
            //Sustituimos los parametros de la misma forma que insertar
            stmt.setInt(1, persona.getIdPersona());
            //Esto es para que actualice el estado de la base de datos 
            registros = stmt.executeUpdate();
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
