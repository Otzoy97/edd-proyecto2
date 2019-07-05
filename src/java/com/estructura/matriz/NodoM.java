/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructura.matriz;

import com.viaje.Ruta;

/**
 *
 * @author otzoy
 */
public class NodoM {

    /**
     * Apuntadores de la matriz disperasa
     */
    public NodoM abajo, siguiente, anterior, arriba;
    
    /**
     * Mantiene las coordenadas del nodo
     */
    private int origen, destino;
    
    /**
     * Dato que almacena el nodo
     */
    private Ruta ruta;

    /**
     * Constructor por defecto
     */
    public NodoM() {
        ruta = null;
        abajo = null;
        siguiente = null;
        anterior = null;
        arriba = null;
    }

    /**
     * Constructor con dato a almcenar
     *
     * @param ruta_
     */
    public NodoM(Ruta ruta_) {
        ruta = ruta_;
        abajo = null;
        siguiente = null;
    }

    /**
     *
     * @return la ruta almacenada en el nodo
     */
    public Ruta Ruta() {
        return ruta;
    }

    /**
     *
     * @param ruta_ la nueva ruta a almacenar en el nodo
     */
    public void Ruta(Ruta ruta_) {
        ruta = ruta_;
    }
}
