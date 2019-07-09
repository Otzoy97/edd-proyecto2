/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viaje;
import com.estructura.lista.Lista;
/**
 *
 * @author otzoy
 */
public class Reservacion {
    private final String nombreCliente; 
    private final float costo, tiempo;
    private final Lista<String> viaje;
    
    /**
     * 
     */
    public Reservacion(){
        nombreCliente = null;
        costo = 0;
        tiempo = 0;
        viaje = null;
    }
    /**
     * 
     * @param nombreCliente
     * @param costo
     * @param tiempo
     * @param viaje 
     */
    public Reservacion(String nombreCliente,  float costo, float tiempo, Lista<String> viaje) {
        this.nombreCliente = nombreCliente;
        this.costo = costo;
        this.tiempo = tiempo;
        this.viaje = viaje;
    }   
    /**
     * 
     * @return el costo del viaje (en quetzales)
     */
    public float Costo(){
        return costo;
    }
    /**
     * 
     * @return el tiempo del viaje (en minutos)
     */
    public float Tiempo(){
        return tiempo;
    }
    /**
     * 
     * @return el nombre del cliente
     */
    public String Cliente(){
        return nombreCliente;
    }
    
    /**
     * 
     * @return el viaje de la reservación
     */
    public Lista<String> Viaje(){
        return viaje;
    }
}
