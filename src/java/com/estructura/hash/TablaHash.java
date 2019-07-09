/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructura.hash;

import java.math.BigInteger;
import com.viaje.Reservacion;

/**
 * Tabla hash con tamaño inicial = 7
 * @author otzoy
 */
public class TablaHash {
    
    /**
     * 
     * @param <Key>
     * @param <Value> 
     */
    class Nodo<K, V>{
        /**
         * Llave que identifica al valor almacenado
         */
        final K key;
        /**
         * valor que se almacena
         */
        private V value;

        /**
         * Constructor por defecto
         * @param key
         * @param value 
         */
        public Nodo(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        /**
         * Devuelve el valor almacenado
         * @return 
         */
        public V valor(){
            return value;
        }
        
        /**
         * Cambia el valor almacenado
         * @param v 
         */
        public void valor(V v){
            value = v;
        }
        
        /**
         * Muestra el valor almacenado
         * @return 
         */
        public K llave(){
            return key;
        }
    }
        
    /**
     * Representa la tabla has sin inicilializar
     */
    private Nodo<Integer, Reservacion> [] th;
    
    /**
     * Mantiene el número de datos que la tabla hash contiene
     */
    private int largo;
    
    /**
     * Constructor por defecto
     */
    public TablaHash(){
        th = new Nodo[7];
        largo = 0;
    }
    
    /**
     * Inserta un nuevo dato en la tabla hash
     * @param llave identifica de forma única al valor
     * @param valor a almacenar en un nodo de tabla hash
     */
    public void insertar(int llave, Reservacion valor){
        //Genera una posición del arreglo (en dónde se pueda alojar el nuevo dato) y resuelve
        //cualquier posible colisión
        int pos = resolverColision(generarPos(valor.Cliente()));
        //Inserta en la tabla
        this.th[pos] = new Nodo(llave,valor);
        //Verifica desbordamiento (de menos del 50%)
        if( (largo/th.length) > 0.500 ){
            reescalar();
        }
        largo++;
    }
    
    /**
     * Determina si ya existe una llave en la tabla hash
     * @param llave
     * @return 
     */
    public boolean existeLlave(int llave){
        for (Nodo<Integer, Reservacion> th1 : this.th) {
            if (th1 != null && th1.key == llave) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Recupera una reservación con llave {@code llave}
     * @param llave
     * @return 
     */
    public Reservacion reservacion(int llave){
        for (Nodo<Integer, Reservacion> th1 : this.th) {
            if (th1 != null && th1.key == llave) {
                return th1.valor();
            }
        }
        return null;
    }
    
    /**
     * Recupera un arreglo con la información de todas las reservaciones que
     * el cliente {@code nombre} haya realizado
     * @param nombre
     * @return 
     */
    public Reservacion[] reservacionPorCliente(String nombre){
        int cn = contar(nombre);
        //Si es menor o igual a cero retona nulo
        if (cn <= 0) return null;
        Reservacion[] r = new Reservacion[cn];
        //Recorre el arreglo guardando las reservaciones del cliente
        int c = 0;
        for (Nodo<Integer, Reservacion> th1 : this.th) {
            //Si no es nulo y coincide aloja la referenca
            if(th1 != null && th1.value.Cliente().equals(nombre))
                r[c++] = th1.valor();
        }
        return r;
    }
    
    /**
     * Usado para contar cuantas reservaciones existen con el nombre de un cliente en particular
     * @param nombre
     * @return 
     */
    private int contar(String nombre){
        int c = 0;
        for (Nodo<Integer, Reservacion> th1 : this.th) {
            if(th1 != null && th1.value.Cliente().equals(nombre))
                c++;
        }
        return c;
    }
    
    /**
     * Genera una llave para la tabla hash utilizando las primeras tres letras de un string
     * La llave representa una posición en la tabla has
     * @param n
     * @return 
     */
    private int generarPos(String n){
        //Si es menor a tres utiliza las letras que existan
        if(n.length() < 3){
            int k = 0;
            for(int i = 0 ;  i < n.length() - 1; i++)
                k += n.codePointAt(i);
            return k%th.length;
        }
        //Tiene suficientes letras
        return (n.codePointAt(0) + n.codePointAt(1)+ n.codePointAt(2))%th.length;
    }
    
    /**
     * Determina si ya existe un nodo en la posición dada
     * @param pos
     * @return 
     */
    private boolean existe(int pos){
        return th[pos] != null;
    }
    
    /**
     * Modifica la posición si hubiera colisión
     * @param pos
     * @return 
     */
    private int resolverColision(int pos){
        int c = 0;
        while(existe(pos) && c < (th.length + 1)){
            pos += Math.pow(c+1, c+1) % th.length;
            c++;
        }
        return pos;
    }
    
    /**
     * Modifca el arreglo, redimensionándolo, creando más espacio disponibles
     * @return 
     */
    private void reescalar(){
        Nodo<Integer, Reservacion> [] nuevo = new Nodo[sigPrimo()];
        //Realiza una copia superficial de la tabla has actual
        System.arraycopy(th, 0, nuevo, 0, th.length);
        //Reasigna el nuevo arreglo
        th = nuevo;
    }
    
    /**
     * Calcula el siguiente número primo
     * @return 
     */
    private int sigPrimo(){
        return Integer.parseInt(new BigInteger(String.valueOf(th.length)).nextProbablePrime().toString());
    }
    
    /**
     * 
     * @return 
     */
    public String dot(){
        StringBuilder sb = new StringBuilder();
        sb.append("graph {\n");
        
        sb.append("\n}");
        return sb.toString();
    }
}
