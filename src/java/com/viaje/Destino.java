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
public class Destino {
    /**
     * Almacena el codigo del destino
     */
    private final int codigo;
    /**
     * Almacen el nombre del destino
     */
    private final String nombre;
    /**
     * Constructor por defecto
     */
    public Destino(){
        codigo = 0;
        nombre = null;
    }
    /**
     * Constructor con codigo y nombre
     * @param codigo_
     * @param nombre_ 
     */
    public Destino(int codigo_, String nombre_){
        codigo = codigo_;
        nombre = nombre_;
    }
    /**
     * 
     * @return el código del destino
     */
    public int Codigo(){
        return codigo;
    }
    /**
     * 
     * @return el nombre del destino
     */
    public String Nombre(){
        return nombre;
    }
}
