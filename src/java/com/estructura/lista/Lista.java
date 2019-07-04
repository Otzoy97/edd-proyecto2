/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructura.lista;

import java.util.Iterator;

/**
 * Lista doblemente enlazada genérica
 *
 * @author otzoy
 * @param <T> Tipo de dato a almacenar en la lista
 */
public class Lista<T> implements Iterable<T> {
    /**
     * Nodo que compone la lista doblemente enlazada
     * @param <T>
     */
    class Nodo<T> {

        /**
         * Apuntadores
         */
        public Nodo siguiente, anterior;
        /**
         * Dato que almacena el nodo
         */
        private T dato;

        Nodo(T dato_) {
            siguiente = null;
            anterior = null;
            dato = dato_;
        }

        /**
         *
         * @return recupera el dato almacenado en el nodo
         */
        public T Dato() {
            return dato;
        }

        /**
         * Modifica el dato almacenado en el nodo
         *
         * @param dato_ nuevo dato
         */
        public void Dato(T dato_) {
            dato = dato_;
        }
    }
    /**
     * Iterador para la clase Lista
     */
    class ListaIterador implements Iterator<T> {
        /**
         * Apuntador al nodo actual :v
         */
        Nodo<T> actual;
        /**
         * Constructor por defecto
         * @param lista
         */
        public ListaIterador(Lista<T> lista) {
            actual = lista.primero;
        }
        /**
         * 
         * @return <code>true</code> si existe un nodo en la lista, de lo contrario <code>false</code>
         */
        @Override
        public boolean hasNext() {
            return actual != null;
        }
        /**
         * Retorna el dato que el nodo actual almacena y mueve el puntero del nodo actual al siguiente
         * @return el dato que el nodo actual almacena
         */
        @Override
        public T next() {
            T data = actual.Dato();
            actual = actual.siguiente;
            return data;
        }
    }
    /**
     * Apuntadores hacia la cabeza y cola de la lista
     */
    Nodo<T> primero, ultimo;
    /**
     * Almacena el largo de la lista
     */
    private int largo;
    /**
     * Constructor por defecto
     */
    public Lista() {
        primero = null;
        ultimo = null;
    }
    /**
     * @return Devuelve <code>true</code> si la lista no contiene algún nodo,
     * <code>false</code> si tiene al menos un nodo
     */
    public boolean esVacio() {
        return primero == null;
    }
    /**
     * Agrega un nuevo nodo al final de la lista
     * @param dato_ que se almacenará al final de lista
     */
    public void agregarAlFinal(T dato_) {
        Nodo<T> nuevo = new Nodo<>(dato_);
        if (!esVacio()) {
            nuevo.anterior = ultimo;
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        } else {
            primero = nuevo;
            ultimo = primero;
        }
        largo++;
    }
    /**
     *
     * @return iterador para la clase lista
     */
    @Override
    public Iterator<T> iterator() {
        return new ListaIterador(this);
    }
}
    