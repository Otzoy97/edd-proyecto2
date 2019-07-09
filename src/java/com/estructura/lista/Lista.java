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
     *
     * @param <T>
     */
    public class Nodo<T> {

        /**
         * Apuntadores
         */
        public Nodo siguiente, anterior;
        /**
         * Dato que almacena el nodo
         */
        private T dato;

        /**
         * Constructor por defecto
         *
         * @param dato_
         */
        Nodo(T dato) {
            siguiente = null;
            anterior = null;
            this.dato = dato;
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
         *
         * @param lista
         */
        public ListaIterador(Lista<T> lista) {
            actual = lista.primero;
        }

        /**
         *
         * @return {@code true} si existe un nodo en la lista
         */
        @Override
        public boolean hasNext() {
            return actual != null;
        }

        /**
         * Retorna el dato que el nodo actual almacena y mueve el puntero del
         * nodo actual al siguiente nodo
         *
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
     *
     * @param dato que se almacenará al final de lista
     */
    public void agregarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
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
     * Elimna un dato en el índice específicado
     *
     * @param indice desde 0 hasta n-1, siendo n el largo de la ista
     */
    public void eliminarEn(int indice) {
        //Nada que eliminar
        if (this.primero == null) {
            return;
        }
        //Es el primero nodo
        if (indice == 0) {
            primero = primero.siguiente;
            primero.anterior = null;
            largo--;
            return;
        }
        //Es el ultimo nodo
        if (indice == largo - 1) {
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
            largo--;
            return;
        }
        Nodo aux;
        //Es un nodo intermedio
        if (indice > (largo - 1) / 2) {
            aux = ultimo;
            for (int i = (largo - 1); i != indice; i--) {
                aux = aux.anterior;
            }
        } else {
            aux = primero;
            for (int i = 0; i != indice; i++) {
                aux = aux.siguiente;
            }
        }
        aux.anterior.siguiente = aux.siguiente;
        aux.siguiente.anterior = aux.anterior;
    }

    /**
     * Remueve el último nodo de la lista
     * @return 
     */
    public Object eliminarAlFinal(){
        if(this.ultimo == null)
            return null;
        T dat = ultimo.dato;
        ultimo = ultimo.anterior;
        ultimo.siguiente = null;
        return dat;
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
