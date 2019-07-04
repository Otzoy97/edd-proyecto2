/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viaje;

/**
 *
 * @author otzoy
 */
public class Ruta {
    private float costo, tiempo;
    private String piloto;
    /**
     * Constructor por defecto
     */
    public Ruta(){
        costo = 0;
        tiempo = 0;
        piloto = null;
    }
    /**
     * 
     * @param costo_
     * @param tiempo_
     * @param piloto_ 
     */
    public Ruta(float costo_, float tiempo_, String piloto_){
        costo = costo_;
        tiempo = tiempo_;
        piloto = piloto_;
    }
    /**
     * 
     * @return el costo de la ruta (en quetzales)
     */
    public float Costo(){
        return costo;
    }
    /**
     * 
     * @return el tiempo de la ruta (en minutos)
     */
    public float Tiempo(){
        return tiempo;
    }
    /**
     * Modifica el nombre del piloto
     * @param piloto_ 
     */
    public void Piloto(String piloto_){
        piloto = piloto_;
    }
    /**
     * Modifica el costo de la ruta (en quetzales)
     * @param costo_ 
     */
    public void Costo(float costo_){
        costo = costo_;
    }
    /**
     * Modifica el tiempo de la ruta (en minutos)
     * @param tiempo_ 
     */
    public void Tiempo(float tiempo_){
        tiempo = tiempo_;
    }
}
