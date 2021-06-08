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
public class Lista {

    // Atributos
    private Nodo cabecera;

    // Constructor
    public Lista() {
        cabecera = null;
    }

    // Modificadores
    public boolean insertar(Object nuevoElemento, int pos) {
        boolean exito = true;

        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = new Nodo(nuevoElemento, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i = i + 1;
                }
                Nodo nuevo = new Nodo(nuevoElemento, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }

        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = true;

        if (pos < 1 || pos > this.longitud()) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i = i + 1;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
        }

        return exito;
    }

    // Observadores
    public Object recuperar(int pos) {
        Object elem = null;

        if (pos < 1 || pos > this.longitud()) {
            elem = null;
        } else {
            Nodo aux = this.cabecera;
            int i = 1;
            while (i != pos) {
                aux = aux.getEnlace();
                i++;
            }
            if (aux != null) {
                elem = aux.getElem();
            }
        }
        return elem;
    }

    public int localizar(Object elemento) {
        int posElemento = -1, contador = 1;
        Nodo aux = this.cabecera;
        boolean encontrado = false;

        while (encontrado == false && aux != null) {
            if (aux.getElem().equals(elemento)) {
                posElemento = contador;
                encontrado = true;
            } else {
                aux = aux.getEnlace();
                contador = contador + 1;
            }
        }

        return posElemento;
    }

    public int longitud() {
        int longi = 0;
        Nodo aux = this.cabecera;

        while (aux != null) {
            longi = longi + 1;
            aux = aux.getEnlace();
        }

        return longi;
    }

    public boolean esVacia() {
        boolean vacia = false;

        if (this.cabecera == null) {
            vacia = true;
        }

        return vacia;
    }

    // Propias del tipo
    public void vaciar() {
        this.cabecera = null;
    }

    public Lista clone() {
        Lista listaClon = new Lista();
        listaClon.cabecera = new Nodo(this.cabecera.getElem(), null);
        Nodo aux1 = this.cabecera;
        Nodo aux2 = listaClon.cabecera;
        aux1 = aux1.getEnlace();

        while (aux1 != null) {
            aux2.setEnlace(new Nodo(aux1.getElem(), null));
            aux1 = aux1.getEnlace();
            aux2 = aux2.getEnlace();
        }

        return listaClon;
    }

    public String toString() {
        String cadena;

        if (this.cabecera == null) {
            cadena = "Lista vacia";
        } else {
            Nodo aux1 = this.cabecera;
            cadena = "[";

            while (aux1 != null) {
                cadena = cadena + aux1.getElem().toString();
                aux1 = aux1.getEnlace();
                if (aux1 != null) {
                    cadena = cadena + ",";
                }
            }

            cadena = cadena + "]";
        }

        return cadena;
    }

    public void invertir() {
        Nodo ultimo = inviertoLista(this.cabecera);
        ultimo.setEnlace(null);
    }

    private Nodo inviertoLista(Nodo aux) {
        if (aux.getEnlace() == null) {
            this.cabecera = aux;
        } else {
            Nodo aux2 = inviertoLista(aux.getEnlace());
            aux2.setEnlace(aux);
        }
        return aux;
    }

    public Lista obtenerMultiplos(int num) {
        Lista listaRetornar = new Lista();
        Nodo aux, aux2;
        int i;

        if (this.cabecera != null) {
            i = 1;
            aux = this.cabecera;
            while (aux.getEnlace() != null && i != num) {
                i++;
                aux = aux.getEnlace();
            }
            if (i == num) {
                listaRetornar.cabecera = new Nodo(aux.getElem(), null);
                aux2 = listaRetornar.cabecera;
                while (aux.getEnlace() != null) {
                    aux = aux.getEnlace();
                    i++;
                    if (i % num == 0) {
                        aux2.setEnlace(new Nodo(aux.getElem(), null));
                        aux2 = aux2.getEnlace();
                    }
                }
            }
        }

        return listaRetornar;
    }

    public Lista eliminarAparicion(Object x) {
        Lista lis = new Lista();
        Nodo aux, copia = null;

        if (this.cabecera != null) {
            aux = this.cabecera;

            while (aux != null && lis.cabecera == null) {
                if (aux.getElem().equals(x) == false) {
                    lis.cabecera = new Nodo(aux.getElem(), null);
                    copia = lis.cabecera;
                }
                aux = aux.getEnlace();
            }

            if (copia != null) {
                while (aux != null) {
                    if (aux.getElem().equals(x) == false) {
                        copia.setEnlace(new Nodo(aux.getElem(), null));
                        copia = copia.getEnlace();
                    }
                    aux = aux.getEnlace();
                }
            }

        }

        return lis;
    }

    public void insertarPosterior(Object valor1, Object valor2) {

        Nodo aux = this.cabecera;
        Nodo nuevo;

        if (aux != null) {
            while (aux.getEnlace() != null) {
                if (this.cabecera.getElem().equals(valor1)) {
                    this.cabecera = new Nodo(valor2, null);
                    this.cabecera.setEnlace(aux);
                }
                
                if(aux.getElem().equals(valor1)){
                    nuevo = aux.getEnlace();
                    aux.setEnlace(new Nodo(valor2, null));
                    aux.getEnlace().setEnlace(nuevo);
                }
                aux = aux.getEnlace();
            }

        }
    }
    
    public void insertarPosPrevia(Object valor1, Object valor2) {
        if (this.cabecera != null) {
            insertarPosPreviaAux(this.cabecera, valor1, valor2);
        }
    }

    private void insertarPosPreviaAux(Nodo nodo, Object buscado, Object insertar) {
        Nodo aux;
        if (nodo != null) {
            while (nodo.getEnlace() != null) {
                
                if (this.cabecera.getElem() == buscado) {
                    
                    this.cabecera = new Nodo(insertar, null);
                    this.cabecera.setEnlace(nodo);
                } 
                if (nodo.getEnlace().getElem().equals(buscado)) {
                    aux = nodo.getEnlace();
                    nodo.setEnlace(new Nodo(insertar, null));
                    nodo.getEnlace().setEnlace(aux);
                    nodo = nodo.getEnlace();
                }
                nodo = nodo.getEnlace();
            }
        }
    }
}
