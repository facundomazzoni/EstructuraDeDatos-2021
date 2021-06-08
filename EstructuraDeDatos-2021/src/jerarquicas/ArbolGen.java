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
public class ArbolGen {

    // Atributos 
    private NodoGen raiz;

    // Constructor
    public ArbolGen() {
        this.raiz = null;
    }

    // insertar(Object elemNuevo, Object elemPadre): boolean
    public boolean insertar(Object elemNuevo, Object elemPadre) {
        /* dado un elemento elemNuevo y un elemento elemPadre, agrega elemNuevo
           como hijo de la primer aparición de elemPadre. Devuelve verdadero
           cuando se pudo agregar el elemNuevo a la estructua y falso en caso 
           contrario. Esta implementación respeta el orden de insertado.
         */
        boolean exito = true;
        if (this.raiz == null) {
            // si el árbol esta vacio
            this.raiz = new NodoGen(elemNuevo, null, null);
        } else {
            // si no esta vacio, se busca el padre
            NodoGen aux = obtenerNodo(this.raiz, elemPadre);
            if (aux != null) {
                if (aux.getHijoIzquierdo() == null) {
                    aux.setHijoIzquierdo(new NodoGen(elemNuevo, null, null));
                } else {
                    aux = aux.getHijoIzquierdo();
                    while (aux.getHermanoDerecho() != null) {
                        aux = aux.getHermanoDerecho();
                    }
                    aux.setHermanoDerecho(new NodoGen(elemNuevo, null, null));
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen nodo, Object elem) {
        // método PRIVADO que busca un elemento y devuelve el nodo que
        // lo contiene. Si no se encuentra devuelve null.
        NodoGen resultado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) // si el buscado es nodo, lo devuelve
            {
                resultado = nodo;
            } else {
                // no es el buscado: busca primero en el HI
                resultado = obtenerNodo(nodo.getHijoIzquierdo(), elem);
                // si no lo encuentra en el HEI, busca en el HD
                if (resultado == null) {
                    NodoGen aux = nodo.getHijoIzquierdo();
                    while (resultado == null && aux != null) {
                        resultado = obtenerNodo(aux.getHermanoDerecho(), elem);
                        aux = aux.getHermanoDerecho();
                    }
                }
            }
        }
        return resultado;
    }

    // pertenece(Object elem): boolean
    public boolean pertenece(Object elem) {
        // devuelve verdadero si el elemento pasado por parámetro está en el 
        // árbol, y falso en caso contrario.
        NodoGen nodo = null;
        if (this.raiz != null) {
            nodo = obtenerNodo(this.raiz, elem);
        }
        return nodo != null;
    }

    // ancestros(Object elem): Lista
    public Lista ancestros(Object elem) {
        /* si le elemento se encuentra en el árbol, devuelve una lista con el 
           camino desde la raiz hasta dicho elemento. Si el elemento no está en
           en el árbol devuelve una lista vaciía*/
        Lista res = new Lista();
        if (this.raiz != null) {
            res = (this.raiz.getElem().equals(elem)) ? res : ancestrosAux(this.raiz, elem, res);
        }
        return res;
    }

    private Lista ancestrosAux(NodoGen nodo, Object elem, Lista lis) {
        // método recursivo PRIVADO
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (lis.esVacia() && hijo != null) {
                if (hijo != null) {
                    if (hijo.getElem().equals(elem)) {
                        lis.insertar(nodo.getElem(), lis.longitud() + 1);
                    } else {
                        lis = ancestrosAux(hijo, elem, lis);
                        if (!lis.esVacia()) {
                            lis.insertar(nodo.getElem(), lis.longitud() + 1);
                        }
                    }
                }
                hijo = hijo.getHermanoDerecho();
            }
        }
        return lis;
    }

    // padre(Object elem): Object
    public Object padre(Object elem) {
        // dado un elemento devuelve el valor almacenado en su nodo padre
        Object elemPadre = null;
        if (this.raiz != null) {
            elemPadre = (this.raiz.getElem().equals(elem)) ? null : padreAux(this.raiz, elem);
        }
        return elemPadre;
    }

    private Object padreAux(NodoGen nodo, Object elem) {
        // método recursivo PRIVADO
        Object elemPadre = null;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (elemPadre == null && hijo != null) {
                if (hijo.getElem().equals(elem)) {
                    elemPadre = nodo.getElem();
                } else {
                    elemPadre = padreAux(hijo, elem);
                }
                hijo = hijo.getHermanoDerecho();
            }
        }
        return elemPadre;
    }

    // esVacio(): boolean
    public boolean esVacio() {
        // devuelve falso si hay al menos un elemento cargado en el árbol y 
        // verdadero en caso contrario
        return this.raiz == null;
    }

    // altura(): int
    public int altura() {
        // devuelve la altura del árbol, es decir la longitud del camino más largo
        // desde la raíz hasta una hoja
        int res = -1;
        int max = -1;
        if (this.raiz != null) {
            res = alturaAux(this.raiz);
        }
        return res;
    }

    private int alturaAux(NodoGen nodo) {
        // método recursivo PRIVADO
        int aux = -1;
        int res = -1;
        NodoGen hijo;
        if (nodo != null) {
            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                aux = alturaAux(hijo);
                res = (aux > res) ? aux : res;
                hijo = hijo.getHermanoDerecho();
            }
            res++;
        }
        return res;
    }

    // nivel(Object elem): int
    public int nivel(Object elem) {
        // devuelve el nivel de un elemento en el árbol
        // árbol vacío tiene altura -1 y hoja tiene altura 0.
        int nvl = -1;
        if (this.raiz != null) {
            nvl = nivelAux(this.raiz, elem, nvl);
        }
        return nvl;
    }

    private int nivelAux(NodoGen nodo, Object elem, int nvl) {
        // método recursivo PRIVADO
        boolean exito = false;
        int temp = nvl;
        NodoGen hijo;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                nvl++;
            } else {
                hijo = nodo.getHijoIzquierdo();
                while (!exito && hijo != null) {
                    nvl = nivelAux(hijo, elem, nvl);
                    if (nvl > temp) {
                        nvl++;
                        exito = true;
                    } else {
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return nvl;
    }

    // vaciar(): void
    public void vaciar() {
        // quita todos los elementos de la estructura
        this.raiz = null;
    }

    // listarPreorden(): Lista
    public Lista listarPreorden() {
        // devuelve una lista con los elementos del árbol binario en el recorrido en preorden
        Lista salida = new Lista();
        listarPreordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPreordenAux(NodoGen n, Lista lis) {
        // método recursivo PRIVADO
        if (n != null) {
            lis.insertar(n.getElem(), lis.longitud() + 1);
            listarPreordenAux(n.getHijoIzquierdo(), lis);
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                listarPreordenAux(hijo.getHermanoDerecho(), lis);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    // listarInorden(): Lista
    public Lista listarInorden() {
        // devuelve una lista con los elementos del árbol binario en el recorrido en inorden
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }

    private void listarInordenAux(NodoGen n, Lista lis) {
        // método recursivo PRIVADO
        if (n != null) {
            listarInordenAux(n.getHijoIzquierdo(), lis);
            lis.insertar(n.getElem(), lis.longitud() + 1);
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                listarInordenAux(hijo.getHermanoDerecho(), lis);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    // listarPosorden(): Lista
    public Lista listarPosorden() {
        // devuelve una lista con los elementos del árbol binario en el recorrido en posorden
        Lista salida = new Lista();
        listarPosordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPosordenAux(NodoGen n, Lista lis) {
        // método recursivo PRIVADO
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                listarPosordenAux(hijo, lis);
                hijo = hijo.getHermanoDerecho();
            }
            lis.insertar(n.getElem(), lis.longitud() + 1);
        }
    }

    // listarPorNiveles(): Lista
    public Lista listarPorNiveles() {
        // devuelve una lista con los elementos del árbol binario en el recorrido por niveles
        NodoGen nodoActual, aux;
        Lista lis = new Lista();
        // nueva cola
        Cola col = new Cola();
        int pos = 1;
        if (!this.esVacio()) {
            // poner nodo raiz en la cola
            col.poner(this.raiz);
            while (!col.esVacia()) {
                nodoActual = (NodoGen) col.obtenerFrente();
                lis.insertar(nodoActual.getElem(), pos);
                pos++;
                col.sacar();
                if (nodoActual.getHijoIzquierdo() != null) {
                    col.poner(nodoActual.getHijoIzquierdo());
                    aux = nodoActual.getHijoIzquierdo();
                    while (aux.getHermanoDerecho() != null) {
                        col.poner(aux.getHermanoDerecho());
                        aux = aux.getHermanoDerecho();
                    }
                }
            }
        }
        return lis;
    }

    // clone(): ArbolGen
    public ArbolGen clone() {
        // genera y devuelve un árbol genérico que es equivalente al árbol original
        ArbolGen copia = new ArbolGen();
        if (this.raiz != null) {
            copia.raiz = new NodoGen(this.raiz.getElem(), null, null);
        }
        clonarNodo(this.raiz, copia.raiz);
        return copia;
    }

    private void clonarNodo(NodoGen nodo, NodoGen copia) {
        // método recursivo PRIVADO
        NodoGen aux;
        if (nodo.getHijoIzquierdo() != null) {
            copia.setHijoIzquierdo(new NodoGen(nodo.getHijoIzquierdo().getElem(), null, null));
            aux = copia.getHijoIzquierdo();
            clonarNodo(nodo.getHijoIzquierdo(), copia.getHijoIzquierdo());
            nodo = nodo.getHijoIzquierdo();
            while (nodo.getHermanoDerecho() != null) {
                aux.setHermanoDerecho(new NodoGen(nodo.getHermanoDerecho().getElem(), null, null));
                aux = aux.getHermanoDerecho();
                nodo = nodo.getHermanoDerecho();
                clonarNodo(nodo, aux);
            }
        }
    }

    // toString(): String
    public String toString() {
        // genera y devuelve una cadena de caracteres que indica cuál es la raíz
        // del árbol y quienes son los hijos de cada nodo
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen n) {
        // método recursivo PRIVADO
        String s = "";
        if (n != null) {
            // visita del nodo n
            s += n.getElem().toString() + " -> ";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            // comienza recorrido de los hijos de n llamando recursivamente
            // para que cada hijo agregue su subcadena a la general
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

    // frontera(): Lista
    public Lista frontera() {
        // este método devuelve una lista con las hojas del árbol
        Lista lis = new Lista();
        fronteraAux(this.raiz, lis);
        return lis;
    }

    private void fronteraAux(NodoGen nodo, Lista lis) {
        // método recusrivo PRIVADO
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() == null) {
                lis.insertar(nodo.getElem(), lis.longitud() + 1);
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                fronteraAux(hijo, lis);
                while (hijo != null) {
                    fronteraAux(hijo.getHermanoDerecho(), lis);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    // grado(): int
    public int grado() {
        // devuelve el grado del árbol
        int max = -1; // máximo número de hijos
        max = gradoAux(this.raiz, max);
        return max;
    }

    private int gradoAux(NodoGen nodo, int num) {
        // método recursivo PRIVADO
        int res, aux;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            res = 0;
            while (hijo != null) {
                res++;
                aux = gradoAux(hijo, num);
                num = (num > aux) ? num : aux;
                hijo = hijo.getHermanoDerecho();
            }
            num = (num > res) ? num : res;
        }
        return num;
    }

    // gradoSubarbol(Object elem): int
    public int gradoSubarbol(Object elem) {
        // este método devuelve el grado del nodo que contiene el elemento
        int res = -1;
        res = gradoSubarbolAux(elem, this.raiz, res);
        return res;
    }

    private int gradoSubarbolAux(Object elem, NodoGen nodo, int num) {
        // método recursivo PRIVADO
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                num = gradoAux(nodo, num);
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                num = gradoSubarbolAux(elem, hijo, num);
                while (num == -1 && hijo != null) {
                    hijo = hijo.getHermanoDerecho();
                    num = gradoSubarbolAux(elem, hijo, num);
                }
            }
        }
        return num;
    }

    // listaQueJustificaLaAltura(): Lista
    public Lista listaQueJustificaLaAltura() {
        // devuelve una lista de elementos que es el camino que comienza en la
        // raiz y termina en la hoja más lejana
        Lista res = new Lista();
        Lista actual;
        if (this.raiz != null) {
            actual = res;
            res = listaQueJustificaLaAlturaAux(this.raiz, actual, res);
        }
        return res;
    }

    private Lista listaQueJustificaLaAlturaAux(NodoGen nodo, Lista actual, Lista res) {
        // método recursivo PRIVADO
        Lista temp;
        NodoGen hijo;
        if (nodo != null) {
            actual.insertar(nodo.getElem(), actual.longitud() + 1);
            hijo = nodo.getHijoIzquierdo();
            temp = actual.clone();
            res = temp;
            while (hijo != null) {
                actual = listaQueJustificaLaAlturaAux(hijo, actual, res);
                res = (actual.longitud() > res.longitud()) ? actual : res;
                actual = temp.clone();
                hijo = hijo.getHermanoDerecho();
            }
        }
        return res;
    }

    // verificarCamino(Lista lis): boolean
    public boolean verificarCamino(Lista lis) {
        boolean existeCamino = false;

        if (this.raiz != null) {
            existeCamino = verificarCaminoAux(this.raiz, lis, 1);
        }

        return existeCamino;
    }

    private boolean verificarCaminoAux(NodoGen nodo, Lista lis, int pos) {
        boolean existeCamino = false;

        if (nodo != null) {

            if (nodo.getElem().equals(lis.recuperar(pos))) {

                pos = pos + 1;
                if (pos > lis.longitud()) {
                    existeCamino = true;
                } else {
                    NodoGen hijo = nodo.getHijoIzquierdo();
                    while (hijo != null) {
                        existeCamino = verificarCaminoAux(hijo, lis, pos);
                        if (!existeCamino) {
                            hijo = hijo.getHermanoDerecho();
                        } else {
                            hijo = null;
                        }
                    }
                }
            }

        }
        return existeCamino;
    }

    // listarEntreNiveles(int niv1, int niv2): Lista
    public Lista listarEntreNiveles(int niv1, int niv2){
        Lista listaRetornar = new Lista();
        
        if(this.raiz != null){
            listarEntreNivelesAux(this.raiz, niv1, niv2, -1, listaRetornar);
        }
        
        return listaRetornar;
    }
    
    private void listarEntreNivelesAux(NodoGen nodo, int niv1, int niv2, int nivActual, Lista lis){
        
        if(nodo != null){
            nivActual = nivActual + 1;
            
            if(nodo.getHijoIzquierdo() != null && niv2 > nivActual){
                listarEntreNivelesAux(nodo.getHijoIzquierdo(), niv1, niv2, nivActual, lis);
            }
            
            if(niv1 <= nivActual && niv2 >= nivActual){
                lis.insertar(nodo.getElem(), lis.longitud()+1);
            }
            
            if(nodo.getHijoIzquierdo() != null && niv2 > nivActual){
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while(hijo != null){
                    listarEntreNivelesAux(hijo, niv1, niv2, nivActual, lis);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }
    
    // eliminarConDescendientes(Object elem): void
    public void eliminarConDescendientes(Object elem){
        
        if(this.raiz != null){
            if(this.raiz.getElem().equals(elem)){
                this.raiz = null;
            }else{
                eliminarConDescendientesAux(this.raiz, elem, null);
            }
        }
    }
    
    private void eliminarConDescendientesAux(NodoGen nodo, Object elem, NodoGen padre){
        
        if(nodo != null){
            if(!(nodo.getElem().equals(elem))){
                // Si entra aca, significa que el elemento del nodo no es el elemento buscado
                // Por lo tanto sigue buscandolo
                NodoGen hijo = nodo.getHijoIzquierdo();
                while(hijo != null){
                    eliminarConDescendientesAux(hijo, elem, nodo);
                    hijo = hijo.getHermanoDerecho();
                }
            }else{
                // Si entra aca, significa que encontro el elemento
                if(padre.getHijoIzquierdo().getElem().equals(nodo.getElem())){
                    // Si entra aca, significa que el nodo, es el hijo izquierdo del padre
                    if(nodo.getHermanoDerecho().getElem() == null){
                        // Si entra aca, significa que el hermano del nodo es null, por lo tanto al hijo izq del padre lo seteo a null
                        padre.setHijoIzquierdo(null);
                    }else{
                        // Si entra aca, significa que el hermano derecho de nodo es distinto a null, por lo tanto ese nodo pasa a ser hijo izquierdo
                        padre.setHijoIzquierdo(nodo.getHermanoDerecho());
                    }
                }else{
                    // Si entra aca, significa que el nodo, es algun hijo derecho del padre
                    NodoGen aux = padre.getHijoIzquierdo();
                    while(aux.getHermanoDerecho().getElem().equals(nodo.getElem()) == false){
                        aux = aux.getHermanoDerecho();
                    }
                    if(nodo.getHermanoDerecho() == null){
                        aux.setHermanoDerecho(null);
                    }else{
                        aux.setHermanoDerecho(nodo.getHermanoDerecho());
                    }
                }
            }
        }
    }

}
