/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viaje;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 *
 * @author otzoy
 */
public class Archivo {

    /**
     * Guarda un archivo
     *
     * @param contenido
     * @param nombre
     * @param extension
     * @return
     */
    public static boolean escribirArchivo(String contenido, String nombre, String extension) {
        FileWriter fw = null;
        BufferedWriter bf = null;
        String script = null;
        try {
            fw = new FileWriter(new File(nombre + extension));
            bf = new BufferedWriter(fw);
            bf.write(contenido);
            //Ejecuta un comando DOT
        } catch (Exception ex) {
            return false;
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                return false;
            }
        }
        return true;
    }

    /**
     * Utiliza un comando de graphviz para renderizar una imagen
     *
     * @param comando
     */
    public static void generarGrafico(String comando) {
        Process p;
        try {
            p = Runtime.getRuntime().exec(comando);
            p.waitFor();
        } catch (Exception e) {

        }
    }

    /**
     * Genera una cadena en base64 que representa una imagen
     *
     * @param nombreArchivo
     * @return
     */
    public static String toBase64(String nombreArchivo) {
        //Aquí se alojará la cadena en base64
        String base64;
        FileInputStream fs = null;
        try {
            //Le el archivo
            //Obtiene un stream de bytes
            File f = new File(nombreArchivo);
            fs = new FileInputStream(f);
            byte[] bytes = new byte[(int) f.length()];
            fs.read(bytes);
            //Genera un codificación en base64
            base64 = new String(Base64.getEncoder().encode(bytes), Charset.forName("UTF-8"));
        } catch (Exception ex) {
            return null;
        } finally {
            try{
                if(fs != null)
                    fs.close();
            } catch (Exception ex){
                return null;
            }
        }
        return base64;
    }
    
    /**
     * Elimina un archivo en la ruta dada
     * @param ruta
     * @return 
     */
    public static boolean eliminarArchivo(String ruta){
        File f = new File(ruta);
        return f.delete();
    }
}
