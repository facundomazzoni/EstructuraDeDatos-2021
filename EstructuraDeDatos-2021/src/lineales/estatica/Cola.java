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
public class Cola {
    // Atributos

    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    // Constructor
    public Cola() {
        this.arreglo = new Object[this.TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    // Modificadores
    public boolean poner(Object nuevoElem) {
        boolean exito = true;

        if (this.frente == (this.fin + 1) % this.TAMANIO) {
            exito = false;
        } else {
            this.arreglo[this.fin] = nuevoElem;
            this.fin = (this.fin + 1) % this.TAMANIO;
        }

        return exito;
    }

    public boolean sacar() {
        boolean exito = true;

        if (this.esVacia() == true) {
            exito = false;
        } else {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;
        }

        return exito;
    }

    // Observadores
    public Object obtenerFrente() {
        Object elem;

        if (frente == fin) {
            elem = null;
        } else {
            elem = this.arreglo[this.frente];
        }

        return elem;
    }

    public boolean esVacia() {
        boolean vacia;

        if (frente == fin) {
            vacia = true;
        } else {
            vacia = false;
        }

        return vacia;
    }
    // No basicas

    public void vaciar() {
        int i;

        for (i = this.frente; i != this.fin; i = (i + 1) % this.TAMANIO) {
            this.arreglo[i] = null;
        }
    }

    public Cola clone() {
        Cola colaClon = new Cola();
        int i;
        colaClon.frente = this.frente;
        colaClon.fin = this.fin;

        for (i = this.frente; i != this.fin; i = (i + 1) % this.TAMANIO) {
            colaClon.arreglo[i] = this.arreglo[i];
        }

        return colaClon;
    }

    public String toString() {
        String cadena = "";
        int i;

        if (this.fin == this.frente) {
            cadena = "Cola vacia";
        } else {
            for (i = this.frente; i != this.fin; i = (i + 1) % this.TAMANIO) {
                cadena = cadena + this.arreglo[i] + " ";
            }
        }

        return cadena;
    }

}