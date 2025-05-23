package um.edu.uy.Implementaciones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import um.edu.uy.Exceptions.EmptyListException;
import um.edu.uy.Exceptions.ListOutOfIndex;
import um.edu.uy.Exceptions.ValueNoExist;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListImplTest {

    private MyLinkedListImpl<String> list;

    @BeforeEach
    void setup() {
        list = new MyLinkedListImpl<>();
    }

    @Test
    void testIsEmptyInitially() {
        assertTrue(list.isEmpty());
    }

    @Test
    void testAddAndSize() {
        list.add("A");
        list.add("B");
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void testAddFirst() throws ListOutOfIndex, EmptyListException {
        list.add("B");
        list.addFirst("A");
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testAddLastOnNonEmpty() throws EmptyListException {
        list.add("A");
        list.addLast("B");
        assertEquals("B", list.get(1));
    }

    @Test
    void testAddAtIndexMiddle() throws Exception {
        list.add("A");
        list.add("C");
        list.add("B", 1);
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testAddAtIndexStart() throws Exception {
        list.add("B");
        list.add("C");
        list.add("A", 0);
        assertEquals("A", list.get(0));
    }

    @Test
    void testAddAtIndexEnd() throws Exception {
        list.add("A");
        list.add("B");
        list.add("C", 1);
        assertEquals("C", list.get(1));
    }

    @Test
    void testDeleteFirstAndLast() throws Exception {
        list.add("A");
        list.add("B");
        assertEquals("A", list.deleteFirst());
        assertEquals("B", list.deleteLast());
        assertTrue(list.isEmpty());
    }

    @Test
    void testDeleteMiddleElement() throws Exception {
        list.add("A");
        list.add("B");
        list.add("C");
        String deleted = list.delete(1);
        assertEquals("B", deleted);
        assertEquals("C", list.get(1));
    }

    @Test
    void testDeleteValue() throws Exception {
        list.add("A");
        list.add("B");
        list.add("C");
        list.deleteValue("B");
        assertFalse(list.contains("B"));
        assertEquals(2, list.size());
    }

    @Test
    void testDeleteValueNotFoundThrows() {
        list.add("A");
        assertThrows(ValueNoExist.class, () -> list.deleteValue("Z"));
    }

    @Test
    void testGet() throws Exception {
        list.add("A");
        list.add("B");
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testGetInvalidIndex() {
        assertThrows(EmptyListException.class, () -> list.get(0));
    }

    @Test
    void testDeleteInvalidIndex() {
        list.add("A");
        assertThrows(ListOutOfIndex.class, () -> list.delete(1));
    }

    @Test
    void testDeleteOnEmpty() {
        assertThrows(EmptyListException.class, () -> list.deleteFirst());
        assertThrows(EmptyListException.class, () -> list.deleteLast());
        assertThrows(EmptyListException.class, () -> list.delete(0));
    }

    @Test
    void testContains() {
        list.add("X");
        assertTrue(list.contains("X"));
        assertFalse(list.contains("Z"));
    }

    @Test
    void testIntercambiate() throws Exception {
        list.add("A");
        list.add("B");
        list.intercambiate(0, 1);
        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1));
    }

    @Test
    void testIntercambiateInvalid() {
        list.add("A");
        assertThrows(ListOutOfIndex.class, () -> list.intercambiate(0, 1));
    }
}
