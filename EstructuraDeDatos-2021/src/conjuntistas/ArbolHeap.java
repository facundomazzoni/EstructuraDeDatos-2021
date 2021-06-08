/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author Facu
 */
public class ArbolHeap {

    // Atributos
    private static final int TAMANIO = 10;
    private Comparable[] heap;
    private int cant;

    // Constructor
    public ArbolHeap() {
        this.heap = new Comparable[TAMANIO];
        this.cant = 0;
    }

    // insertar(TipoElemento): boolean
    public boolean insertar(Comparable elem) {
        boolean exito;

        if (this.cant + 1 >= TAMANIO) {
            exito = false;
        } else {
            exito = true;
            if (this.cant == 0) {
                cant = cant + 1;
                this.heap[cant] = elem;
            } else {
                cant = cant + 1;
                this.heap[cant] = elem;
                hacerSubir(cant);
            }

        }
        return exito;
    }

    private void hacerSubir(int posHijo) {
        int posPadre = (posHijo / 2);
        boolean salir = false;
        Comparable aux;

        while (salir == false) {

            if (this.heap[posHijo].compareTo(this.heap[posPadre]) < 0) {
                aux = this.heap[posHijo];
                this.heap[posHijo] = this.heap[posPadre];
                this.heap[posPadre] = aux;
                posHijo = posPadre;
                posPadre = posHijo / 2;
            } else {
                salir = true;
            }

            if (posPadre == 1) {
                if (this.heap[posHijo].compareTo(this.heap[posPadre]) < 0) {
                    aux = this.heap[posHijo];
                    this.heap[posHijo] = this.heap[posPadre];
                    this.heap[posPadre] = aux;
                }
            }
        }
    }

    // eliminarCima(): boolean
    public boolean eliminarCima() {
        boolean exito;
        if (this.cant == 0) {
            // estructura vacia
            exito = false;
        } else {
            // saca la raiz y pone la ultima hoja en su lugar
            this.heap[1] = this.heap[cant];
            this.cant--;
            // reestablece la propiedad de heap minimo
            hacerBajar(1);
            exito = true;
        }

        return exito;
    }

    private void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (salir == false) {
            posH = posPadre * 2;
            if (posH <= this.cant) {
                // temp tiene al menos un hizo (izq) y lo considera menor
                if (posH < this.cant) {
                    // hijoMenor tiene hermano derecho
                    if (this.heap[posH + 1].compareTo(this.heap[posH]) < 0) {
                        // el hijo derecho es el menor de los dos
                        posH++;
                    }
                }

                // compara al hijo menor con el padre
                if (this.heap[posH].compareTo(temp) < 0) {
                    // el hijo es menor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    // el padre es menor que sus hijos, estan bien ubicado
                    salir = true;
                }
            } else {
                // el temp es hoja, esta bien ubicado
                salir = true;
            }
        }
    }

    // recuperarCima(): TipoElemento
    public Object recuperarCima() {
        Object resultado = null;

        if (this.cant != 0) {
            resultado = this.heap[1];
        }

        return resultado;
    }

    // esVacio(): boolean
    public boolean esVacio() {
        return this.cant == 0;
    }

    public String toString() {
        String cadena = "[";

        if (this.esVacio() != true) {
            int i;
            for (i = 1; i <= this.cant; i++) {
                cadena = cadena + this.heap[i] + "|";
            }
            cadena = cadena + "]";
        } else {
            cadena = "Arbol Heap vacio";
        }

        return cadena;
    }
}
