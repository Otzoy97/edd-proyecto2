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
import com.viaje.Ruta;

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
     * Agrega una nueva ruta
     * @param origen
     * @param destino
     * @param ruta
     * @return
     */
    @WebMethod(operationName = "agregarRuta")
    public String agregarRuta(@WebParam(name = "origen") int origen,@WebParam(name = "destino") int destino,@WebParam(name = "ruta") Ruta ruta){
        try{
            m.agregarRuta(origen, destino, ruta);
            m.agregarRuta(destino, origen, ruta);
        } catch (Exception ex){
            return ex.getMessage();
        }
        return String.format("Ruta (%d, %d) agregada exitosamente", origen, destino);
    }
    
    /**
     * Elimina la ruta en la coordenada ({@code origen}, {@code destino})
     * @param origen
     * @param destino
     * @return 
     */
    @WebMethod(operationName = "eliminarRuta")
    public String eliminarRuta(@WebParam(name = "origen") int origen,@WebParam(name = "destino") int destino){
        try{
            m.eliminar(origen, destino);
            m.eliminar(destino, origen);
        } catch (Exception ex){
            return ex.getMessage();
        }
        return String.format("Ruta (%d, %d) eliminada exitosamente", origen, destino);
    }
    
    /**
     * 
     * @return Base64 de la imagen de la matriz y encabezado del árbol B
     */
    @WebMethod(operationName = "generarMatriz")
    public String generarMatriz(){
        //Recupera el script de la matriz
        String str = m.graficarMB();
        //Escriba un archivo con nombre -dotMB- y extensión -.dot-
        Archivo.escribirArchivo(str,"dotMB", ".dot");
        //Ejecuta un comando en el cmd
        Archivo.generarGrafico("neato -Tpng:cairo:gd dotMB.dot -o MB.png");
        //Elimina el archivo -dotMB-
        Archivo.eliminarArchivo("dotMB.png");
        //Genera un base65;
        return Archivo.toBase64("MB.png");
    }
    
    /**
     * 
     * @return base64 de la imagen del árbol B
     */
    @WebMethod(operationName = "generarArbol")
    public String generarArbol(){
        //Recupera el script de la matriz
        String str = m.graficarMB();
        //Escriba un archivo con nombre -dotMB- y extensión -.dot-
        Archivo.escribirArchivo(str,"dotTB", ".dot");
        //Ejecuta un comando en el cmd
        Archivo.generarGrafico("neato -Tpng:cairo:gd dotTB.dot -o TreeB.png");
        //Elimina el archivo -dotMB-
        Archivo.eliminarArchivo("TreeB.png");
        //Genera un base65;
        return Archivo.toBase64("MB.png");
    }
}
