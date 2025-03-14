// Interfaz Lista
interface Lista<T> {
    boolean add(T elemento);
    boolean delete(T elemento);
    Iterador<T> getIterador();
    int getNumElementos();
}

// Interfaz Iterador
interface Iterador<T> {
    boolean hasNext();
    T next();
    void delete();
}

// Clase Nodo para la lista simplemente enlazada
class Nodo<T> {
    T dato;
    Nodo<T> siguiente;

    Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

// Lista simplemente enlazada
class ListaSimplementeEnlazada<T> implements Lista<T> {
    private Nodo<T> cabeza;
    private int numElementos;

    @Override
    public boolean add(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        numElementos++;
        return true;
    }

    @Override
    public boolean delete(T elemento) {
        if (cabeza == null) return false;
        if (cabeza.dato.equals(elemento)) {
            cabeza = cabeza.siguiente;
            numElementos--;
            return true;
        }
        Nodo<T> actual = cabeza;
        while (actual.siguiente != null && !actual.siguiente.dato.equals(elemento)) {
            actual = actual.siguiente;
        }
        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            numElementos--;
            return true;
        }
        return false;
    }

    @Override
    public Iterador<T> getIterador() {
        return new IteradorListaSimplementeEnlazada(cabeza);
    }

    @Override
    public int getNumElementos() {
        return numElementos;
    }

    //iterador
    private class IteradorListaSimplementeEnlazada implements Iterador<T> {
        private Nodo<T> actual;
        private Nodo<T> previo;

        IteradorListaSimplementeEnlazada(Nodo<T> cabeza) {
            this.actual = cabeza;
            this.previo = null;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            if (actual == null) return null;
            T dato = actual.dato;
            previo = actual;
            actual = actual.siguiente;
            return dato;
        }

        @Override
        public void delete() {
            if (previo != null) {
                previo.siguiente = actual.siguiente;
                actual = actual.siguiente;
            }
        }
    }
}

// Pila
class Pila<T> {
    private ListaSimplementeEnlazada<T> lista = new ListaSimplementeEnlazada<>();

    public void push(T elemento) {
        lista.add(elemento);
    }

    public T pop() {
        if (lista.getNumElementos() == 0) return null;
        Iterador<T> iterador = lista.getIterador();
        T ultimo = null;
        while (iterador.hasNext()) {
            ultimo = iterador.next();
        }
        lista.delete(ultimo);
        return ultimo;
    }
}

// Cola
class Cola<T> {
    private ListaSimplementeEnlazada<T> lista = new ListaSimplementeEnlazada<>();

    public void enqueue(T elemento) {
        lista.add(elemento);
    }

    public T dequeue() {
        if (lista.getNumElementos() == 0) return null;
        Iterador<T> iterador = lista.getIterador();
        T primero = iterador.next();
        lista.delete(primero);
        return primero;
    }
}

// test
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestPilaCola {
    @test
    void testPila() {
        Pila<Integer> pila = new Pila<>();
        pila.push(1);
        pila.push(2);
        pila.push(3);
        assertEquals(3, pila.pop());
        assertEquals(2, pila.pop());
        assertEquals(1, pila.pop());
    }

    @Test
    void testCola() {
        Cola<Integer> cola = new Cola<>();
        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        assertEquals(10, cola.dequeue());
        assertEquals(20, cola.dequeue());
        assertEquals(30, cola.dequeue());
    }
}
