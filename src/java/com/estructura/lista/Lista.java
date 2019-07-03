/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructura.lista;

/**
 * Lista doblemente enlazada genérica
 * @author otzoy
 * @param <T> Tipo de dato a almacenar en la lista
 */
public class Lista<T> {
    /**
     * Nodo que compone la lista doblemente enlazada
     * @param <T> 
     */
    class Nodo<T>{
        /**
         * Apuntadores
         */
        public Nodo siguiente, anterior;
        /**
         * Dato que almacena el nodo
         */
        private T dato;
        Nodo(T dato_){
            siguiente = null;
            anterior = null;
            dato = dato_;
        }
        /**
         * 
         * @return recupera el dato almacenado en el nodo
         */
        public T Dato(){
            return dato;
        }
        /**
         * Modifica el dato almacenado en el nodo
         * @param dato_ nuevo dato
         */
        public void Dato(T dato_){
            dato = dato_;
        }
    }
    
    private Nodo primero;
    private Nodo ultimo;
    private int largo;
    
    Lista(){
        primero = null;
        ultimo = null;
    }
    /**
     * Devuelve <code>true</code> si la lista no contiene algún nodo, <code>false</code> si tiene al menos un nodo
     * @return 
     */
    public boolean esVacio(){
        return primero == null;
    }
    /**
     * Agrega un nuevo nodo al final de la lista
     * @param dato_
     */
    public void agregarAlFinal(T dato_){
        Nodo<T> nuevo = new Nodo<>(dato_);
        if(!esVacio()){
            nuevo.anterior = ultimo;
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        } else {
            primero = nuevo;
            ultimo = primero;
        }
        largo++;
    }
//    public void imprimir(){
//        Nodo<T> temp = primero;
//        while(temp!=null){
//            System.out.println(temp.Dato());    
//            temp = temp.siguiente;
//        }
//    }
//    public static void main(String args[]){
//        Lista<Integer> a = new Lista();
//        a.agregarAlFinal(10);
//        a.agregarAlFinal(9);
//        a.agregarAlFinal(8);
//        a.imprimir();
//    }
}
