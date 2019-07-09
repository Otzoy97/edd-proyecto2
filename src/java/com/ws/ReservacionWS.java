/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws;

import com.estructura.hash.TablaHash;
import com.estructura.lista.Lista;
import com.viaje.Archivo;
import com.viaje.Reservacion;
import static com.ws.DestinoWS.m;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author otzoy
 */
@WebService(serviceName = "ReservacionWS")
public class ReservacionWS {
    public static TablaHash th = new TablaHash();
    
    /**
     * Agrega una nueva reservación
     * @param llave
     * @param nombreCliente
     * @param costo
     * @param tiempo
     * @param viaje
     * @return 
     */
    @WebMethod(operationName = "agregarReservacion")
    public String agregarReservacion( @WebParam(name = "llave") int llave, @WebParam(name = "nombreCliente") String nombreCliente , @WebParam(name = "costo") float costo, @WebParam(name = "tiempo") float tiempo, @WebParam(name = "viaje") Lista<Integer> viaje){
        try{
            Lista<String>  l = new Lista();
            for(Integer i : viaje){
                l.agregarAlFinal(m.nombreDestino(i));
            }
            th.insertar(llave, new Reservacion(nombreCliente, costo, tiempo, l));
        } catch (Exception ex){
            return String.format("Ocurrió un error al intentar crear la reservacion");
        }
        return String.format("Reservación creada existosamente");
    }
    
    /**
     * Elimina una reservacion con la llave dada
     * @param llave 
     * @return  
     */
    @WebMethod(operationName = "eliminarReservacionConLlave")
    public String eliminarReservacionConLlave(@WebParam(name = "llave") int llave){
        try{
            th.eliminar(llave);
        } catch (Exception ex){
            return String.format("Ocurrió un error al intentar eliminar la reservación");
        }
        return String.format("Reservación eliminada exitosamente");
    }
    
    /**
     * 
     * @param llave
     * @return {@code true} si la llave existe en la tabla hash
     */
    @WebMethod(operationName = "verificarLlave")
    public boolean verificarLlave(@WebParam(name = "llave") int llave){
        return th.existeLlave(llave);
    }
    
    /**
     * Recupera una reservación con llave {@code llave}
     * @param llave
     * @return 
     */
    @WebMethod(operationName = "reservacionConLlave")
    public Reservacion reservacionConLlave(@WebParam(name = "llave") int llave){
        return th.reservacion(llave);
    }
    
    /**
     * Recupera un arreglo con la información de todas las reservaciones que el
     * cliente {@code nombre} haya realizado
     * 
     * @param nombre
     * @return 
     */
    @WebMethod(operationName = "reservacionesDelCliente")
    public Reservacion[] reservacionesDelCliente( @WebParam(name = "nombre") String nombre){
        return th.reservacionPorCliente(nombre);
    }
    
    /**
     * 
     * @return Base64 de la representación de memoria de la tabla hash
     */
    @WebMethod(operationName = "generarTablaHash")
    public String generarTablaHash(){
        //Recupera el script de la matriz
        String str = th.dot();
        //Escriba un archivo con nombre -dotMB- y extensión -.dot-
        Archivo.escribirArchivo(str,"dotHT", ".dot");
        //Ejecuta un comando en el cmd
        Archivo.generarGrafico("neato -Tbmp dotHT.dot -o HT.bmp");
        //Elimina el archivo -dotMB-
        Archivo.eliminarArchivo("dotHT.dot");
        //Genera un base65;
        return Archivo.toBase64("HT.bmp");
    }
}
