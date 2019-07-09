/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws;

import com.estructura.lista.Lista;
import com.viaje.Archivo;
import com.viaje.Ruta;
import com.viaje.RutaD;
import static com.ws.DestinoWS.m;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author otzoy
 */
@WebService(serviceName = "RutaWS")
public class RutaWS {

    /**
     * 
     * @param origen
     * @param destino
     * @param costoRuta
     * @param tiempoRuta
     * @param piloto
     * @return 
     */
    @WebMethod(operationName = "agregarRuta")
    public String agregarRuta(@WebParam(name = "origen") int origen,@WebParam(name = "destino") int destino, @WebParam(name = "costoRuta") float costoRuta, @WebParam(name = "tiempoRuta") float tiempoRuta, @WebParam(name = "piloto") String piloto){
        try{
            m.agregarRuta(origen, destino, new Ruta(costoRuta, tiempoRuta, piloto));
            m.agregarRuta(destino, origen, new Ruta(costoRuta, tiempoRuta, piloto));
        } catch (Exception ex){
            return String.format(ex.getMessage());
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
            return String.format(ex.getMessage());
        }
        return String.format("Ruta (%d, %d) eliminada exitosamente", origen, destino);
    }
    
    /**
     * Lista de listas con todas las posibles rutas desde origen hasta destino
     * @param origen
     * @param destino 
     * @return  
     */
    @WebMethod(operationName = "generarViajes")
    public Lista<Lista<RutaD>> generarViajes(@WebParam(name = "origen") int origen,@WebParam(name = "destino") int destino){
        return m.generarViajes(origen, destino);
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
        Archivo.generarGrafico("neato -Tbmp dotMB.dot -o MB.bmp");
        //Elimina el archivo -dotMB-
        Archivo.eliminarArchivo("dotMB.dot");
        //Genera un base65;
        return Archivo.toBase64("MB.bmp");
    }
}
