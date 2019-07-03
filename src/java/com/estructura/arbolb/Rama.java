/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructura.arbolb;

/**
 *
 * @author otzoy
 */
public class Rama<T> {
    class Nodo<T>{
        public Nodo anterior, siguiente, izquierda, derecha;
        private T dato;
        public Nodo(T dato_){
            dato = dato_;
        }
        /**
         * Recupera el dato almacenado en el nodo
         * @return 
         */
        public T Dato(){
            return dato;
        }
        /**
         * Sobreescribe el dato almacenado en el nodo
         * @param dato_ 
         */
        public void Dato(T dato_){
            dato = dato_;
        }
    }
}
