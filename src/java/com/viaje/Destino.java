/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viaje;

import com.estructura.matriz.NodoM;

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
     * Apuntador hacia alguna ruta relacionada con el destino;
     */
    /**
     * Constructor por defecto
     */
    public Destino() {
        this.codigo = 0;
        this.nombre = null;
    }

    /**
     * Constructor con codigo y nombre
     *
     * @param codigo
     * @param nombre
     */
    public Destino(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     *
     * @return el código del destino
     */
    public int Codigo() {
        return codigo;
    }

    /**
     *
     * @return el nombre del destino
     */
    public String Nombre() {
        return nombre;
    }
}
