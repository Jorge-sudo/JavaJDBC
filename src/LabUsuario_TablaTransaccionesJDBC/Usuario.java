
package LabUsuario_TablaTransaccionesJDBC;


public class Usuario {
    //Aqui ya tenemos definidos los atributos que representa a la tabla que tenemos en la base de datos
    private int id_Usuario;
    private String usuario;
    private String password;

            //AQUI IMPLEMENTAMOS LA SOBRE CARGA DE CONSTRUCTORES 
    
    //Agregamos las constructores
    public Usuario() {
    }

    //Aqui solo agregamos la idPersona para poder eliminar la persona con solo la ID
    public Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    //Este constructor es para poder insertar un nuevo objeto de tipo Usuario
    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    //Si queremos modificar(Actualizar) un registro creamos este constructor que tenga todos los atributos 
    public Usuario(int id_Usuario, String usuario, String password) {
        this.id_Usuario = id_Usuario;
        this.usuario = usuario;
        this.password = password;
    }

        //Si queremos modificar algun dato en espesifico creamos los metodos GET y SET
    
    public int getId_Usuario() {
        return this.id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

        //Agregamos el metodo toString para que mandemos a imprimir el estado del objeto en cualquier momento
    @Override
    public String toString() {
        return "Usuario{" + "id_Usuario=" + id_Usuario + ", usuario=" + usuario + ", password=" + password + '}';
    }
    
}
