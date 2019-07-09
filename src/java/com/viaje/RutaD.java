/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viaje;

/**
 * Almacena el nombre y una ruta para generar el grafo de rutas
 * @author otzoy
 */
public class RutaD {
    public int destino;
    public Ruta ruta;
    
    /**
     * Constructor por defecto
     * @param destino
     * @param ruta 
     */
    public RutaD(int destino, Ruta ruta) {
        this.destino = destino;
        this.ruta = ruta;
    }
    
}
