/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

import lineales.dinamica.Lista;
import lineales.dinamica.Cola;

/**
 *
 * @author Facu
 */
public class ArbolBin {

    // Atributos
    private NodoArbol raiz;

    // Constructores
    public ArbolBin() {
        this.raiz = null;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                // si el buscado es n, lo devuelve
                resultado = n;
            } else {
                // no es el buscado: buscado primero en el HI
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                // si no lo encuentra en el HI, busca en HD
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    // insertar(TipoElemento, TipoElemento, char): boolean
    public boolean insertar(Object elemNuevo, Object elemPadre, char posicion) {
        boolean exito = true;

        if (this.raiz == null) {
            // si el arbol esta vacio, ponemos el elem nuevo en la raiz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            // si no esta vacio, se busca el padre
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                if (posicion == 'I' && nodoPadre.getIzquierdo() == null) {
                    // si el padre existe y no tiene HI se lo agrega
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else {
                    if (posicion == 'D' && nodoPadre.getDerecho() == null) {
                        // si el padre existe y no tiene HD se lo agrega
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                    } else {
                        //si el padre no existe o ya tiene ese hijo, da error
                        exito = false;
                    }
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    // esVacio(): boolean
    public boolean esVacio() {
        boolean vacio = false;

        if (this.raiz == null) {
            // si la raiz es nula entonces el arbol binario es vacio
            vacio = true;
        }

        return vacio;
    }

    // padre(TipoElemento): TipoElemento
    public Object padre(Object elemBuscado) {
        Object res = null;
        if (this.raiz != null) {
            if (this.raiz.getElem().equals(elemBuscado) == false) {
                res = padreAux(this.raiz, elemBuscado);
            }
        }
        return res;
    }

    private Object padreAux(NodoArbol nodo, Object elemBuscado) {
        Object res = null;
        NodoArbol nodoIzq;
        NodoArbol nodoDer;

        if (nodo != null) {
            nodoIzq = nodo.getIzquierdo();
            nodoDer = nodo.getDerecho();

            if (nodoIzq != null && nodoIzq.getElem().equals(elemBuscado) || nodoDer != null && nodoDer.getElem().equals(elemBuscado)) {
                // si nodoIzq es distinto de nulo y ademas es igual al elemento que buscamos encontramos el padre
                // o
                // si nodoDer es distinto de nulo y ademas es igual al elemento que buscamos encontramos el padre
                res = nodo.getElem();
            } else {
                // si lo anterior es falso, entonces empezamos a recorrer por sus hijos
                res = padreAux(nodo.getIzquierdo(), elemBuscado);
                // recorremos por HI
                if (res == null) {
                    res = padreAux(nodo.getDerecho(), elemBuscado);
                    // recorremos por HD
                }
            }
        }
        return res;
    }

    // altura(): int
    public int altura() {
        int resultado = -1;
        if (this.raiz != null) {
            resultado = alturaAux(this.raiz);
        }
        return resultado;
    }

    private int alturaAux(NodoArbol nodo) {
        int alturaIzq, alturaDer, alturaArbol;
        alturaIzq = -1;
        alturaDer = -1;

        if (nodo != null) {
            alturaIzq = alturaAux(nodo.getIzquierdo()) + 1; // recorro el subarbol izquierdo
            alturaDer = alturaAux(nodo.getDerecho()) + 1; // recorro el subarbol derecho
        }

        if (alturaIzq > alturaDer) {
            alturaArbol = alturaIzq;
        } else {
            alturaArbol = alturaDer;
        }

        return alturaArbol;
    }

    // nivel(tipoElemento): int
    public int nivel(Object elemNivel) {
        int nivel = -1;
        if (this.raiz != null) {
            nivel = nivelAux(this.raiz, elemNivel);
        }
        return nivel;
    }

    private int nivelAux(NodoArbol nodo, Object elemNivel) {
        int nivel = -1;

        if (nodo != null) {
            if (nodo.getElem().equals(elemNivel)) {
                // si elemNivel = raiz entones nivel = 0
                nivel = nivel + 1;
            } else {
                nivel = nivelAux(nodo.getIzquierdo(), elemNivel);
                // empiezo a recorrer por HI
                if (nivel != -1) {
                    nivel = nivel + 1;
                } else {
                    nivel = nivelAux(nodo.getDerecho(), elemNivel);
                    // empiezo a recorrer por HD
                    if (nivel != -1) {
                        nivel = nivel + 1;
                    }
                }
            }
        }
        return nivel;
    }

    // vaciar()
    public void vaciar() {
        this.raiz = null;
    }

    // clone(): ArbolBin
    public ArbolBin clone() {
        ArbolBin copia = new ArbolBin();
        if (!this.esVacio()) {
            copia.raiz = clonAux(this.raiz);
        }
        return copia;
    }

    private NodoArbol clonAux(NodoArbol nodo) {
        NodoArbol nuevo = new NodoArbol(nodo.getElem(), null, null);
        if (nodo.getIzquierdo() != null) {
            nuevo.setIzquierdo(clonAux(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nuevo.setDerecho(clonAux(nodo.getDerecho()));
        }
        return nuevo;
    }

    // toString(): String
    public String toString() {
        String cadena = "Arbol binario vacio";

        if (this.raiz != null) {
            cadena = toStringAuxiliar(this.raiz, "");
        }

        return cadena;
    }

    private String toStringAuxiliar(NodoArbol nodo, String cadena) {
        NodoArbol nodoIzq, nodoDer;

        if (nodo != null) {
            cadena = cadena + "\n" + nodo.getElem() + " ";

            nodoIzq = nodo.getIzquierdo();
            nodoDer = nodo.getDerecho();

            if (nodoIzq != null && nodoDer != null) {
                cadena = cadena + "HI: " + nodoIzq.getElem() + "    HD: " + nodoDer.getElem();
            } else {
                if (nodoIzq != null) {
                    cadena = cadena + "HI: " + nodoIzq.getElem() + "    HD: -";

                } else {
                    if (nodoDer != null) {
                        cadena = cadena + "HI: -" + "    HD: " + nodoDer.getElem();
                    } else {
                        cadena = cadena + "HI: -" + "    HD: -";
                    }
                }
            }

            cadena = toStringAuxiliar(nodoIzq, cadena);
            cadena = toStringAuxiliar(nodoDer, cadena);
        }
        return cadena;
    }

    // listarPreorden(): Lista
    public Lista listarPreorden() {
        // retornar una lista con los elementos del arbol en PREORDEN
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        // metodo recursivo PRIVADO porque su parametro es de tipo NodoArbol
        if (nodo != null) {
            // visita el elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1);

            // recorre a sus hijos en preorden
            listarPreordenAux(nodo.getIzquierdo(), lis);
            listarPreordenAux(nodo.getDerecho(), lis);
        }
    }

    // listarPosorden(): Lista
    public Lista listarPosorden() {
        // retornar una lista con los elementos del arbol en POSORDEN
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            // recorre a sus hijos en posorden
            listarPosordenAux(nodo.getIzquierdo(), lis);
            listarPosordenAux(nodo.getDerecho(), lis);

            // visita el elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
        }
    }

    // listarInorden(): Lista
    public Lista listarInorden() {
        // retornar una lista con los elementos del arbol en INORDEN
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            // recorre a sus hijos en inorden
            listarInordenAux(nodo.getIzquierdo(), lis);

            // visita el elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1);

            // recorre a sus hijos en inorden
            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    // listarPorNiveles(): Lista
    public Lista listarPorNiveles() {
        Lista lis = new Lista();
        Cola q = new Cola();
        NodoArbol nodoActual;

        if (this.raiz != null) {
            q.poner(this.raiz);

            while (q.esVacia() == false) {
                nodoActual = (NodoArbol) q.obtenerFrente();
                q.sacar();

                lis.insertar(nodoActual.getElem(), lis.longitud() + 1);

                if (nodoActual.getIzquierdo() != null) {
                    q.poner(nodoActual.getIzquierdo());
                }

                if (nodoActual.getDerecho() != null) {
                    q.poner(nodoActual.getDerecho());
                }
            }
        }
        return lis;
    }

    // frontera(): Lista
    public Lista frontera() {
        Lista lis = new Lista();
        if (this.raiz != null) {
            fronteraAux(this.raiz, lis);
        }
        return lis;
    }

    private void fronteraAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                lis.insertar(nodo.getElem(), lis.longitud() + 1);
            }
            fronteraAux(nodo.getIzquierdo(), lis);
            fronteraAux(nodo.getDerecho(), lis);
        }
    }

    // cloneInvertido(): ArbolBin
    public ArbolBin cloneInvertido() {
        ArbolBin copia = new ArbolBin();
        if (this.esVacio() == false) {
            copia.raiz = cloneInvertidoAux(this.raiz);
        }

        return copia;
    }

    private NodoArbol cloneInvertidoAux(NodoArbol nodo) {
        NodoArbol nuevo = new NodoArbol(nodo.getElem(), null, null);

        if (nodo.getDerecho() != null) {
            nuevo.setIzquierdo(cloneInvertidoAux(nodo.getDerecho()));
        }

        if (nodo.getIzquierdo() != null) {
            nuevo.setDerecho(cloneInvertidoAux(nodo.getIzquierdo()));
        }

        return nuevo;
    }

    public boolean verificarPatron(Lista patron) {
        /**
         * Recibe una lista por parametro y retorna true en caso de que coincida
         * con al menos un camino del arbol, caso contrario retorna false.
         */
        boolean exito;
        if (patron.esVacia() && this.raiz == null) {
            exito = true;
        } else {
            exito = verificarPatronRecursivo(this.raiz, patron, 1);
        }
        return exito;
    }

    private boolean verificarPatronRecursivo(NodoArbol nodo, Lista lis, int pos) {
        boolean controlIzq = false, control = true;

        if (pos <= lis.longitud()) {

            if (nodo != null) {
                control = nodo.getElem().equals(lis.recuperar(pos));

                if (control == true) {
                    controlIzq = verificarPatronRecursivo(nodo.getIzquierdo(), lis, pos + 1);
                }

                if (controlIzq == false && control == true) {
                    control = verificarPatronRecursivo(nodo.getDerecho(), lis, pos + 1);
                }
            } else {
                control = false;
            }
        }

        return control;
    }

    public void alterarParte(Object hi, Object hd, Object pp) {

        if (this.raiz != null) {
            alterarParteAux(hi, hd, pp, this.raiz);
        }
    }

    private void alterarParteAux(Object hi, Object hd, Object pp, NodoArbol nodo) {

        if (nodo != null) {

            if (nodo.getElem().equals(pp) == false) {
                alterarParteAux(hi, hd, pp, nodo.getIzquierdo());
                alterarParteAux(hi, hd, pp, nodo.getDerecho());
            } else {
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    nodo.setIzquierdo(new NodoArbol(hi, null, null));
                    nodo.setDerecho(new NodoArbol(hd, null, null));
                } else {
                    if (nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
                        nodo.setIzquierdo(new NodoArbol(hi, null, null));
                        nodo.getDerecho().setElem(hd);
                    } else {
                        if (nodo.getIzquierdo() != null && nodo.getDerecho() == null) {
                            nodo.getIzquierdo().setElem(hi);
                            nodo.setDerecho(new NodoArbol(hd, null, null));
                        } else {
                            nodo.getIzquierdo().setElem(hi);
                            nodo.getDerecho().setElem(hd);
                        }
                    }
                }

            }

        }
    }

}
