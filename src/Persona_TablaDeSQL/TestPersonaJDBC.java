
package Persona_TablaDeSQL;

import java.util.*;


public class TestPersonaJDBC {
    public static void main(String[] args) {
        System.out.println("    METODO SELECT ver registro de la tabla ");
            //PONEMOS EN PRUEBA LA PALABRA SELECT=Nos permite recuperar registros de la tabla 
                    //Metodo "seleccionar" de la clase PersonaJDBC
        //Aqui PersonaJDBC tendria que ser de tipo interface es una buena practica 
        imprimir();
        
        System.out.println("    METODO INSERT agregar informacion a la tabla ");
            //PONEMOS A PRUEBA LA PALABRA INSERT=Nos permite agregar informacion a la tabla
                        //Metodo "Insertar" de la clase PersonaJDBC
        //Insertando un nuevo obejto de tipo persona
        PersonaJDBC personaDao =  new PersonaJDBC();
        Persona personaNueva = new Persona("Carlos", "Esparza", "cesparza@gmail.com", "56882623");
        //personaDao.insertar(personaNueva);
        //Imprimimos lo la tabla actualizado para ver que si se agrego el objeto
        imprimir();
        
        System.out.println("    METODO UPDATE actualizar informacion de nuestra tabla ");
            //PONEMOS A PRUEBA LA PALABRA UPDATE=Nos permitira actualizar informacion de nuestra tabla
                    //Metodo "actualizar" de la clase PersonaJDBC
        Persona personaModificar = new Persona(14, "Josefa", "Quispe", "josefaquispe@gmail.com", "33838377983");
        personaDao.actualizar(personaModificar);
        imprimir();
        
        System.out.println("    METODO DELETE Eliminar informacion de nuestra tabla ");
            //PONEMOS A PRUEBA LA PALABRA DELETE=Eliminar informacion de nuestra tabla 
                    //Metodo "Eliminar" de la clase PersonaJDBC
        Persona personaEliminar = new Persona(16);
        personaDao.eliminar(personaEliminar);
        imprimir();
                    
    }
    
    
    public static void imprimir(){
        PersonaJDBC personaDao =  new PersonaJDBC();
        List<Persona> personas = personaDao.seleccionar();
        
        personas.forEach((persona) -> {
            System.out.println("persona = " + persona);
        });
        System.out.println("");
        
        /*Tambien se puede imprimir con forEach
        for(Persona persona:personas){
            System.out.println("persona = " + persona);
        }*/
        
    }
}
