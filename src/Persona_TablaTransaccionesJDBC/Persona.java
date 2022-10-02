
package Persona_TablaTransaccionesJDBC;

//Primero aqui implememtaremos todo lo aprendido con obejtos, en java se maneja como siempre lo manejamos pero 

//Primero aqui implememtaremos todo lo aprendido con obejtos, en java se maneja como siempre lo manejamos pero 
//En base de datos es diferente y se tiene que respetar el buen manejo de cada lenguaje 

public class Persona {
    
    //Aqui ya tenemos definidos los atributos que representa a la tabla que tenemos en la base de datos
    private int idPersona;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    
            //AQUI IMPLEMENTAMOS LA SOBRE CARGA DE CONSTRUCTORES 
    
    //Agregamos las constructores
    public Persona(){
        
    }
    
    //Aqui solo agregamos la idPersona para poder eliminar la persona con solo la ID
    public Persona(int idPersona){
        this.idPersona=idPersona;
    }
    
    public Persona(int idPersona, String nombre){
        this.idPersona=idPersona;
        this.nombre=nombre;
    }

    //Este constructor es para poder insertar un nuevo objeto de tipo Persona 
    public Persona(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    
    //Si queremos modificar un registro creamos este constructor que tenga todos los atributos 
    public Persona(int idPersona, String nombre, String apellido, String email, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    
        //Si queremos modificar algun dato en espesifico creamos los metodos GET y SET

    public int getIdPersona() {
        return this.idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

        //Agregamos el metodo toString para que mandemos a imprimir el estado del objeto en cualquier momento 
    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombre=" +
                nombre + ", apellido=" + apellido + ", email=" + email + 
                ", telefono=" + telefono + '}';
    }
    
}
