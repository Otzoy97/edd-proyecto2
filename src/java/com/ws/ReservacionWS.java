/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws;

import com.estructura.hash.TablaHash;
import com.estructura.lista.Lista;
import com.viaje.Archivo;
import com.viaje.Reservacion;
import static com.ws.DestinoWS.m;
import java.util.Iterator;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author otzoy
 */
@WebService(serviceName = "ReservacionWS")
public class ReservacionWS {

    public static TablaHash th = new TablaHash();

    /**
     * Agrega una nueva reservación
     *
     * @param llave
     * @param nombreCliente
     * @param costo
     * @param tiempo
     * @param viaje
     * @return
     */
    @WebMethod(operationName = "agregarReservacion")
    public String agregarReservacion(@WebParam(name = "llave") int llave, @WebParam(name = "nombreCliente") String nombreCliente, @WebParam(name = "costo") float costo, @WebParam(name = "tiempo") float tiempo, @WebParam(name = "viaje") Lista<Integer> viaje) {
        try {
            Lista<String> l = new Lista();
            for (Integer i : viaje) {
                l.agregarAlFinal(m.nombreDestino(i));
            }
            th.insertar(llave, new Reservacion(nombreCliente, costo, tiempo, l));
        } catch (Exception ex) {
            return String.format("Ocurrió un error al intentar crear la reservacion");
        }
        return String.format("Reservación creada existosamente");
    }

    /**
     * Elimina una reservacion con la llave dada
     *
     * @param llave
     * @return
     */
    @WebMethod(operationName = "eliminarReservacionConLlave")
    public String eliminarReservacionConLlave(@WebParam(name = "llave") int llave) {
        try {
            th.eliminar(llave);
        } catch (Exception ex) {
            return String.format("Ocurrió un error al intentar eliminar la reservación");
        }
        return String.format("Reservación eliminada exitosamente");
    }

    /**
     *
     * @param llave
     * @return {@code true} si la llave existe en la tabla hash
     */
    @WebMethod(operationName = "verificarLlave")
    public boolean verificarLlave(@WebParam(name = "llave") int llave) {
        return th.existeLlave(llave);
    }

    /**
     * Recupera una reservación con llave {@code llave}
     *
     * @param llave
     * @return
     */
    @WebMethod(operationName = "reservacionConLlave")
    public String[] reservacionConLlave(@WebParam(name = "llave") int llave) {
        Reservacion rev = th.reservacion(llave);
        //No se encontró nada
        if (rev == null) {
            return null;
        }
        //Crea y llena un arreglo para enviar los datos
        String[] r = new String[4];
        r[0] = rev.Cliente();
        r[1] = String.format(".2f", rev.Costo());
        r[2] = String.format(".2f", rev.Tiempo());
        //Concatene el nombre de los paises que están el
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = rev.Viaje().iterator();
        while (it.hasNext()) {
            sb.append(String.format("%s%s", it.next(), it.hasNext() ? ">>" : ""));
        }
        r[3] = sb.toString();
        return r;
    }

    /**
     * Recupera un arreglo con la información de todas las reservaciones que el
     * cliente {@code nombre} haya realizado
     *
     * @param nombre
     * @return
     */
    @WebMethod(operationName = "reservacionesDelCliente")
    public String[][] reservacionesDelCliente(@WebParam(name = "nombre") String nombre) {
        Reservacion[] rev = th.reservacionPorCliente(nombre);
        if (rev == null) {
            return null;
        }
        String[] t;
        StringBuilder sb;
        String[][] r = new String[rev.length][4];
        int c = 0;
        for (Reservacion rev1 : rev) {
            t = new String[4];
            t[0] = rev1.Cliente();
            t[1] = String.format(".2f", rev1.Costo());
            t[2] = String.format(".2f", rev1.Tiempo());
            //Concatene el nombre de los paises que están en la reservación
            sb = new StringBuilder();
            Iterator<String> it = rev1.Viaje().iterator();
            while (it.hasNext()) {
                sb.append(String.format("%s%s", it.next(), it.hasNext() ? ">>" : ""));
            }
            t[3] = sb.toString();
            r[c++] = t;
        }
        return r;
    }

    /**
     *
     * @return Base64 de la representación de memoria de la tabla hash
     */
    @WebMethod(operationName = "generarTablaHash")
    public String generarTablaHash() {
        //Recupera el script de la matriz
        String str = th.dot();
        if (str == null) {
            return "";
        }
        if (str.equals("")) {
            return "";
        }
        //Escriba un archivo con nombre -dotMB- y extensión -.dot-
        Archivo.escribirArchivo(str, "dotHT", ".dot");
        //Ejecuta un comando en el cmd
        Archivo.generarGrafico("neato -Tbmp dotHT.dot -o HT.bmp");
        //Elimina el archivo -dotMB-
        Archivo.eliminarArchivo("dotHT.dot");
        //Genera un base65;
        return Archivo.toBase64("HT.bmp");
    }
}
