package LabUsuario_TablaDeSQL;

import java.util.*;

public class TestUsuarioJDBC {
    public static void main(String[] args) {
        System.out.println("    METODO SELECT ver registro de la tabla ");
            //PONEMOS EN PRUEBA LA PALABRA SELECT=Nos permite recuperar registros de la tabla 
                    //Metodo "seleccionar" de la clase PersonaDAO
        //Aqui PersonaDAO tendria que ser de tipo interface es una buena practica 
        imprimir();
        
        System.out.println("    METODO INSERT agregar informacion a la tabla ");
            //PONEMOS A PRUEBA LA PALABRA INSERT=Nos permite agregar informacion a la tabla
                        //Metodo "Insertar" de la clase PersonaDAO
        //Insertando un nuevo obejto de tipo persona
        UsuarioJDBC usuarioDao =  new UsuarioJDBC();
        Usuario usuarioNuevo = new Usuario("jorge ", "jorge123");
        usuarioDao.insertar(usuarioNuevo);
        //Imprimimos lo la tabla actualizado para ver que si se agrego el objeto
        imprimir();
        
        System.out.println("    METODO UPDATE actualizar informacion de nuestra tabla ");
            //PONEMOS A PRUEBA LA PALABRA UPDATE=Nos permitira actualizar informacion de nuestra tabla
                    //Metodo "actualizar" de la clase PersonaDAO
        Usuario usuarioModificar = new Usuario(1, "jose", "jose12345");
        usuarioDao.actualizar(usuarioModificar);
        imprimir();
        
        System.out.println("    METODO DELETE Eliminar informacion de nuestra tabla ");
            //PONEMOS A PRUEBA LA PALABRA DELETE=Eliminar informacion de nuestra tabla 
                    //Metodo "Eliminar" de la clase PersonaDAO
        Usuario usuarioEliminar = new Usuario(2);
        usuarioDao.eliminar(usuarioEliminar);
        imprimir();
        
        
    }
    public static void imprimir(){
        UsuarioJDBC usuarioDao =  new UsuarioJDBC();
        List<Usuario> usuarios = usuarioDao.seleccionar();
        
        usuarios.forEach((usuario) -> {
            System.out.println("usuario = " + usuario);
        });
        System.out.println("");
        
        /*Tambien se puede usando forEahc
        for(Usuario usuario:usuarios){
            System.out.println("usuario = " + usuario);
        }*/
        
    }
}
