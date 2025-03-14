package Lista_basica;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ListaTest {
    @Test
    void ListaTest() {
        Lista<Integer> lista = new ListaArray<>();
        lista.add(11);
        lista.add(2);
        lista.add(5);
        lista.add(8);

        System.out.println("Elementos: " + lista.getNumElementos());

        Iterator<Integer> iterador = lista.getIterador();
        while (iterador.hasNext()) {
            System.out.println("Número: " + iterador.next());
        }

        lista.delete(11);
        System.out.println("Elementos después de eliminar 11: " + lista.getNumElementos());
    }
}