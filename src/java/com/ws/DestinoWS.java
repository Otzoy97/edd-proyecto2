/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import com.estructura.matriz.Matriz;
import com.viaje.Archivo;

/**
 * Se encarga de las operaciones de 
 * @author otzoy
 */
@WebService(serviceName = "DestinoWS")
public class DestinoWS {
    /**
     * Matriz de origenes y destinos
     */
    public static Matriz m = new Matriz();
    
    /**
     * Agrega un nuevo encabezado a la matriz
     * @param codigo identifica de forma única al destino
     * @param nombre 
     * @return  
     */
    @WebMethod(operationName = "agregarDestino")
    public String agregarDestino(@WebParam(name = "codigo") int codigo, @WebParam(name = "nombre") String nombre){
        m.agregarDestino(codigo, nombre);
        return String.format("Destino (%d, %s) agregado exitosamente", codigo, nombre);
    }
            
    /**
     * 
     * @return base64 de la imagen del árbol B
     */
    @WebMethod(operationName = "generarArbol")
    public String generarArbol(){
        //Recupera el script de la matriz
        String str = m.graficarB_Arbol();
        if(str==null)return "";
        if(str.equals("")) return "";
        //Escriba un archivo con nombre -dotMB- y extensión -.dot-
        Archivo.escribirArchivo(str,"dotTB", ".dot");
        //Ejecuta un comando en el cmd
        Archivo.generarGrafico("dot -Tbmp dotTB.dot -o TreeB.bmp");
        //Elimina el archivo -dotMB-
        Archivo.eliminarArchivo("dotTB.dot");
        //Genera un base65;
        return Archivo.toBase64("TreeB.bmp");
    }
}
