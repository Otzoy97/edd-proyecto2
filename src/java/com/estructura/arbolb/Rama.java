/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructura.arbolb;

import java.util.Iterator;

/**
 *
 * @author otzoy
 */
public class Rama implements Iterable<NodoB> {

    /**
     * Iterador para la clase Rama
     */
    class RamaIterador implements Iterator<NodoB> {

        /**
         * Apuntador al nodo actual
         */
        NodoB actual;

        /**
         * Constructor por defecto
         *
         * @param rama
         */
        public RamaIterador(Rama rama) {
            actual = rama.primero;
        }

        /**
         *
         * @return <code>true</code> si existe un nodo en la rama, de lo
         * contrario <code>false</code>
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
        public NodoB next() {
            NodoB data = actual;
            actual = actual.siguiente;
            return data;
        }
    }

    /**
     * Constructor por defecto
     */
    public Rama() {
        primero = null;
        largo = 0;
        hoja = true;
    }
    /**
     * Almacena el tamaño de la rama
     */
    private int largo;
    /**
     * Determina si la rama es hoja
     */
    private boolean hoja;

    /**
     *
     * @return <code>true</code> si la rama es hoja
     */
    public boolean esHoja() {
        return hoja;
    }

    /**
     *
     * @param hoja_ especifica si la rama es hoja
     */
    public void esHoja(boolean hoja_) {
        hoja = hoja_;
    }
    /**
     * Apuntador a la cabeza de la rama
     */
    public NodoB primero;

    /**
     *
     * @return devuelve un {@code int} que especifica el largo de la rama
     */
    public int Largo() {
        return largo;
    }

    /**
     *
     * @return devuelve {@code true} si la rama está vacía o <code>false</code>
     * si no lo está
     */
    public boolean esVacio() {
        return primero == null;
    }

    /**
     * Agrega de forma ordenada un nodo a la rama
     *
     * @param nodo
     */
    public void agregar(NodoB nodo) {
        if (!esVacio()) {
            NodoB temp = primero;
            while (temp != null) {
                if (temp.Dato().Codigo() > nodo.Dato().Codigo()) {
                    if (temp != primero) {
                        //Arregla los nodos anterior y siguiente (del nuevo nodo)
                        nodo.anterior = temp.anterior;
                        nodo.siguiente = temp;
                        //Arregla los nodos de las ramas (del nuevo nodo)
                        nodo.izquierda = temp.anterior.derecha;
                        temp.izquierda = nodo.derecha;
                        nodo.derecha = null;
                        //Arregla los nodos anterior y siguiente (del nodo temp)
                        temp.anterior = nodo;
                        temp.anterior.siguiente = nodo;
                    } else {
                        //Arregla los nodos anterior y siguiente
                        nodo.siguiente = primero;
                        primero.anterior = nodo;
                        nodo.anterior = primero.anterior;
                        //Arregla los nodos de las ramas
                        primero.izquierda = nodo.derecha;
                        primero = nodo;
                    }
                    largo++;
                    return;
                } else if (temp.Dato().Codigo() < nodo.Dato().Codigo()) {
                    if (temp.siguiente == null) {
                        temp.derecha = nodo.izquierda;
                        nodo.anterior = temp;
                        temp.siguiente = nodo;
                        largo++;
                        return;
                    }
                }
                temp = temp.siguiente;
            }
        } else {
            primero = nodo;
            largo++;
        }
    }

    /**
     *
     * @param codigo
     * @return {@code true} si el codigo existe
     */
    public boolean existe(int codigo) {
        for (NodoB temp : this) {
            if (temp.Dato().Codigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param codigo
     * @return {@code Destino} con el código especificado
     */
    public NodoB buscar(int codigo) {
        for (NodoB temp : this) {
            if (temp.Dato().Codigo() == codigo) {
                return temp;
            }
        }
        return null;
    }

    /**
     * Determina la posición en la que se encuentra
     *
     * @param codigo
     * @return
     */
    public int buscarPos(int codigo) {
        int cod = 0;
        for (NodoB temp : this) {
            if (temp.Dato().Codigo() == codigo) {
                return cod;
            } else {
                cod++;
            }
        }
        return cod;
    }

    /**
     * Retorna un espejo de la rama para el árbol origen
     *
     * @return
     */
    public String espejoMB() {
        StringBuilder sb = new StringBuilder();
        contador = 1;
        sb.append(String.format("{<f0>%s}", espejoMB(this.primero)));
        return sb.toString();
    }

    private static int contador;

    /**
     * Retorna un script de la rama del arbol destino
     *
     * @return
     */
    public String ramaMB() {
        StringBuilder sb = new StringBuilder();
        contador = 1;
        sb.append(String.format("<f0>%s", ramaMB(this.primero)));
        return sb.toString();
    }

    /**
     * Retorna un script para graficar la rama
     *
     * @param n
     * @return
     */
    private String ramaMB(NodoB n) {
        if (n == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|<f%d>%d|<f%d>", contador++, n.Dato().Codigo(), contador++));
        sb.append(ramaMB(n.siguiente));
        return sb.toString();
    }

    /**
     * Retorna un script para graficar la rama "espejo"
     *
     * @param n
     * @return
     */
    private String espejoMB(NodoB n) {
        if (n == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(espejoMB(n.siguiente));
        sb.append(String.format("|<f%d>%d|<f%d>", contador++, n.Dato().Codigo(), contador++));
        return sb.toString();
    }

    /**
     *
     * @return iterador para la clase Rama
     */
    @Override
    public Iterator<NodoB> iterator() {
        return new RamaIterador(this);
    }
}
