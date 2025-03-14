package ListaDoblementeEnlazada;

import Lista_basica.Lista;

import java.util.Iterator;

//
class ElementoDoble<T> {
    T elemento;
    ElementoDoble<T> siguiente;
    ElementoDoble<T> anterior;

    public ElementoDoble(T Elemento) {
        this.elemento = Elemento;
        this.siguiente = null;
        this.anterior = null;
    }
}

class ListaDoblementeEnlazada<T> implements Lista<T> {
    private ElementoDoble<T> cabeza;
    private ElementoDoble<T> cola;
    private int numElementos;

    public ListaDoblementeEnlazada(){
        this.cabeza = null;
        this.cola = null;
        this.numElementos = 0;
    }

    @Override
    public boolean add(T elemento) {
        ElementoDoble<T> aux = new ElementoDoble<T>(elemento);
        if(this.cabeza == null){
            this.cabeza = aux;
            this.cola = aux;
        } else {
            this.cabeza.anterior = aux;
            this.cola.anterior = aux;
        }
        this.numElementos++;
        return true;
    }

    @Override
    public boolean delete(T elemento) {
        ElementoDoble aux2 = this.cabeza;
        while (aux2 != null){
            if (aux2.elemento == elemento){
                if(aux2.anterior != null) {
                    aux2.siguiente = aux2.anterior.siguiente;
                } else {
                    aux2.anterior = aux2.siguiente;
                }
                if (aux2.siguiente != null) {
                    aux2.siguiente = aux2.siguiente.anterior;
                } else {
                    this.cabeza = aux2.anterior;
                }
                this.numElementos--;
                return true;
            }
            aux2 = aux2.anterior;
        }
        return false;
    }

    @Override
    public Iterator getIterador() {
        return new IteradorListaDoblementeEnlazada<T>(cabeza);
    }

    @Override
    public int getNumElementos() {
        return 0;
    }

    public int numElementos() {
        return this.numElementos;
    }
}

class IteradorListaDoblementeEnlazada<T> implements Iterator<T> {
    private ElementoDoble<T> actual;

    public IteradorListaDoblementeEnlazada() {
        this.actual = null;
    }

    public IteradorListaDoblementeEnlazada(ElementoDoble<T> cabeza){
        this.actual = cabeza;
    }

    @Override
    public boolean hasNext() {
        return this.actual != null;
    }

    @Override
    public T next() {
        if (!hasNext()){
            return null;
        }
        T aux = this.actual.elemento;
        this.actual = this.actual.siguiente;
        return aux;
    }

    public boolean hasPrevious() {
        return this.actual != null && this.actual.anterior != null;
    }

    public T previous() {
        if (!hasPrevious()){
            return null;
        }
        T aux = this.actual.elemento;
        this.actual = this.actual.siguiente;
        return aux;
    }
}
