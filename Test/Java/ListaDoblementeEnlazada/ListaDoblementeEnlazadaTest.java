package ListaDoblementeEnlazada;

import Lista_basica.Lista;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListaDoblementeEnlazadaTest {
    @Test
    void testListaDoblementeEnlazada() {
        Lista<Integer> lista = new ListaDoblementeEnlazada<Integer>();
        lista.add(5);
        lista.add(15);
        lista.add(25);
        lista.delete(15);
        List<Integer> resultado = new ArrayList<>();
        Iterator<Integer> iterador = lista.getIterador();
        while (iterador.hasNext()) {
            resultado.add(iterador.next());
        }
        assertEquals(List.of(5, 25), resultado);
    }
}