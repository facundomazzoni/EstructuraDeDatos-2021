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
public class Pila {
    
    private Nodo tope;
    
    //Constructor
    public Pila(){
        this.tope = null;
    }
    
    // Modificadores
    public boolean apilar(Object nuevoElem){
        
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        
        this.tope = nuevo;
        
        return true;
    }
    
    public boolean desapilar(){
        boolean exito;
        
        if(this.tope == null){
            exito = false;
        }else{
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        
        return exito;
    }
    
    // Observadores
    public Object obtenerTope(){
        Object elem;
        
        if(this.tope == null){
            elem = null;
        }else{
            elem = this.tope.getElem();
        }
        
        return elem;
    }
    
    public boolean esVacia(){
        boolean vacia;
        
        if(this.tope == null){
            vacia = true;
        }else{
            vacia = false;
        }
        
        return vacia;
    }
    
    // No basicas
    public void vaciar(){
        
        this.tope = null;
    }
    
    public Pila clone(){
        Pila pilaClonada = new Pila();
        Nodo aux = this.tope;
        
        while(aux != null){
            pilaClonada.tope = this.tope;
            aux = aux.getEnlace();
        }
        
        return pilaClonada;
    }
   
    public String toString(){
        String s;
        
        if(this.tope == null){
            s = "Pila vacia";
        }else{
            Nodo aux = this.tope;
            s = "[";
            
            while(aux != null){
                s = s + aux.getElem().toString();
                aux = aux.getEnlace();
                if(aux != null){
                    s = s + ",";
                }
            }
            s = s + "]";
        }
        
        return s;
    }
}
