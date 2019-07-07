/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructura.matriz;

import com.estructura.arbolb.NodoB;
import com.viaje.Ruta;
import java.util.Iterator;

/**
 *
 * @author otzoy
 */
public class NodoM implements Iterable<NodoM>{
    
    /**
     * Iterador para la clase NodoM
     */
    class NodoIterator implements Iterator<NodoM>{
        /**
         * Apuntador al nodo actual
         */
        NodoM actual;
        
        /**
         * Constructor por defecto
         * @param nodo 
         */
        public NodoIterator(NodoM nodo){
            actual = nodo;
        }
        
        /**
         * 
         * @return {@code true} si el nodo actual no es nulo
         */
        @Override
        public boolean hasNext(){
            return actual != null;
        }
        
        /**
         * Retorna el nodo actual y mueve el puntero hacia el nodo siguiente
         * @return nodo actual
         */
        @Override
        public NodoM next(){
            NodoM data = actual;
            actual = actual.siguiente;
            return data;
        }
    }
    
    /**
     * Apuntadores de la matriz disperasa
     */
    public NodoM abajo, siguiente, anterior, arriba;
    /**
     * Apuntadores hacia encabezados de arbolB
     */
    public NodoB origen, destino;
    /**
     * Mantiene las coordenadas del nodo
     */
    private final int idOrigen, idDestino;
    
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
        this.idOrigen = -1;
        this.idDestino = -1;
        this.origen = null;
        this.destino = null;
    }

    /**
     * Constructor con dato a almacenar
     *
     * @param ruta
     * @param origen
     * @param destino
     */
    public NodoM(Ruta ruta, int origen, int destino) {
        this.ruta = ruta;
        arriba = null;
        abajo = null;
        anterior = null;
        siguiente = null;
        this.idOrigen = origen;
        this.idDestino = destino;
        this.origen = null;
        this.destino = null;
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
    
    /**
     * 
     * @return {@code codigo} del origen del encabezado
     */
    public int Origen(){
        return this.idOrigen;
    }
    
    /**
     * 
     * @return {@code codigo} del destino del encabezado
     */
    public int Destino(){
        return this.idDestino;
    }
    
    /**
     * 
     * @return iterador para la clase NodoM
     */
    @Override
    public Iterator<NodoM> iterator(){
        return new NodoIterator(this);
    }
}
