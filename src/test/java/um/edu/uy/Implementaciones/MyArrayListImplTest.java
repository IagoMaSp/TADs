package um.edu.uy.Implementaciones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import um.edu.uy.Exceptions.EmptyListException;
import um.edu.uy.Exceptions.ListOutOfIndex;
import um.edu.uy.Exceptions.ValueNoExist;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListImplTest {
    private MyArrayListImpl<String> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayListImpl<>(2);
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
    void testAddAtIndex() throws ListOutOfIndex {
        list.add("A");
        list.add("C");
        list.add("B", 1);
        assertEquals("B", list.get(1));
        assertEquals(3, list.size());
    }

    @Test
    void testAddFirst() {
        list.add("A");
        list.add("B");
        list.addFirst("Z");
        assertEquals("Z", list.get(0));
    }

    @Test
    void testDeleteByIndex() throws Exception {
        list.add("A");
        list.add("B");
        String removed = list.delete(0);
        assertEquals("A", removed);
        assertEquals("B", list.get(0));
    }

    @Test
    void testDeleteFirstAndLast() throws Exception {
        list.add("X");
        list.add("Y");
        assertEquals("X", list.deleteFirst());
        assertEquals("Y", list.deleteLast());
    }

    @Test
    void testDeleteValue() throws Exception {
        list.add("A");
        list.add("B");
        list.deleteValue("A");
        assertEquals(1, list.size());
        assertFalse(list.contains("A"));
    }

    @Test
    void testDeleteValueNotFound() {
        list.add("A");
        assertThrows(ValueNoExist.class, () -> list.deleteValue("Z"));
    }

    @Test
    void testDeleteOnEmptyThrows() {
        assertThrows(EmptyListException.class, () -> list.delete(0));
        assertThrows(EmptyListException.class, () -> list.deleteFirst());
        assertThrows(EmptyListException.class, () -> list.deleteLast());
    }

    @Test
    void testGetValidAndInvalid() throws Exception {
        list.add("Hello");
        assertEquals("Hello", list.get(0));
        assertThrows(ListOutOfIndex.class, () -> list.get(1));
    }

    @Test
    void testContains() {
        list.add("A");
        assertTrue(list.contains("A"));
        assertFalse(list.contains("B"));
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