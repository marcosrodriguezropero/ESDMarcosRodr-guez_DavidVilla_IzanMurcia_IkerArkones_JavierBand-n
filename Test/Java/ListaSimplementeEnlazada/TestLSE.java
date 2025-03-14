package ListaSimplementeEnlazada;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class TestLSE {
    @Test
    void TestLSE() {
        Lista2<Integer> lista = new ListaSimplementeEnlazada<Integer>();
        lista.add(10);
        lista.add(20);
        lista.add(30);
        lista.delete(20);

        List<Integer> result = new ArrayList<>();
        Iterator<Integer> iterador = lista.getIterador();

        while (iterador.hasNext()) {
            result.add(iterador.next());
        }
        assertEquals(List.of(10, 30), result);
    }

}