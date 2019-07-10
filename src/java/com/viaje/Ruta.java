/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viaje;

import java.util.Random;

/**
 *
 * @author otzoy
 */
public class Ruta {

    private float costo, tiempo;
    private int[] piloto;
    private byte[] llave;

    /**
     *
     * @param costo
     * @param tiempo
     * @param piloto
     */
    public Ruta(float costo, float tiempo, String piloto) {
        this.costo = costo;
        this.tiempo = tiempo;
        encriptar(piloto);
    }

    /**
     * Encripta el nombre del piloto Genera una llave aleatoria de bytes y
     * realiza un cifrado Vernam con los ASCII de la cadena inicial
     *
     * @param s
     */
    private void encriptar(String s) {
        //Crea una llave única de tamaño del nombre del piloto
        llave = new byte[s.length()];
        piloto = new int[s.length()];
        //Se llena con bytes random
        new Random().nextBytes(llave);
        //Reescriba el nombre del piloto
        for (int i = 0; i < llave.length; i++) {
            piloto[i] = llave[i] + ((int) s.charAt(i));
        }
    }

    /**
     *
     * @return el costo de la ruta (en quetzales)
     */
    public float Costo() {
        return costo;
    }

    /**
     *
     * @return el tiempo de la ruta (en minutos)
     */
    public float Tiempo() {
        return tiempo;
    }

    /**
     * Modifica el nombre del piloto
     *
     * @param piloto
     */
    public void Piloto(String piloto) {
        encriptar(piloto);
    }

    /**
     * Modifica el costo de la ruta (en quetzales)
     *
     * @param costo_
     */
    public void Costo(float costo_) {
        costo = costo_;
    }

    /**
     * Modifica el tiempo de la ruta (en minutos)
     *
     * @param tiempo_
     */
    public void Tiempo(float tiempo_) {
        tiempo = tiempo_;
    }

    /**
     * 
     * @return el nombre del piloto -descifrado-
     */
    public String Piloto() {
        String aux = "";
        for (int i = 0; i < this.piloto.length; i++) {
            aux += (char) (this.piloto[i]-this.llave[i]);
        }
        return aux;
    }
}
