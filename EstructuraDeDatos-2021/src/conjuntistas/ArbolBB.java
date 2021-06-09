/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

import lineales.dinamica.Lista;

/**
 *
 * @author Facu
 */
public class ArbolBB {
    // atributos

    private NodoABB raiz;

    // constructor vacio
    public ArbolBB() {
        // crea un árbol sin elementos
        this.raiz = null;
    }

    // métodos
    public boolean esVacio() {
        // devuelve falso si hay al menos un elemento cargado en el árbol y 
        // verdadero en caso contrario 
        return this.raiz == null;
    }

    public boolean insertar(Comparable elem) {
        /* Recibe un elemento y lo agrega en el árbol de manera ordenada.
           Precondición el elemento no debe estar el árbol
           Devuelve verdadero si agrega el elemento a la estructura, falso en 
           caso contrario */
        boolean exito;
        NodoABB nodo = this.raiz;
        if (nodo == null) {
            this.raiz = new NodoABB(elem, null, null);
            exito = true;
        } else {
            exito = insertarAux(nodo, elem);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB nodo, Comparable elem) {
        // método recursivo PRIVADO
        int comparacion = nodo.getElem().compareTo(elem);
        boolean exito;
        if (comparacion < 0) {
            if (nodo.getDerecho() != null) {
                exito = insertarAux(nodo.getDerecho(), elem);
            } else {
                nodo.setDerecho(new NodoABB(elem, null, null));
                exito = true;
            }
        } else if (comparacion > 0) {
            if (nodo.getIzquierdo() != null) {
                exito = insertarAux(nodo.getIzquierdo(), elem);
            } else {
                nodo.setIzquierdo(new NodoABB(elem, null, null));
                exito = true;
            }

        } else {
            exito = false;
        }
        return exito;
    }

    public boolean eliminar(Comparable elem) {
        /* recibe un elemento que se desea eliminar y se procede a removerlo del
           árbol. Devuelve verdadero si el elemento se elimina de la estructura
           y falso en caso contrario */
        boolean exito = false;
        if (this.raiz != null) {
            exito = eliminarAux(null, this.raiz, elem);
        }
        return exito;
    }

    private boolean eliminarAux(NodoABB padre, NodoABB nodo, Comparable elem) {
        // método recursivo privado
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elem) == 0) { // caso base
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    exito = eliminarCasoUno(padre, nodo); // eliminar hoja
                } else if ((nodo.getIzquierdo() != null && nodo.getDerecho() == null)
                        || (nodo.getIzquierdo() == null && nodo.getDerecho() != null)) {
                    exito = eliminarCasoDos(padre, nodo); // eliminar nodo con un hijo
                } else {
                    exito = eliminarCasoTres(nodo); // eliminar nodo con dos hijos
                }
            } else { // paso recursivo
                padre = nodo;
                if (nodo.getElem().compareTo(elem) > 0) {
                    nodo = nodo.getIzquierdo();
                } else {
                    nodo = nodo.getDerecho();
                }
                exito = eliminarAux(padre, nodo, elem);
            }
        }
        return exito;
    }

    private boolean eliminarCasoUno(NodoABB padre, NodoABB nodo) {
        // método recursivo privado
        // elimina hoja
        if (padre == null) {
            this.raiz = null;
        } else if (padre.getIzquierdo() != null
                && padre.getIzquierdo().getElem().compareTo(nodo.getElem()) == 0) {
            padre.setIzquierdo(null);
        } else {
            padre.setDerecho(null);
        }
        return true;
    }

    private boolean eliminarCasoDos(NodoABB padre, NodoABB nodo) {
        // método recursivo privado
        // eliminar nodo con un hijo
        if (padre == null) {
            if (nodo.getIzquierdo() != null) {
                this.raiz = nodo.getIzquierdo();
            } else {
                this.raiz = nodo.getDerecho();
            }
        } else if (padre.getIzquierdo() != null
                && padre.getIzquierdo().getElem().compareTo(nodo.getElem()) == 0) {
            if (nodo.getIzquierdo() != null) {
                padre.setIzquierdo(nodo.getIzquierdo());
            } else {
                padre.setIzquierdo(nodo.getDerecho());
            }
        } else {
            if (nodo.getIzquierdo() != null) {
                padre.setDerecho(nodo.getIzquierdo());
            } else {
                padre.setDerecho(nodo.getDerecho());
            }
        }
        return true;
    }

    private boolean eliminarCasoTres(NodoABB nodo) {
        // método recursivo privado
        // eliminar nodo con dos hijos
        NodoABB aux1, aux2;
        aux1 = nodo.getIzquierdo();
        aux2 = null;
        while (aux1.getDerecho() != null) {
            aux2 = aux1;
            aux1 = aux1.getDerecho();
        }
        nodo.setElem(aux1.getElem());
        if (aux2 != null) {
            aux2.setDerecho(aux1.getIzquierdo());
        } else {
            nodo.setIzquierdo(nodo.getIzquierdo().getIzquierdo());
        }
        return true;
    }

    public boolean pertenece(Comparable elem) {
        // devuelve verdadero si el elemento recibido por parámetri está en el 
        // árbol y falso en caso contrario
        boolean exito = false;
        if (this.raiz != null) {
            exito = perteneceAux(this.raiz, elem);
        }
        return exito;
    }

    private boolean perteneceAux(NodoABB nodo, Comparable elem) {
        // método recursivo PRIVADO
        boolean exito;
        int comparacion = nodo.getElem().compareTo(elem);
        if (comparacion == 0) {
            exito = true;
        } else if (comparacion < 0) {
            if (nodo.getDerecho() != null) {
                exito = perteneceAux(nodo.getDerecho(), elem);
            } else {
                exito = false;
            }
        } else {
            if (nodo.getIzquierdo() != null) {
                exito = perteneceAux(nodo.getIzquierdo(), elem);
            } else {
                exito = false;
            }
        }
        return exito;
    }

    public void vaciar() {
        // quita todos los elementos de la estructura
        this.raiz = null;
    }

    public Lista listar() {
        // devuelve una lista con los elementos del árbol binario en el recorrido en inorden
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenAux(NodoABB nodo, Lista lis) {
        // método recursivo PRIVADO
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    public Lista listarInvertido() {
        // devuelve una lista con los elementos del árbol binario en el recorrido en inorden
        Lista lis = new Lista();
        listarInordenInvertidoAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenInvertidoAux(NodoABB nodo, Lista lis) {
        // método recursivo PRIVADO
        if (nodo != null) {
            listarInordenInvertidoAux(nodo.getDerecho(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarInordenInvertidoAux(nodo.getIzquierdo(), lis);
        }
    }

    public Lista listarRango(Comparable min, Comparable max) {
        // recorre parte del árbol y devuelve una lista ordenada con los 
        // elementos que se encuentran en el intervalo [min, max]
        Lista lis = new Lista();
        listarRangoAux(this.raiz, min, max, lis);
        return lis;
    }

    private void listarRangoAux(NodoABB nodo, Comparable min, Comparable max, Lista lis) {
        // método recursivo PRIVADO
        if (nodo != null) {
            if (nodo.getElem().compareTo(min) > 0) {
                listarRangoAux(nodo.getIzquierdo(), min, max, lis);
            }

            if (nodo.getElem().compareTo(min) >= 0 && nodo.getElem().compareTo(max) <= 0) {
                lis.insertar(nodo.getElem(), lis.longitud() + 1);
            }

            if (nodo.getElem().compareTo(max) < 0) {
                listarRangoAux(nodo.getDerecho(), min, max, lis);
            }
        }
    }

    public Comparable minimoElem() {
        // recorre la rama correspondiente y devuelve el elemento más pequeño
        Comparable elem = null;
        NodoABB nodo = this.raiz;
        if (nodo != null) {
            while (nodo.getIzquierdo() != null) {
                nodo = nodo.getIzquierdo();
            }
            elem = nodo.getElem();
        }
        return elem;
    }

    public Comparable maximoElem() {
        // recorre la rama correspondiente y devuelve el elemento más grande
        Comparable elem = null;
        NodoABB nodo = this.raiz;
        if (nodo != null) {
            while (nodo.getDerecho() != null) {
                nodo = nodo.getDerecho();
            }
            elem = nodo.getElem();
        }
        return elem;
    }

    public String toString() {
        // genera y devuelve una cadena de caracteres que indica cuál es la raíz
        // del árbol y quienes son sus hijos de cada nodo.
        String cadena = "";
        if (this.esVacio()) {
            cadena = "Arbol vacio";
        } else {
            cadena = aCadena(this.raiz, cadena);
        }
        return cadena;
    }

    private String aCadena(NodoABB nodo, String cadena) {
        // método recursivo PRIVADO
        if (nodo != null) {
            // visita el nodo y agrega el nodo y los hijos a la cadena
            cadena += nodo.getElem().toString();
            if (nodo.getIzquierdo() != null) {
                cadena += "\tHI: " + nodo.getIzquierdo().getElem().toString();
            } else {
                cadena += "\tHI: ";
            }
            if (nodo.getDerecho() != null) {
                cadena += "\tHD: " + nodo.getDerecho().getElem().toString() + "\n";
            } else {
                cadena += "\tHD:\n";
            }
            // recorre a sus hijos en preorden
            cadena = aCadena(nodo.getIzquierdo(), cadena);
            cadena = aCadena(nodo.getDerecho(), cadena);
        }
        return cadena;
    }


    /* ---- Ejercicios adicionales ---- */
    // eliminarMinimo(): void
    public void eliminarMinimo() {
        if (this.raiz != null) {
            if(this.raiz.getDerecho() == null && this.raiz.getIzquierdo() == null){
                this.raiz = null;
            }else{
                if(this.raiz.getDerecho() != null && this.raiz.getIzquierdo() == null){
                    this.raiz = this.raiz.getDerecho();
                }else{
                    eliminarMinimoAux(this.raiz, null);
                }
            }
        }
    }

    private void eliminarMinimoAux(NodoABB nodo, NodoABB padre) {

        if (nodo != null) {
            while(nodo.getIzquierdo() != null){
                padre = nodo;
                nodo = nodo.getIzquierdo();
            }
            if(nodo.getDerecho() == null){
                padre.setIzquierdo(null);
            }else{
                padre.setIzquierdo(nodo.getDerecho());
            }
        }
    }

    public ArbolBB clonarParteInvertida(Comparable elem){
        ArbolBB copia = new ArbolBB();
        ArbolBB arbolParteInvertida = new ArbolBB();
        
        if(this.raiz != null){
            copia.raiz = buscarSubarbol(this.raiz, elem);
            if(copia.raiz.getElem() != null){
                arbolParteInvertida.raiz = clonarParteInvertidaAux(copia.raiz);
            }
        }
        return arbolParteInvertida;
    }

    private NodoABB buscarSubarbol(NodoABB nodo, Comparable elem){
        NodoABB auxiliar = null;
        if(nodo != null){
           if(nodo.getElem().compareTo(elem) == 0){
                auxiliar = nodo;
            }else{
                if(nodo.getElem().compareTo(elem) > 0){
                    auxiliar = buscarSubarbol(nodo.getIzquierdo(), elem);
                }else{
                    auxiliar = buscarSubarbol(nodo.getDerecho(), elem);
                }
            } 
        }
        return auxiliar;
    }
    
    private NodoABB clonarParteInvertidaAux(NodoABB nodo){
        NodoABB nuevo = new NodoABB(nodo.getElem(), null, null);
        
        if(nodo.getIzquierdo() != null){
            nuevo.setDerecho(clonarParteInvertidaAux(nodo.getIzquierdo()));
        }
        
        if(nodo.getDerecho() != null){
            nuevo.setIzquierdo(clonarParteInvertidaAux(nodo.getDerecho()));
        }
        
        return nuevo;
    }
    
    public Lista listarMayorIgual(Comparable elem){
        Lista listaRetornar = new Lista();
        if(this.raiz != null){
            listarMayorIgualAux(this.raiz, elem, listaRetornar);
        }
        return listaRetornar;
    }
    
    private void listarMayorIgualAux(NodoABB nodo, Comparable elem, Lista lis){
        int comparacion = nodo.getElem().compareTo(elem);
        
        if(nodo.getIzquierdo() != null && comparacion > 0){
            listarMayorIgualAux(nodo.getIzquierdo(), elem, lis);
        }
        
        if(comparacion >= 0){
            lis.insertar(nodo.getElem(), 1);
        }
        
        if(nodo.getDerecho() != null && (comparacion <= 0 || comparacion >= 0)){
            listarMayorIgualAux(nodo.getDerecho(), elem, lis);
        }
    }

    public Lista listarMenores(Comparable elem) {
        Lista listaRetornar = new Lista();
        if (this.raiz != null) {
            listarMenoresAux(this.raiz, elem, listaRetornar);
        }
        return listaRetornar;
    }

    private void listarMenoresAux(NodoABB nodo, Comparable elem, Lista lis) {
        int comparacion = nodo.getElem().compareTo(elem);

        if (nodo.getIzquierdo() != null) {
            listarMenoresAux(nodo.getIzquierdo(), elem, lis);
        }

        if(comparacion < 0){
            lis.insertar(nodo.getElem(), lis.longitud()+1);
        }
        
        if(nodo.getDerecho() != null && comparacion < 0){
            listarMenoresAux(nodo.getDerecho(), elem, lis);
        }
    }

}
