/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estatica;

/**
 *
 * @author Facu
 */
public class Pila {

    private Object[] arreglo;
    private int tope;
    private static final int TAMAÑO = 3;

    // Constructor
    public Pila() {
        this.arreglo = new Object[TAMAÑO];
        this.tope = -1;
    }

    // Modificadoras
    public boolean apilar(Object nuevoElem) {
        boolean exito;

        if (this.tope + 1 >= this.TAMAÑO) {
            exito = false;
        } else {
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
        }

        return exito;
    }

    public boolean desapilar() {
        boolean exito;

        if (this.tope == -1) {
            exito = false;
        } else {
            this.arreglo[tope] = null;
            this.tope--;
            exito = true;
        }

        return exito;
    }

    // Observadoras
    public Object obtenerTope() {
        Object elem;

        if (this.tope != -1) {
            elem = this.arreglo[tope];
        } else {
            elem = null;
        }

        return elem;
    }

    public boolean esVacia() {
        boolean pilaVacia;

        if (this.tope == -1) {
            pilaVacia = true;
        } else {
            pilaVacia = false;
        }

        return pilaVacia;
    }

    // No basicas
    public void vaciar() {
        int i;

        for (i = this.tope; i >= 0; i--) {
            this.arreglo[i] = null;
            this.tope--;
        }
    }

    public Pila clone() {
        Pila pilaClonada = new Pila();
        pilaClonada.tope = this.tope;
        int i;

        for (i = 0; i <= this.tope; i++) {
            pilaClonada.arreglo[i] = this.arreglo[i];
        }
        return pilaClonada;
    }

    public String toString() {
        String cadena = "";
        int i;

        if (this.tope == -1) {
            cadena = "Pila vacia";
        } else {
            for (i = 0; i <= this.tope; i++) {
                cadena = cadena + this.arreglo[i] + " ";
            }
        }

        return cadena;
    }
}
