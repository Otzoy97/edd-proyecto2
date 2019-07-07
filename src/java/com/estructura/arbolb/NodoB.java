/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructura.arbolb;

import com.viaje.Destino;
import com.estructura.matriz.NodoM;
/**
 *
 * @author otzoy
 */
public class NodoB {

    /**
     * Punteros hacia el anterior o siguiente nodo
     */
    public NodoB anterior, siguiente;
    /**
     * Puntero hacia la rama izquierda o derecha
     */
    public Rama izquierda, derecha;
    /**
     * Dato que almacena el nodo
     */
    private Destino dato;
    /**
     * Puntero hacia alguna ruta;
     */
    public NodoM ruta;
    /**
     * Constructor por defecto
     *
     * @param dato_
     */
    public NodoB (Destino dato_) {
        dato = dato_;
        anterior = null;
        siguiente = null;
        izquierda = null;
        derecha = null;
        ruta = null;
    }

    /**
     * Recupera el dato almacenado en el nodo
     *
     * @return
     */
    public Destino Dato() {
        return dato;
    }

    /**
     * Sobreescribe el dato almacenado en el nodo
     *
     * @param dato_
     */
    public void Dato(Destino dato_) {
        dato = dato_;
    }
}
