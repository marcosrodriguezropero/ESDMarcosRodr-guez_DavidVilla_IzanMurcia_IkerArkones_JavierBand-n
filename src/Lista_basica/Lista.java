package Lista_basica;

import java.util.Iterator;

//Definimos la interfaz lista<T>
public interface Lista<T> {
    boolean add(T elemento);
    boolean delete(T elemento);
    Iterator<T> getIterador();
    int getNumElementos();
}

//Definimos un Array para la interfaz con sus métodos previos
class ListaArray<T> implements Lista<T> {
    private T[] elementos;
    private int numElementos;
    private int inicio = 10;

    public ListaArray() {
        elementos = (T[]) new Object[10];
        numElementos = 0;
    }

    @Override
    public boolean add(T elemento) {
        if (numElementos == elementos.length) {
            expansionDominio();
        }
        elementos[numElementos] = elemento;
        numElementos++;
        return true;
    }

    private void expansionDominio() {
        T[] aux = (T[]) new Object[elementos.length * 2];
        for (int i = 0; i < numElementos; i++) {
            aux[i] = elementos[i];
        }
        elementos = aux;
    }

    @Override
    public boolean delete(T elemento) {
        for (int i = 0; i < numElementos; i++) {
            if (elementos[i] == elemento) {
                for (int j = i + 1; j < numElementos; j++) {
                    elementos[j] = elementos[j - 1];
                }
                elementos[i] = null;
                numElementos--;
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> getIterador() {
        return new IteradorArray<>(elementos, numElementos);
    }

    @Override
    public int getNumElementos() {
        return numElementos;
    }

//Implementamos un Array con su métodos
class IteradorArray<T> implements Iterator<T> {
    private T[] elementos;
    private int numElementos;
    private int inicio = 0;

    public IteradorArray(T[] elementos, int numElementos) {
        this.elementos = elementos;
        this.numElementos = numElementos;
    }

    @Override
    public boolean hasNext() {
        return inicio < numElementos;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new RuntimeException("Lista vacía");
        }
        return elementos[inicio++];
    }

    public void delete() {
    }
}
}



