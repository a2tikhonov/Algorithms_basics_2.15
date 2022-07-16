package ru.atikhonov.integerlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class StringListTest {

    IntegerList out = new IntegerListImpl(20);


    @BeforeEach
    void setUp() {
    }

    @Test
    void add() {
        assertEquals(1, out.add(1));
        assertEquals(1, out.size());
        assertEquals(1, out.get(0));
        assertEquals(2, out.add(2));
        assertEquals(2, out.size());
        assertEquals(2, out.get(1));
        assertThrows(IllegalArgumentException.class, () -> out.add(null));
    }

    @Test
    void testAdd() {
        assertEquals(1, out.add(0, 1));
        assertEquals(1, out.size());
        assertEquals(1, out.get(0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> out.add(1, 2));
    }

    @Test
    void set() {
        out.add(0);
        assertEquals(1, out.set(0, 1));
        assertEquals(1, out.get(0));
    }

    @Test
    void remove() {
        out.add(0);
        out.add(1);
        out.add(2);
        out.remove(1);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> out.remove(3));
        assertEquals(2, out.get(1));
        assertFalse(out.contains(1));
    }

    @Test
    void testRemove() {
        out.add(0);
        out.add(1);
        out.add(2);
        out.remove(2);
        assertThrows(IllegalArgumentException.class, () -> out.remove(Integer.valueOf(4)));
        assertEquals(1, out.get(1));
        assertFalse(out.contains(2));
    }

    @Test
    void contains() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            out.add(random.nextInt());
        }
        out.set(18, 5555);
        assertTrue(out.contains(5555));
    }

    @Test
    void indexOf() {
        out.add(0);
        out.add(1);
        out.add(2);
        assertEquals(1, out.indexOf(1));
        assertEquals(-1, out.indexOf(3));
    }

    @Test
    void lastIndexOf() {
        out.add(0);
        out.add(1);
        out.add(2);
        assertEquals(1, out.lastIndexOf(1));
        assertEquals(-1, out.lastIndexOf(3));
    }

    @Test
    void get() {
        out.add(0);
        assertEquals(0, out.get(0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> out.get(1));
    }

    @Test
    void testEquals() {
        IntegerList expected = new IntegerListImpl(6);
        out.add(0);
        out.add(1);
        out.add(2);
        expected.add(0);
        expected.add(1);
        expected.add(2);
        assertTrue(out.equals(expected));
        expected.add(3);
        assertFalse(out.equals(expected));
    }

    @Test
    void size() {
        out.add(0);
        out.add(1);
        out.add(2);
        assertEquals(3, out.size());
        out.remove(0);
        assertEquals(2, out.size());
        out.add(0);
        out.add(3);
        assertEquals(4, out.size());
    }

    @Test
    void isEmpty() {
        out.add(0);
        assertFalse(out.isEmpty());
        out.remove(0);
        assertTrue(out.isEmpty());
    }

    @Test
    void clear() {
        out.add(0);
        out.add(1);
        out.add(2);
        out.clear();
        assertTrue(out.isEmpty());
    }

    @Test
    void toArray() {
        Integer[] expected = new Integer[] {0, 1, 2};
        out.add(0);
        out.add(1);
        out.add(2);
        Integer[] actual = out.toArray();
        assertArrayEquals(expected, actual);
    }
}