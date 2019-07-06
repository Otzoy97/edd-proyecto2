/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructura.arbolb;

import com.viaje.Destino;

/**
 * Rama de un árbol B
 *
 * @author otzoy
 */
public class ArbolB {

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
    public void agregar(Destino dato) {
        if (!esVacio()) {
            Object obj = agregar(new NodoB(dato), raiz);
            if (obj instanceof NodoB) {
                raiz = new Rama();
                raiz.agregar((NodoB) obj);
                raiz.esHoja(false);
            }
        } else {
            raiz = new Rama();
            raiz.agregar(new NodoB(dato));
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
    public NodoB buscar(Rama raiz, int codigo) {
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
    public Object dividir(Rama rama) {
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
}
