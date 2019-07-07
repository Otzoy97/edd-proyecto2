/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructura.matriz;

import com.estructura.arbolb.NodoB;
import com.estructura.arbolb.Rama;
import com.viaje.Destino;
import com.viaje.Ruta;

/**
 * Matriz dispersa utilizada como una matriz de adyacencia cuyos encabezados
 * están indexados con ArbolB la matriz siempre es cuadrada debido a que los
 * nodos son bidireccionales.
 *
 * @author otzoy
 */
public class Matriz {

    /**
     * Contiene el comportamiento del árbolB
     */
    class ArbolB {

        private int tamanio = 0;

        /**
         * Constructor por defecto
         */
        public ArbolB() {
            raiz = null;
        }

        /**
         * Apunta a la raiz del árbol
         */
        public Rama raiz;

        /**
         *
         * @return {@code true} si el árbol está vacío
         */
        public boolean esVacio() {
            return raiz == null;
        }

        /**
         * Agrega un nuevo destino al árbol B
         *
         * @param dato
         */
        private void agregar(Destino dato) {
            if (!esVacio()) {
                Object obj = agregar(new NodoB(dato), raiz);
                if (obj instanceof NodoB) {
                    raiz = new Rama();
                    raiz.agregar((NodoB) obj);
                    raiz.esHoja(false);
                    tamanio++;
                }
            } else {
                raiz = new Rama();
                raiz.agregar(new NodoB(dato));
                tamanio++;
            }
        }

        /**
         * Agrega un nuevo nodo al árbol B
         *
         * @param nodo
         * @param rama
         * @return
         */
        private Object agregar(NodoB nodo, Rama rama) {
            if (rama.esHoja()) {
                rama.agregar(nodo);
                if (rama.Largo() == 5) {
                    return dividir(rama);
                }
            } else {
                for (NodoB temp : rama) {
                    if (nodo.Dato().Codigo() == temp.Dato().Codigo()) {
                        return rama;
                    } else if (nodo.Dato().Codigo() < temp.Dato().Codigo()) {
                        Object obj = agregar(nodo, temp.izquierda);
                        if (obj instanceof NodoB) {
                            rama.agregar((NodoB) obj);
                            if (rama.Largo() == 5) {
                                return dividir(rama);
                            }
                        }
                        return rama;
                    } else if (temp.siguiente == null) {
                        Object obj = agregar(nodo, temp.derecha);
                        if (obj instanceof NodoB) {
                            rama.agregar((NodoB) obj);
                            if (rama.Largo() == 5) {
                                return dividir(rama);
                            }
                        }
                        return rama;
                    }
                }
            }
            return rama;
        }

        /**
         *
         * @param codigo
         * @return {@code true} si existe un Destino con el código especificado
         */
        public boolean existe(int codigo) {
            return existe(raiz, codigo);
        }

        /**
         * Recorre las ramas buscando una coincidencia
         *
         * @param raiz
         * @param codigo
         * @return
         */
        private boolean existe(Rama raiz, int codigo) {
            //Se llegó a una rama nula y no se encontró una coincidencia
            if (raiz == null) {
                return false;
            }
            //Busca una coincidencia en la rama
            if (raiz.existe(codigo)) {
                return true;
            }
            //Busca una coincidencia en las subramas
            for (NodoB temp : raiz) {
                //El dato es menor, busca en la subrama izquierda
                //El dato es mayor y el siguiente es nulo, busca en la subrama derecha
                if (codigo < temp.Dato().Codigo()) {
                    return existe(temp.izquierda, codigo);
                } else if (codigo > temp.Dato().Codigo() && temp.siguiente == null) {
                    return existe(temp.derecha, codigo);
                }
            }
            return false;
        }

        /**
         *
         * @param codigo
         * @return destino almacenado en el árbol B con el código especificado
         */
        public Destino buscar(int codigo) {
            NodoB d = buscar(this.raiz, codigo);
            return d != null ? d.Dato() : null;
        }

        /**
         * Recorre el árbol buscando en las ramas un destino que posea el código
         * especificado
         *
         * @param raiz
         * @param codigo
         * @return
         */
        private NodoB buscar(Rama raiz, int codigo) {
            //Se llegó a una rama nula y no se encontró una coincidencia
            if (raiz == null) {
                return null;
            }
            //Busca una coincidencia en la rama
            if (raiz.existe(codigo)) {
                return raiz.buscar(codigo);
            }
            //Busca una coincidencia en las subramas
            for (NodoB temp : raiz) {
                //El dato es menor, busca en la subrama izquierda
                //El dato es mayor y el siguiente es nulo, busca en la subrama derecha
                if (codigo < temp.Dato().Codigo()) {
                    return buscar(temp.izquierda, codigo);
                } else if (codigo > temp.Dato().Codigo() && temp.siguiente == null) {
                    return buscar(temp.derecha, codigo);
                }
            }
            return null;
        }

        /**
         * Divide una rama que ya excedio el límite de > 4
         *
         * @param rama
         * @return
         */
        private Object dividir(Rama rama) {
            Rama izq = new Rama(), der = new Rama();
            NodoB nuevo = null;
            int contador = 0;
            for (NodoB temp : rama) {
                if (contador < 2) {
                    NodoB a_izq = new NodoB(temp.Dato());
                    a_izq.izquierda = temp.izquierda;
                    a_izq.derecha = temp.derecha;
                    izq.esHoja(a_izq.izquierda == null && a_izq.derecha == null);
                    izq.agregar(a_izq);
                    contador++;
                    continue;
                }
                if (contador == 2) {
                    nuevo = new NodoB(temp.Dato());
                } else if (contador < 5) {
                    NodoB a_der = new NodoB(temp.Dato());
                    a_der.izquierda = temp.izquierda;
                    a_der.derecha = temp.derecha;
                    der.esHoja(a_der.izquierda == null && a_der.derecha == null);
                    der.agregar(a_der);
                }
                contador++;
            }
            nuevo.izquierda = izq;
            nuevo.derecha = der;
            return nuevo;
        }

        /**
         * Devuelve un gráfica que representa al arbol B
         *
         * @param aux
         * @return
         */
        public String graph(Rama aux) {
            if (aux == null) {
                return "";
            }
            //Aquí se almacenará el string del dot
            StringBuilder sb = new StringBuilder();
            //Llevará el control de los apuntadores hacia los subárboles
            int contador = 0;
            //Recorre los nodos de la rama creándolos
            sb.append(String.format("BT%d[label=\"", aux.hashCode()));
            for (NodoB temp : aux) {
                sb.append(String.format("<f%d>|%d\\n%s|", contador++, temp.Dato().Codigo(), temp.Dato().Nombre()));
                //sb.append("<f").append(contador++).append(">|").append(temp.Dato().Codigo()).append("|<f").append(contador).append(">");
            }
            sb.append(String.format("<f%d>\"; shape = record];\n", contador));
            //Reinicia el contador
            contador = 0;
            //Recorre los nodos de la rama conectando con otros subarboles
            for (NodoB temp : aux) {
                if (temp.siguiente != null) {
                    if (temp.izquierda != null) {
                        sb.append(String.format("\"BT%d\":f%d -- \"BT%d\";\n", aux.hashCode(), contador++, temp.izquierda.hashCode()));
                    }
                } else {
                    if (temp.izquierda != null) {
                        sb.append(String.format("\"BT%d\":f%d -- \"BT%d\";\n", aux.hashCode(), contador++, temp.izquierda.hashCode()));
                    }
                    if (temp.derecha != null) {
                        sb.append(String.format("\"BT%d\":f%d -- \"BT%d\";\n", aux.hashCode(), contador++, temp.derecha.hashCode()));
                    }
                }
            }
            //Recorre en profundidad el árbol
            for (NodoB temp : aux) {
                sb.append(graph(temp.izquierda));
                if (temp.siguiente == null) {
                    sb.append(graph(temp.derecha));
                }
            }
            return sb.toString();
        }

        /**
         * Cuenta el número de datos que existe en la rama {@code r} y sus
         * subramas
         *
         * @param r
         * @return
         */
        private int contar(Rama r) {
            if (r == null) {
                return 0;
            }
            int totalRama = r.Largo();
            for (NodoB n : r) {
                totalRama += contar(n.izquierda);
                if (n.siguiente == null) {
                    totalRama += contar(n.derecha);
                }
            }
            return totalRama;
        }

        /**
         * Recorre inorden el árbolB contando cuántos nodos ha pasado hasta
         * encontrar una coincidencia
         *
         * @return
         */
        private boolean buscarPos(Rama rama, int id) {
            if (rama == null) {
                return false;
            }
            for (NodoB b : rama) {
                if (!buscarPos(b.izquierda, id)) {
                    if (b.Dato().Codigo() != id) {
                        Matriz.cont++;
                    } else {
                        return true;
                    }
                    if (b.siguiente == null) {
                        if (buscarPos(b.derecha, id)) {
                            return true;
                        }
                    }
                } else {
                    return true;
                }
            }
            return false;
        }

        /**
         * Obtiene la profundidad del árbolB
         *
         * @return
         */
        private int profundidad(Rama r) {
            if (r == null) {
                return 0;
            }
            for (NodoB n : r) {
                return profundidad(n.izquierda) + 1;
            }
            return 0;
        }
    }

    /**
     * Se utiliza para contar nodos, ver {@code buscarPos} en {@code ArbolB} --
     * No evidente
     */
    private static int cont;

//    public void depth(){
//        System.out.println(this.idDestino.profundidad(this.idDestino.raiz));
//        System.out.println(this.idOrigen.profundidad(this.idOrigen.raiz));
//    }
    /**
     * Encabezados
     */
    private final ArbolB idOrigen, idDestino;

    /**
     * Mantiene el número de filas y columnas del árbol
     */
    private int dimension;

    /**
     * Constructor por defecto
     */
    public Matriz() {
        idOrigen = new ArbolB();
        idDestino = new ArbolB();
        dimension = 0;
    }

    /**
     * Agrega un nuevo destino a la matriz
     *
     * @param codigo
     * @param nombre
     */
    public void agregarDestino(int codigo, String nombre) {
        this.idOrigen.agregar(new Destino(codigo, nombre));
        this.idDestino.agregar(new Destino(codigo, nombre));
        dimension++;
    }

    /**
     *
     * @param origen
     * @param destino
     * @param ruta
     * @throws java.lang.Exception el {@code origen} o {@code destino} no existe
     */
    public void agregarRuta(int origen, int destino, Ruta ruta) throws Exception {
        //Se asegura que los encabezados necesarios existan
        if (!idOrigen.existe(origen) || !idDestino.existe(destino)) {
            throw new Exception("No existen los destinos necesarios para crear la ruta");
        }
        //Recupera los destinos de las cabeceras
        NodoB o = idOrigen.buscar(this.idOrigen.raiz, origen);
        NodoB d = idDestino.buscar(this.idDestino.raiz, destino);
        //Ambos nodos son nulos
        if (o.ruta == null && d.ruta == null) {
            NodoM nuevo = new NodoM(ruta, origen, destino);
            o.ruta = nuevo;
            d.ruta = nuevo;
            nuevo.origen = o;
            nuevo.destino = d;
        } //Solo el origen (fila) es nulo
        else if (o.ruta == null && d.ruta != null) {
            NodoM nuevo = new NodoM(ruta, origen, destino);
            NodoM col = d.ruta;
            //Verifica si el nuevo nodo a insertar es mayor que el nodo actual
            if (col.Origen() < origen) {
                nuevo.abajo = col;
                col.arriba = nuevo;
                d.ruta = nuevo;
                o.ruta = nuevo;
                return;
            }
            //Recorre las celdas de las columnas
            while (col.abajo != null && col.abajo.Origen() >= origen) {
                col = col.abajo;
            }
            //Enlaza los nodos a la columna
            nuevo.arriba = col;
            nuevo.abajo = col.abajo;
            if (col.abajo != null) {
                col.abajo.arriba = nuevo;
            }
            col.abajo = nuevo;
            nuevo.origen = o;
            o.ruta = nuevo;
        } //Solo el destino (columna) es nulo
        else if (o.ruta != null && d.ruta == null) {
            NodoM nuevo = new NodoM(ruta, origen, destino);
            NodoM fila = o.ruta;
            //Verifica si el nodo a insertar es menor al nodo actual
            if (fila.Destino() > destino) {
                nuevo.siguiente = fila;
                fila.anterior = nuevo;
                o.ruta = nuevo;
                d.ruta = nuevo;
                return;
            }
            //Recorre las celdas de las filas
            while (fila.siguiente != null && fila.siguiente.Destino() <= destino) {
                fila = fila.siguiente;
            }
            //Enlaza los nodos a la fila
            nuevo.anterior = fila;
            nuevo.siguiente = fila.siguiente;
            if (fila.siguiente != null) {
                fila.siguiente.anterior = nuevo;
            }
            fila.siguiente = nuevo;
            nuevo.destino = d;
            d.ruta = nuevo;
        } //Ninguno es nulo
        else {
            NodoM col = d.ruta;
            NodoM fila = o.ruta;
            //Recorre los nodos hasta encontrar la celda necesaria
            while (col.abajo != null && col.abajo.Origen() <= origen) {
                col = col.abajo;
            }
            while (fila.siguiente != null && fila.siguiente.Destino() <= destino) {
                fila = fila.siguiente;
            }
            //Si las coordenadas coinciden modifica el valor
            if (col.Destino() == destino && col.Origen() == origen && fila.Destino() == destino && fila.Origen() == origen) {
                col.Ruta(ruta);
                fila.Ruta(ruta);
            } else {
                //Crea un nuevo nodo
                NodoM nuevo = new NodoM(ruta, origen, destino);
                //Enlaza la fila
                nuevo.siguiente = fila.siguiente;
                nuevo.anterior = fila;
                if (fila.siguiente != null) {
                    fila.siguiente.anterior = nuevo;
                }
                fila.siguiente = nuevo;
                //Enlaza la columna
                nuevo.arriba = col;
                nuevo.abajo = col.abajo;
                if (col.abajo != null) {
                    col.abajo.abajo = nuevo;
                }
                col.abajo = nuevo;
            }
        }
    }

    /**
     * Genera el script necesario para mostrar la matriz y los encabezados
     *
     * @return
     */
    public String graficarMB() {
        if (idOrigen.esVacio()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        //Se debe utilizar el árbolB de origen para generar la matriz
        int p = this.idDestino.profundidad(this.idDestino.raiz);
        sb.append(String.format("graph {\nnode[shape=record; fontsize = 8; width = 0.1; height = 0.1];\n%s\n%s\n%s\n}", graficarMB_Arbol(this.idOrigen.raiz, true, p), graficarMB_Arbol(this.idDestino.raiz, false, p), graficarMB_Matriz(this.idOrigen.raiz)));
        return sb.toString();
    }

    /**
     * Genera el script para generar la gráfica del árbol y la matriz
     *
     * @param raiz
     * @return script de la matriz
     */
    private String graficarMB_Matriz(Rama raiz) {
        if (raiz == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (NodoB temp : raiz) {
            sb.append(graficarMB_Matriz(temp.izquierda));
            if (temp.ruta != null) {
                StringBuilder rs = new StringBuilder();
                StringBuilder cl = new StringBuilder();
                rs.append(String.format("MG%d", temp.ruta.hashCode()));
                cl.append(String.format("MG%d", temp.ruta.hashCode()));
                for (NodoM pivote : temp.ruta) {
                    sb.append(String.format("MG%d[label=\"Q.%.2f\\n%.2f min\"; pos = \"%d,%d!\"];\n", pivote.hashCode(), pivote.Ruta().Costo(), pivote.Ruta().Tiempo(), posDestino(pivote.Destino()), posOrigen(pivote.Origen())));
                    if (pivote.siguiente != null) {
                        rs.append(String.format("-- MG%d", pivote.siguiente.hashCode()));
                    }
                    if (pivote.abajo != null) {
                        cl.append(String.format("-- MG%d", pivote.abajo.hashCode()));
                    }
                }
                rs.append(";\n");
                cl.append(";\n");
                sb.append(rs.toString()).append(cl.toString());
            }
            if (temp.siguiente == null) {
                sb.append(graficarMB_Matriz(temp.derecha));
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param raiz de una cabecera árbolB
     * @return
     */
    private String graficarMB_Arbol(Rama raiz, boolean esOrigen, int profundidad) {
        if (raiz == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        //Crea la rama
        if (esOrigen) {
            sb.append(String.format("MG%d[label=\"%s\"; pos=\"%d,%d!\"; height = 0.01 ; width = 0.01];\n", raiz.hashCode(), raiz.espejoMB(), -profundidad, posOrigen(raiz.primero.Dato().Codigo())));

        } else {
            sb.append(String.format("MG%d[label=\"%s\"; pos=\"%d,%d!\"; height = 0.01 ; width = 0.01];\n", raiz.hashCode(), raiz.ramaMB(), posDestino(raiz.primero.Dato().Codigo()), this.dimension + profundidad));
        }
        int c_aux = !esOrigen ? 0 : raiz.Largo() * 2;
        //Recorre la rama y enlaza los subramas
        for (NodoB nb : raiz) {
            if (nb.izquierda != null) {
                sb.append(String.format("MG%d:f%d--MG%d;\n", raiz.hashCode(), c_aux, nb.izquierda.hashCode()));
            }
            //Enlaza a la matriz
            if (nb.ruta != null) {
                sb.append(String.format("MG%d:f%d--MG%d;\n", raiz.hashCode(), !esOrigen ? c_aux + 1 : c_aux - 1, nb.ruta.hashCode()));
            }
            c_aux = c_aux + (!esOrigen ? 2 : -2);
            if (nb.siguiente == null && nb.derecha != null) {
                sb.append(String.format("MG%d:f%d--MG%d;\n", raiz.hashCode(), c_aux, nb.derecha.hashCode()));
            }
        }
        //Recorre el resto del árbol
        for (NodoB nb : raiz) {
            sb.append(graficarMB_Arbol(nb.izquierda, esOrigen, profundidad - 1));
            if (nb.siguiente == null) {
                sb.append(graficarMB_Arbol(nb.derecha, esOrigen, profundidad - 1));
            }
        }
        return sb.toString();
    }

    /**
     * Localiza la posoción de un nodo con {@code clave} en la cabecera de
     * origenes
     *
     * @param clave
     * @return
     */
    private int posOrigen(int clave) {
        Matriz.cont = 0;
        idOrigen.buscarPos(idOrigen.raiz, clave);
        return Matriz.cont;
    }

    /**
     * Localiza la posoción de un nodo con {@code clave} en la cabecera de
     * destinos
     *
     * @param clave
     * @return
     */
    private int posDestino(int clave) {
        Matriz.cont = 0;
        idDestino.buscarPos(idDestino.raiz, clave);
        return Matriz.cont;
    }
}
