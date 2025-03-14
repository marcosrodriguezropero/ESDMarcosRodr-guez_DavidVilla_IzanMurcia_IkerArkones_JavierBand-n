//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// Interfaz Diccionario
interface Diccionario<K, V> {
    boolean add(K clave, V valor);
    boolean delete(K clave);
    Iterador<ElementoDiccionario<K, V>> getIterador();
    boolean exists(K clave);
    V get(K clave);
    int getNumElementos();
}

// Clase ElementoDiccionario para almacenar pares clave-valor
class ElementoDiccionario<K, V> {
    K clave;
    V valor;
    ElementoDiccionario<K, V> siguiente;
    ElementoDiccionario<K, V> anterior;

    public ElementoDiccionario(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }

    public K getClave() {
        return clave;
    }

    public V getValor() {
        return valor;
    }
}

// Implementación de DiccionarioBasico
class DiccionarioBasico<K, V> implements Diccionario<K, V> {
    private ElementoDiccionario<K, V> cabeza;
    private ElementoDiccionario<K, V> cola;
    private int numElementos = 0;

    @Override
    public boolean add(K clave, V valor) {
        ElementoDiccionario<K, V> nuevo = new ElementoDiccionario<>(clave, valor);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        numElementos++;
        return true;
    }

    @Override
    public boolean delete(K clave) {
        ElementoDiccionario<K, V> actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                if (actual.anterior != null) {
                    actual.anterior.siguiente = actual.siguiente;
                } else {
                    cabeza = actual.siguiente;
                }
                if (actual.siguiente != null) {
                    actual.siguiente.anterior = actual.anterior;
                } else {
                    cola = actual.anterior;
                }
                numElementos--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public boolean exists(K clave) {
        return get(clave) != null;
    }

    @Override
    public V get(K clave) {
        ElementoDiccionario<K, V> actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public int getNumElementos() {
        return numElementos;
    }

    @Override
    public Iterador<ElementoDiccionario<K, V>> getIterador() {
        return new IteradorDiccionario(cabeza);
    }

    private class IteradorDiccionario implements Iterador<ElementoDiccionario<K, V>> {
        private ElementoDiccionario<K, V> actual;

        IteradorDiccionario(ElementoDiccionario<K, V> cabeza) {
            this.actual = cabeza;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public ElementoDiccionario<K, V> next() {
            ElementoDiccionario<K, V> temp = actual;
            actual = actual.siguiente;
            return temp;
        }

        @Override
        public void delete() {
            // No implementado en el iterador
        }
    }
}

// Implementación de DiccionarioOrdenado
class DiccionarioOrdenado<K extends Comparable<K>, V> extends DiccionarioBasico<K, V> {
    @Override
    public boolean add(K clave, V valor) {
        ElementoDiccionario<K, V> nuevo = new ElementoDiccionario<>(clave, valor);
        if (super.getNumElementos() == 0) {
            return super.add(clave, valor);
        }
        ElementoDiccionario<K, V> actual = cabeza;
        while (actual != null && actual.clave.compareTo(clave) < 0) {
            actual = actual.siguiente;
        }
        if (actual == null) {
            return super.add(clave, valor);
        }
        nuevo.siguiente = actual;
        nuevo.anterior = actual.anterior;
        if (actual.anterior != null) {
            actual.anterior.siguiente = nuevo;
        } else {
            cabeza = nuevo;
        }
        actual.anterior = nuevo;
        numElementos++;
        return true;
    }
}

// Pruebas unitarias con JUnit 5
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDiccionario {
    @Test
    void testDiccionarioBasico() {
        Diccionario<String, Integer> diccionario = new DiccionarioBasico<>();
        diccionario.add("a", 1);
        diccionario.add("b", 2);
        diccionario.delete("a");
        assertEquals(2, diccionario.get("b"));
        assertFalse(diccionario.exists("a"));
    }

    @Test
    void testDiccionarioOrdenado() {
        Diccionario<String, Integer> diccionario = new DiccionarioOrdenado<>();
        diccionario.add("c", 3);
        diccionario.add("a", 1);
        diccionario.add("b", 2);
        Iterador<ElementoDiccionario<String, Integer>> iterador = diccionario.getIterador();
        assertEquals("a", iterador.next().getClave());
        assertEquals("b", iterador.next().getClave());
        assertEquals("c", iterador.next().getClave());
    }
}
