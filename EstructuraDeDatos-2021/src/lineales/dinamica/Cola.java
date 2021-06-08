/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamica;

/**
 *
 * @author Facu
 */
public class Cola {

    private Nodo frente;
    private Nodo fin;

    // Constructor
    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    // Modificadores (poner y sacar)
    public boolean poner(Object nuevoElemento) {
        Nodo nuevoNodo = new Nodo(nuevoElemento, null);
        if (esVacia()) {
            this.frente = nuevoNodo;
        } else {
            this.fin.setEnlace(nuevoNodo);
        }
        
        this.fin = nuevoNodo;
        return true;
    }

    public boolean sacar() {
        boolean exito = true;

        if (this.frente == null) {
            exito = false;
        } else {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }

        return exito;
    }
    
    // Observadores (obtenerTope y esVacia)
    public Object obtenerFrente(){
        Object elemento;
        
        if(this.frente == null){
            elemento = null;
        }else{
            elemento = this.frente.getElem();
        }
        
        return elemento;
    }
    
    public boolean esVacia(){
        boolean vacia;
        
        vacia = this.frente == null;
        
        return vacia;
    }
    
    // No basicas (vaciar - clone - toString)
    public void vaciar(){
        
        this.frente = null;
    }
    
    public Cola clone(){

        Cola colaClon = new Cola();
        Nodo nodoOriginal, nodoAux2, nodoAux;

        if (this.esVacia() == false) {
            nodoAux2 = new Nodo(this.frente.getElem(), null);
            colaClon.frente = nodoAux2;
            nodoOriginal = this.frente.getEnlace();
            while (nodoOriginal != null) {
                nodoAux = new Nodo(nodoOriginal.getElem(), null);
                nodoAux2.setEnlace(nodoAux);
                colaClon.fin = nodoAux;
                nodoAux2 = nodoAux2.getEnlace();
                nodoOriginal = nodoOriginal.getEnlace();
            }

        }
        return colaClon;

    }
    
    public String toString(){
        String cadena;
        
        if(this.frente == null){
            cadena = "Cola vacia";
        }else{
            Nodo aux1 = this.frente;
            cadena = "[";
            
            while(aux1 != null){
                cadena = cadena + aux1.getElem().toString();
                aux1 = aux1.getEnlace();
                if(aux1 != null){
                    cadena = cadena + ",";
                }
            }
            
            cadena = cadena + "]";
        }
        
        return cadena;
    }
}