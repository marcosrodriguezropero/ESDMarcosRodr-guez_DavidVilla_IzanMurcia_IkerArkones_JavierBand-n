package ListaSimplementeEnlazada;

import java.util.Iterator;

//Interfaz Lista2 para la lista simplemente enlazada
interface Lista2<T> {
    boolean add(T elemento);
    boolean delete(T elemento);
    Iterator<T> getIterador();
    int getNumElementos();
}

//Clase Elemento que almacena los elementos y hace referencia al dato siguiente
class Elemento <T> {
    T elemento;
    Elemento<T> siguiente;

    public Elemento(T elemento, Elemento<T> siguiente) {
        this.elemento = elemento;
        this.siguiente = siguiente;
    }
}

//Implementación de la lista simplemente enlazada
class ListaSimplementeEnlazada<T> implements Lista2<T> {
    private Elemento<T> cabeza;
    private int numElementos;

    public ListaSimplementeEnlazada() {
        this.cabeza = null;
        this.numElementos = 0;
    }

    @Override
    public boolean add(T elemento) {
        Elemento <T> aux = new Elemento <T>(elemento, this.cabeza);
        if (this.cabeza == null) {
            cabeza = aux;
        } else {
            Elemento <T> aux2 = new Elemento <T>(elemento, this.cabeza);
            this.cabeza = aux2;
            while (this.cabeza.siguiente != null) {
                this.cabeza = this.cabeza.siguiente;
            }
            aux2.siguiente = this.cabeza;
        }
        this.numElementos++;
        return true;
    }

    @Override
    public boolean delete(T elemento) {
        if (this.cabeza == null) {
            return false;
        }

        //Si hay elemento residual
        if (this.cabeza.elemento == elemento) {
            this.cabeza = this.cabeza.siguiente;
            this.numElementos--;
            return true;
        }

        //Buscar elemento en lista
        Elemento <T> aux = new Elemento <T>(elemento, this.cabeza);
        this.cabeza = aux;
        while (this.cabeza.siguiente != null && this.cabeza.siguiente.elemento == elemento) {
            this.cabeza = this.cabeza.siguiente;
        }

        if (this.cabeza.siguiente != null) {
            this.cabeza.siguiente = this.cabeza.siguiente.siguiente;
            this.numElementos--;
            return true;
        }
        return false;

    }
    @Override
    public Iterator<T> getIterador() {
        return new IteradorListaSimplementeEnlazada<T>(cabeza);
    }

    @Override
    public int getNumElementos() {
        return this.numElementos;
    }
}

//Implementación del Iterador para la lista simplemente enlazada
class IteradorListaSimplementeEnlazada<T> implements Iterator<T> {
    private Elemento<T> cabeza;

    public IteradorListaSimplementeEnlazada(Elemento<T> cabeza) {
        this.cabeza = cabeza;
    }

    @Override
    public boolean hasNext() {
        return this.cabeza.siguiente != null;
    }

    @Override
    public T next() {
        if (!this.hasNext()) {
            return null;
        }
        T dato = this.cabeza.elemento;
        this.cabeza = this.cabeza.siguiente;
        return dato;
    }

    public void delete() {
        System.out.println("ListaSimplementeEnlazada eliminada");
    }
}

