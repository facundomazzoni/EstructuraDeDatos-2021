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
public class Nodo {
    
    private Object elem;
    private Nodo enlace;
    
    // Constructor
    public Nodo(Object elemento, Nodo enlace){
        this.elem = elemento;
        this.enlace = enlace;
    }
    
    // Modificadores
    public void setElem(Object elem){
        this.elem = elem;
    }
    
    public void setEnlace(Nodo enlace){
        this.enlace = enlace;
    }
    
    // Observadores
    public Object getElem(){
        return elem;
    }
    
    public Nodo getEnlace(){
        return enlace;
    }

}